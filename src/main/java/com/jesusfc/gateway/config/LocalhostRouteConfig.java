package com.jesusfc.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author Jesús Fdez. Caraballo
 * Created on abr - 2024
 */
@Configuration
public class LocalhostRouteConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("service_1", r -> r.path("/service_1/*").uri("lb://spb3-service-1"))
                .route("service_2", r -> r.path("/service_2/*").uri("lb://spb3-service-2"))
                .route("feign_service", r -> r.path("/feign_service_1/*").uri("lb://spb3-service-2"))
                .build();

    }
}
