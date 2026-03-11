package lk.ijse.apigateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UserIdHeaderFilter extends AbstractGatewayFilterFactory<UserIdHeaderFilter.Config> {

    public UserIdHeaderFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Add custom header to the request
            exchange.getRequest()
                    .mutate()
                    .header("X-User-Id", config.userId)
                    .build();
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // Optional: post-processing after response
            }));
        };
    }

    // Optional configuration class to allow dynamic userId in YAML
    public static class Config {
        private String userId = "default-user"; // default value

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}