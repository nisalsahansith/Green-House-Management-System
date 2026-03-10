package lk.ijse.apigateway.dto;

public class LoginRequest {

    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;   
    }

    public void setPassword(String number) {
        this.password = password;
    }

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
}