package com.jesusfc.gateway.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.UriSpec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

/**
 * Author Jesús Fdez. Caraballo
 * Created on abr - 2024
 */
@Configuration
public class LocalhostRouteConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {


        Function<GatewayFilterSpec, UriSpec> uriSpec = f -> f.circuitBreaker(c -> c.setName("service1_circuit_breaker").setFallbackUri("forward:/service-fail/*").setRouteId("service1"));

        return builder.routes()
                .route("service_1", r -> r.path("/service_1/*")
                        .filters(uriSpec)
                        .uri("lb://spb3-service-1"))
                .route("service_2", r -> r.path("/service_2/*").uri("lb://spb3-service-2"))
                .route("feign_service", r -> r.path("/feign-service/*").uri("lb://spb3-service-2"))
                .route("circuit_breaker", r -> r.path("/service-fail/*").uri("lb://spb3-circuit-breaker"))
                .build();

    }
}
