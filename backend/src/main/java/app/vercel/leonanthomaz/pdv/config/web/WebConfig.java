package app.vercel.leonanthomaz.pdv.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/auth/**") // Permitir apenas em URLs sob o caminho /auth/**
                .allowedOriginPatterns("*") // Permitir de todas as origens
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permitir todos os métodos
                .allowCredentials(true); // Permitir credenciais
        registry.addMapping("/users/**") // Permitir apenas em URLs sob o caminho /auth/**
                .allowedOriginPatterns("*") // Permitir de todas as origens
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permitir todos os métodos
                .allowCredentials(true); // Permitir credenciais
        registry.addMapping("/products/**") // Permitir apenas em URLs sob o caminho /auth/**
                .allowedOriginPatterns("*") // Permitir de todas as origens
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permitir todos os métodos
                .allowCredentials(true); // Permitir credenciais
        registry.addMapping("/cashier/**") // Permitir apenas em URLs sob o caminho /auth/**
                .allowedOriginPatterns("*") // Permitir de todas as origens
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permitir todos os métodos
                .allowCredentials(true); // Permitir credenciais
        registry.addMapping("/cashieritem/**") // Permitir apenas em URLs sob o caminho /auth/**
                .allowedOriginPatterns("*") // Permitir de todas as origens
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permitir todos os métodos
                .allowCredentials(true); // Permitir credenciais

    }
}