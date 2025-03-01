package com.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class BaseProjApplication implements ApplicationListener<ContextRefreshedEvent> {

    public static void main(String[] args) {
        SpringApplication.run(BaseProjApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext() instanceof ServletWebServerApplicationContext) {
            Environment env = event.getApplicationContext().getEnvironment();
            String serverPort = env.getProperty("server.port", "8080");
            String contextPath = env.getProperty("server.servlet.context-path", "");

            System.out.println("\n----------------------------------------------------------");
            System.out.println("API文档地址：");
            System.out.println("Knife4j文档：http://localhost:" + serverPort + contextPath + "/doc.html");
            System.out.println("Swagger文档：http://localhost:" + serverPort + contextPath + "/swagger-ui/listVehicle.html");
            System.out.println("----------------------------------------------------------\n");
        }
    }
}
