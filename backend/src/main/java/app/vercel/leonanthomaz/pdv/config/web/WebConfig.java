package app.vercel.leonanthomaz.pdv.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configurações Web para permitir o CORS em diferentes endpoints da API.
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configura as permissões CORS para diferentes endpoints da API.
     *
     * @param registry O registro CORS que contém os mapeamentos de URLs e as permissões associadas.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Configuração para o endpoint /auth/**
        configureCorsForEndpoint(registry, "/auth/**");
        // Configuração para o endpoint /users/**
        configureCorsForEndpoint(registry, "/users/**");
        // Configuração para o endpoint /products/**
        configureCorsForEndpoint(registry, "/products/**");
        // Configuração para o endpoint /cashier/**
        configureCorsForEndpoint(registry, "/cashier/**");
        // Configuração para o endpoint /cashieritem/**
        configureCorsForEndpoint(registry, "/cashieritem/**");
    }

    /**
     * Configura as permissões CORS para um endpoint específico.
     *
     * @param registry O registro CORS que contém os mapeamentos de URLs e as permissões associadas.
     * @param pathPattern O padrão de caminho do endpoint.
     */
    private void configureCorsForEndpoint(CorsRegistry registry, String pathPattern) {
        registry.addMapping(pathPattern) // Permitir apenas em URLs sob o caminho especificado
                .allowedOriginPatterns("*") // Permitir de todas as origens
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permitir todos os métodos
                .allowCredentials(true); // Permitir credenciais
    }
}