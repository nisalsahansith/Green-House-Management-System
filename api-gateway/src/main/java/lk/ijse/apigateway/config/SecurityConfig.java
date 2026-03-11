package lk.ijse.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/zone-management-service/**").authenticated()
                        .pathMatchers("/sensor-telemetry-service/**").authenticated()
                        .pathMatchers("/automation-control-service/**").authenticated()
                        .pathMatchers("/crop-inventory-service/**").authenticated()
                        .anyExchange().permitAll() // for other endpoints
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> {}) // JWT validation
                );

        return http.build();
    }
}