package br.com.carv.parking.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.logging.Logger;

@Component
public class JWTProvider {

    private final Logger logger = Logger.getLogger(JWTProvider.class.getName());

    @Value("${jwt.secret.key}")
    private String key;

    @Value("${jwt.expiration.milliseconds}")
    private Long expiration;

    private SecretKey generateKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
    }

    public String generateToken(Authentication authentication) {
        logger.info("Generating token...");
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expiredAt = new Date(currentDate.getTime() + expiration);

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expiredAt)
                .signWith(generateKey())
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().verifyWith(generateKey())
                .build().parseSignedClaims(token).getPayload().getSubject();
    }

    public Boolean isValid(String token) {
        try {
            Jwts.parser().verifyWith(generateKey()).build().parse(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new MalformedJwtException("Invalid JWT Token");
        }
    }
}
