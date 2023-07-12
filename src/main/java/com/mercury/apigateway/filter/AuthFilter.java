package com.mercury.apigateway.filter;

import com.mercury.apigateway.DataResponse;
import com.mercury.apigateway.Response;
import com.mercury.apigateway.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    @Autowired
    private RouteValidator validator;
    @Autowired
    private RestTemplate restTemplate;

    public AuthFilter() {
        super(Config.class);
    }
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authentication header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
//                    //REST call to AUTH service
//                    HttpHeaders headers = new HttpHeaders();
//                    headers.set(HttpHeaders.AUTHORIZATION, authHeader);
//                    HttpEntity<String> entity = new HttpEntity<>(headers);
//
//                    ResponseEntity<DataResponse> response = restTemplate.exchange(
//                            "http://localhost:8082/auth/checklogin", HttpMethod.GET, entity, DataResponse.class);
//
//                    DataResponse dataResponse = response.getBody();
                    restTemplate.getForObject("http://localhost:8082/auth/validate?token=" + authHeader, String.class);
//                    System.out.println("AuthFilter-------------- "+dataResponse);
//                    if (dataResponse.isSuccess() == false) {
//                        throw new Exception();
//                    }
//                    exchange.getAttributes().put("user", dataResponse.getData());
//                    jwtUtil.validateToken(authHeader);

                } catch (Exception e) {
                    System.out.println("invalid access...!");
                    throw new RuntimeException("un authenticated access to application");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}
