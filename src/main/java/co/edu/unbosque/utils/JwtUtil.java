package co.edu.unbosque.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final SecretKey secretKey;
    private final long EXPIRATION_TIME = 3600000; // 1 hora

    public JwtUtil() {
        String base64Key = "miClaveSecretaSuperSegura1234567890ab"; // 32 caracteres
        this.secretKey = Keys.hmacShaKeyFor(base64Key.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username) {
        try {
            if (username == null || username.trim().isEmpty()) {
                throw new IllegalArgumentException("Username no puede ser nulo o vacío");
            }
            System.out.println("Generando token para: '" + username + "'"); // Log clave
            
            return Jwts.builder()
                    .setSubject(username.trim()) // Elimina espacios en blanco
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(secretKey, SignatureAlgorithm.HS256)
                    .compact();
        } catch (Exception e) {
            System.err.println("ERROR en generateToken(): " + e.getMessage());
            throw e; // Propaga el error real
        }
    }
}