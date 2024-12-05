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
//@Profile("google") // This is a profile to activate this configuration
@Configuration
public class GoogleConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("google", r -> r.path("/google").filters(f -> f.rewritePath("/google(?<segment>/?.*)", "/${segment}")).uri("https://google.com"))
                .route("elmundo", r -> r.path("/espana").uri("http://elmundo.es"))
                .route("sprinter", r -> r.path("/sprinter").filters(f -> f.rewritePath("/(?<segment>/?.*)", "/${segment}")).uri("https://pass.sprintersports.com"))
                .build();
    }


}
