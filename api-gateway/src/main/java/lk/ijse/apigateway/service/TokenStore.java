package lk.ijse.apigateway.service;

import lk.ijse.apigateway.dto.AuthResponse;
import org.springframework.stereotype.Component;

@Component
public class TokenStore {

    private String accessToken;
    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setTokens(AuthResponse response){
        this.accessToken = response.getAccessToken();
        this.refreshToken = response.getRefreshToken();
    }

}