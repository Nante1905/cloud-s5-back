package com.cloud.voiture.services.authentication;

import java.security.Key;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.cloud.voiture.models.auth.Utilisateur;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTManager {
    private static final String secret = "DvxMWzlQ2d6zSQ77EseNcGI1x0hhpCVJwtXBIx4c7uUlDSSRCD4kBXFyzEY2zLdN";
    private static final Key key = new SecretKeySpec(Base64.getDecoder().decode(secret),
            SignatureAlgorithm.HS256.getJcaName());

    public String generateToken(Utilisateur user,
            Collection<? extends GrantedAuthority> authorities) {
        Date currentDate = new Date();
        String auths = authorities.stream().map(a -> a.getAuthority()).toList().toString();
        auths = auths.substring(1, auths.length() - 1);

        String token = Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(currentDate)
                .setExpiration(new Date(currentDate.getTime() + dayToMs(1)))
                .claim("email", user.getEmail())
                .claim("id", user.getId())
                .claim("authorization", auths)
                .signWith(key)
                .compact();
        return token;
    }

    public String getEmail(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("email", String.class);
    }

    public boolean validateToken(String token)
            throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException {

        Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        return true;

    }

    private long dayToMs(long day) {
        return 1000 * day * 24 * 60 * 60;
    }
}
