package com.example.quiz.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/submit") // Specify the endpoint you want to allow CORS for
                        .allowedOrigins("http://127.0.0.1:5500") // Whitelist the origin
                        .allowedMethods("POST") // Specify the allowed HTTP methods
                        .allowCredentials(true); // Allow credentials (if needed)
            }
        };
    }
}
