package pl.adamd.crm.api.config.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pl.adamd.crm.api.config.employee.details.EmployeeDetailsImpl;

import java.util.Date;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class JwtUtils {

    private static final Logger logger = getLogger(JwtUtils.class);

    @Value("${jwt.expiration.ms}")
    private int jwtExpirationMs;
    @Value("${jwt.secret}")
    private String jwtSecret;

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public String generateJwtToken(Authentication authentication) {
        EmployeeDetailsImpl employeePrincipal = (EmployeeDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                   .setSubject((employeePrincipal.getUsername()))
                   .setIssuedAt(new Date())
                   .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                   .signWith(SignatureAlgorithm.HS512, jwtSecret)
                   .compact();
    }

    public String geEmployeeFromJwtToken(String token) {
        return Jwts.parser()
                   .setSigningKey(jwtSecret)
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
    }

}

