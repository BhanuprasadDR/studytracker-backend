package com.example.st.payload;



public class JwtResponse {

    private String token;
    private String username;
    private String name;

    public JwtResponse(String token, String username, String name) {
        this.token = token;
        this.username = username;
        this.name = name;
    }

    public String getToken() { return token; }
    public String getUsername() { return username; }
    public String getName() { return name; }
}
