package com.mercury.apigateway.filter;

import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openEndpoints = List.of(
            "/auth/register",
            "/auth/login",
            "/auth/admin/register",
            "/auth/admin/login",
            "/auth/checklogin",
            "/auth/admin/checklogin"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri)
                            || (request.getURI().getPath().contains("/products") && request.getMethod().equals(HttpMethod.GET)));
}
