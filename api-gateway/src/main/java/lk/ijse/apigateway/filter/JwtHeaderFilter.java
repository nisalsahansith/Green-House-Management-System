package lk.ijse.apigateway.filter;

import lk.ijse.apigateway.service.TokenVerificationService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class JwtHeaderFilter implements GatewayFilter {

    private final TokenVerificationService tokenService;

    public JwtHeaderFilter(TokenVerificationService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            Map<String, Object> claims = tokenService.verifyToken(token).block(); // blocking for demo, use Mono in production
            if (claims != null) {
                ServerWebExchange mutated = exchange.mutate()
                        .request(exchange.getRequest().mutate()
                                .header("X-User-Id", claims.get("sub").toString())
                                .header("X-User-Verified", claims.get("verified").toString())
                                .header("X-User-Roles", claims.get("roles").toString())
                                .build())
                        .build();
                return chain.filter(mutated);
            }
        }
        return chain.filter(exchange);
    }
}