package com.example.st.security;



import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String principal; // username

    public JwtAuthenticationToken(String principal) {
        super(Collections.emptyList());
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return ""; // not used
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
