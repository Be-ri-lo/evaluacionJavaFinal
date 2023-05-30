package com.nisum.evaluacionJava.services;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class JwtBuilderGeneratorService {

    public String generateToken(String username) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Key secretKey = MacProvider.generateKey(SignatureAlgorithm.HS256);

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        HashMap<String, Object> header = new HashMap<>();
        header.put("alg", signatureAlgorithm.toString());
        header.put("typ", "JWT");

        JwtBuilder builder = Jwts.builder()
                .setHeader(header)
                .setIssuedAt(now)
                .setSubject(username)
                .setIssuer("nissum")
                .signWith(signatureAlgorithm, secretKey);

        return builder.compact();
    }

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
                .setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
                .signWith(signatureAlgorithm, secretKey);

        return tokenJWT.compact();
    }*/
}
