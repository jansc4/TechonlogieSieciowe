package edu.ib.technologiebyadamski.service;

import edu.ib.technologiebyadamski.commonTypes.Role;
import edu.ib.technologiebyadamski.infrastructure.entity.AuthEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private long tokenLifetime = 1000 * 60 * 60;    //token ma ważność 24 min
    @Value("${token.signing.key}")
    private String  jwtSigningKey;

    public String generateToken(AuthEntity userDetail){
        return generateToken(new HashMap<>(), userDetail);
    }
    public boolean isTokenValid(String token){
        try{
            return !isTokenExpired(token);
        } catch (Exception e){
            return false;
        }

    }
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public Role extractRole(String token){
        String roleString = extractClaim(token, (claims) -> claims.get("role", String.class));
        return Role.valueOf(roleString);
    }
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T>  T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extraxtAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extraxtAllClaims(String token){
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }

    private String generateToken(Map<String, Object> extraClains, AuthEntity userDetails){
        extraClains.put("role", userDetails.getRole());
        return Jwts.builder()
                .claims(extraClains)
                .subject(userDetails.getUserName())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tokenLifetime))
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
