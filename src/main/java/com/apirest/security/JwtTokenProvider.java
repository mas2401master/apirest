package com.apirest.security;

import com.apirest.exception.ApiException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date current = new Date();
        Date expiration = new Date(current.getTime() + jwtExpirationInMs);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

        return token;
    }

    public String getUsernameToJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex) {
            throw new ApiException(HttpStatus.BAD_REQUEST,"Invalid JWT signature");
        }
        catch (MalformedJwtException ex) {
            throw new ApiException(HttpStatus.BAD_REQUEST,"JWT token not valid");
        }
        catch (ExpiredJwtException ex) {
            throw new ApiException(HttpStatus.BAD_REQUEST,"Expired JWT Token");
        }
        catch (UnsupportedJwtException ex) {
            throw new ApiException(HttpStatus.BAD_REQUEST,"JWT token not supported");
        }
        catch (IllegalArgumentException ex) {
            throw new ApiException(HttpStatus.BAD_REQUEST,"JWT claims string is empty");
        }
    }


}
