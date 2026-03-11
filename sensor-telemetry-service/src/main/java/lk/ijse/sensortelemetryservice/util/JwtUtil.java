package lk.ijse.sensortelemetryservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtUtil {

    private static final String SECRET =
            "mySuperSecretJwtKeyForAGMSProject12345"; // 32+ chars

    private static final Key KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String extractUserId(String token){

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}