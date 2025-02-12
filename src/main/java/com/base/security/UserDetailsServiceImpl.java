package com.base.security;

import com.base.vm.entity.VUser;
import com.base.vm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
        logger.info("UserDetailsServiceImpl initialized with UserService: {}", userService.getClass().getName());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户信息
        com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<VUser> wrapper = new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<>();
        wrapper.eq(VUser::getUsername, username);
        VUser vUser = userService.getOne(wrapper);

        if (vUser == null) {
            throw new UsernameNotFoundException("用户 " + username + " 不存在");
        }

        // 构建用户权限集合
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 这里简单假设用户角色信息存储在 VUser 的 role 字段，可根据实际情况调整
        String role = "ROLE_" + vUser.getRole();
        authorities.add(new SimpleGrantedAuthority(role));

        // 构建 Spring Security 的 UserDetails 对象
        return new User(
                vUser.getUsername(),
                vUser.getPassword(),
                true, // 是否启用
                true, // 账户是否未过期
                true, // 密码是否未过期
                true, // 账户是否未锁定
                authorities
        );
    }
}