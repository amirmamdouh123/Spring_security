package com.example.demo.Services;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final String key; // Load from a secure source

    public JwtService() {
        // Initialize key from a secure source
        this.key = "qj4iomr,249ptrj45t0934t5i095koptr,fporvkpieroskd;lfkioapfap9eri9t-w49t545t3905t93490t53905k034ktg5oprgp0i0tk-465y";
    }

    private Key getKey() {
        byte[] bytes = key.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String generateToken(String username, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100 * 60 * 60 * 24))
                .signWith(getKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(String username) {
        return generateToken(username, new HashMap<>());
    }

    public String extractUsername(String token) {
        return extractOneClaim(token, Claims::getSubject);
    }

    public Date extractExpirationDate(String token) {
        return extractOneClaim(token, Claims::getExpiration);
    }

    public boolean isExpired(String token) {
        return extractExpirationDate(token).before(new Date(System.currentTimeMillis()));
    }

    public boolean isNameValid(String token, String username) {
        String usernameCompared = extractUsername(token);
        return usernameCompared.equals(username);
    }

    public boolean TokenIsValid(String token, String username) {
        return !isExpired(token) && isNameValid(token, username);
    }

    private Claims extractAllClaims(String token) {
    Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }


    private <T> T extractOneClaim(String token, Function<Claims, T> oneClaim) {
           Claims claims= extractAllClaims(token);
    return oneClaim.apply(claims);
    }
}