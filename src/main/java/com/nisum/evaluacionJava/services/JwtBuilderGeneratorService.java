package com.nisum.evaluacionJava.services;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

@Component
public class JwtBuilderGeneratorService {
    /*public String generateToken(String username) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.ES256;
        SecretKey secretKey = MacProvider.generateKey(SignatureAlgorithm.HS256);

        HashMap<String, Object> header = new HashMap<>();
        header.put("alg", signatureAlgorithm.toString());
        header.put("typ", "JWT");

        JwtBuilder tokenJWT = Jwts.builder()
                .setHeader(header)
                .setSubject("nisum")
                .claim("username", username)
                .setIssuedAt(Date.from(Instant.ofEpochSecond(1466796822L)))
                .signWith(signatureAlgorithm, secretKey);

        return tokenJWT.toString();
    }*/

    public String generateToken(String username) {
        SecretKey key = MacProvider.generateKey(SignatureAlgorithm.HS256);
        String jws = Jwts.builder().setSubject("Bea").signWith(SignatureAlgorithm.ES256, key).compact();
        return jws;
    }
}
