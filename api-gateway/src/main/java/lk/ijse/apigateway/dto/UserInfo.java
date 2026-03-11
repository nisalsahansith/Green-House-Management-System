package lk.ijse.apigateway.dto;

public class UserInfo {
    private String userId;
    private String email;
    private String[] roles;

    // Getters & Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String[] getRoles() { return roles; }
    public void setRoles(String[] roles) { this.roles = roles; }
}