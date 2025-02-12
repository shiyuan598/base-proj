package com.base.security;

import com.base.common.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserDetailsServiceImpl userDetailsService) throws Exception {
        // 手动设置 JwtFilter 的 UserDetailsService
        jwtFilter.setUserDetailsService(userDetailsService);

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // 放行登录接口
                        .requestMatchers("/auth/login", "/auth/register", "/auth/forgetPassword").permitAll()
                        // 放行 Knife4j 和 Swagger 文档相关路径
                        .requestMatchers(
                                "/doc.html",                 // Knife4j 文档页面
                                "/webjars/**",               // Knife4j 依赖的资源
                                "/v3/api-docs/**",           // OpenAPI 规范数据
                                "/swagger-resources/**",      // Swagger 资源
                                "/swagger-ui/**",            // Swagger UI 界面
                                "/swagger-ui.html"           // Swagger UI 页面
                        ).permitAll()
                        // 放行静态资源路径
                        .requestMatchers("/static/**", "/resources/**", "/images/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}