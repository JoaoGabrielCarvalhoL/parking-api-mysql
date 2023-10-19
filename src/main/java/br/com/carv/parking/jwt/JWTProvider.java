package br.com.carv.parking.jwt;

import br.com.carv.parking.exception.ParkingJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Value;
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
        return Keys.hmacShaKeyFor(DatatypeConverter.parseBase64Binary(key));
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
                .signWith(generateKey(), Jwts.SIG.HS256)
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
        } catch (ExpiredJwtException ex) {
            throw new ParkingJwtException("Expired JWT Token");
        } catch (UnsupportedJwtException ex) {
            throw new ParkingJwtException("Unsupported JWT Token");
        } catch (IllegalArgumentException ex) {
            throw new ParkingJwtException("Invalid Argument. Token must be not empty and non null.");
        }
    }
}
