package com.example.api_web_ban_hang.models;


public class AuthResponse {
    private String username;
    private String accessToken;

    private String expires_in;

    public AuthResponse() { }

    public AuthResponse(String username, String accessToken, String expires_in) {
        this.username = username;
        this.accessToken = accessToken;
        this.expires_in = expires_in;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
}
