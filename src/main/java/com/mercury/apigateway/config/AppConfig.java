package com.mercury.apigateway.config;

import com.mercury.apigateway.filter.AuthFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
//
//    @Bean
//    public RouteLocator routerLocator(RouteLocatorBuilder builder){
//        return builder.routes()
//                .route(r->r.path("/auth/**")
//                        .filters(f->f.filter(new AuthFilter().apply(new AuthFilter.Config())))
//                        .uri("lb://auth-service"))
//                .route(r->r.path("/**")
//                        .filters(f->f.filter(new AuthFilter().apply(new AuthFilter.Config())))
//                        .uri("lb://order-service"))
//                .build();
//
//    }

    @Bean
    public CorsWebFilter corsWebFilter(){
        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST","PUT","DELETE"));
        corsConfig.addAllowedHeader("*");

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
