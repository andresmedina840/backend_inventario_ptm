package co.com.ptm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    
    @Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                            "http://localhost:3000","http://localhost:3001"
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("Authorization", "Content-Type", "Accept")
                        .exposedHeaders("Content-Disposition")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}