package com.nisum.evaluacionJava.domain;

import java.util.Objects;

public class JwtToken {
    private final String token;

    public JwtToken(String token) {
        if(token == null || token.trim().isEmpty()) throw new IllegalArgumentException();
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwtToken token1 = (JwtToken) o;
        return Objects.equals(token, token1.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }
}


