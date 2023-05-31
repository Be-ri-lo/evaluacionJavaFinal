package com.nisum.evaluacionJava.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenTest {
    @Test
    @DisplayName("Test constructor - Valid token")
    public void constructor_ValidToken() {
        String token = "validToken";

        JwtToken jwtToken = new JwtToken(token);

        assertEquals(token, jwtToken.getToken());
    }

    @Test
    @DisplayName("Test constructor - Invalid token")
    public void constructor_InvalidToken() {
        String token = null;

        assertThrows(IllegalArgumentException.class, () -> new JwtToken(token));
    }

    @Test
    @DisplayName("Test equals - Same tokens")
    public void equals_SameTokens() {
        String token = "token";

        JwtToken jwtToken1 = new JwtToken(token);
        JwtToken jwtToken2 = new JwtToken(token);

        assertEquals(jwtToken1, jwtToken2);
    }

    @Test
    @DisplayName("Test equals - Different tokens")
    public void equals_DifferentTokens() {

        String token1 = "token1";
        String token2 = "token2";

        JwtToken jwtToken1 = new JwtToken(token1);
        JwtToken jwtToken2 = new JwtToken(token2);

        assertNotEquals(jwtToken1, jwtToken2);
    }
}