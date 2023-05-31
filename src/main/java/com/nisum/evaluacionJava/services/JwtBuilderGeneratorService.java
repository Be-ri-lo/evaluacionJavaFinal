package com.nisum.evaluacionJava.services;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;

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

}
