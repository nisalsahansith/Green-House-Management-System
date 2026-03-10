package lk.ijse.apigateway.controller;

import lk.ijse.apigateway.dto.AuthResponse;
import lk.ijse.apigateway.dto.LoginRequest;
import lk.ijse.apigateway.dto.RefreshRequest;
import lk.ijse.apigateway.dto.RegisterRequest;
import lk.ijse.apigateway.service.IotAuthService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IotAuthService authService;

    public AuthController(IotAuthService authService) {
        this.authService = authService;
    }

    // REGISTER USER
    @PostMapping("/register")
    public Mono<AuthResponse> register(@RequestBody RegisterRequest request) {

        return authService.register(
                request
        );
    }

    // LOGIN USER
    @PostMapping("/login")
    public Mono<AuthResponse> login(@RequestBody LoginRequest request) {

        return authService.login(
                request
        );
    }

    @PostMapping("/refresh")
    public Mono<AuthResponse> refresh(@RequestBody RefreshRequest request) {
        return authService.refreshToken(request.getRefreshToken());
    }
}