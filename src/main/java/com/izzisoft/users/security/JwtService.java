package com.izzisoft.users.security;

import com.izzisoft.users.model.MarketUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private static final String SECRET_KEY = "verySecretKeyverySecretKeyverySecretKeyverySecretKey";

    public String generateToken(MarketUser marketUser) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", marketUser.getRoles());

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(marketUser.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 300000))
                .and()
                .signWith(getSignKey())
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
