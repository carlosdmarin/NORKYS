package com.senati.NORKYS;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// @Configuration esta clase no es una clase normal,
// en esta clase hacemos la configuración para mi aplicación.
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    //este método que estoy escribiendo no es nuevo, estoy sobrescribiendo
    //(reemplazando) un método que ya existe en una interfaz o en una clase padre.”
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //allowedOrigins → Solo permite peticiones desde localhost:5500
                // (el puerto que usa Live Server de VS Code)
                .allowedOrigins("http://127.0.0.1:5500", "http://localhost:5500")
                //allowedMethods → Permite usar los verbos más comunes (GET, POST, PUT, DELETE)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                //
                .allowedHeaders("*");
    }
}
