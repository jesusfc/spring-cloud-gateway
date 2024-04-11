package com.jesusfc.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Author Jesús Fdez. Caraballo
 * Created on abr - 2024
 */
@Profile("google")
@Configuration
public class GoogleConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route", r -> r.path("/google").filters(f -> f.rewritePath("/google(?<segment>/?.*)", "/${segment}")).uri("http://localhost:8085"))
                .route("path_route", r -> r.path("/swagger-ui/**").uri("http://localhost:8085"))
                .route("elmundo", r -> r.path("/espana").uri("http://elmundo.es"))
                .build();
    }
}
