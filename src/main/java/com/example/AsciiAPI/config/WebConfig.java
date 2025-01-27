package com.example.AsciiAPI.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Tillåter endast API-ändpunkter
                .allowedOrigins("http://localhost:5500") // Den port där frontend körs (Live Server i VSCode)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // De metoder du vill tillåta
                .allowedHeaders("*") // Tillåter alla headers
                .allowCredentials(true); // Om du behöver hantera cookies/sessioner
    }
}