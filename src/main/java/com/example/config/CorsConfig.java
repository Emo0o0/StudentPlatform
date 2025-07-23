package com.example.config;

import io.quarkus.vertx.http.runtime.filters.Filters;
import io.vertx.core.http.HttpMethod;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class CorsConfig {
    public void addCorsHandler(@Observes Filters filters) {
        filters.register(rc -> {
            rc.response()
                    .putHeader("Access-Control-Allow-Origin", "*")
                    .putHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                    .putHeader("Access-Control-Allow-Headers", "Content-Type, Authorization")
                    .putHeader("Access-Control-Allow-Credentials", "true");

            if (rc.request().method() == HttpMethod.OPTIONS) {
                rc.response().setStatusCode(200).end();
            } else {
                rc.next();
            }
        }, 1000);
    }
}
