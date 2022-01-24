package cqrs.microservice.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Configuration
public class FallbackConfig {
    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions
                .route(RequestPredicates.GET("/queryFallBack"),
                        this::handleGetFallback)
                .andRoute(RequestPredicates.POST("/commandFallBack"),
                        this::handlePostFallback);
    }

    public Mono<ServerResponse> handleGetFallback(ServerRequest request) {
        return ServerResponse.ok().body(Mono.just("The query service seems unavailable"), String.class);
    }

    public Mono<ServerResponse> handlePostFallback(ServerRequest request) {
        return ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }
}

