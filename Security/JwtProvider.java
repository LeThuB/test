package TechMini.Security;

import TechMini.Model.Entity.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
   @Value("${jwt.secret}")
    private String secret;
@Value("${jwt.access-token-expiration}")
    private long accessToken;

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
    //tao token
public String generateAccessToken(User user){
   Date now = new Date();
   Date accessDate = new Date(now.getTime()+ accessToken );
   return Jwts.builder()
           .subject(user.getEmail())
           .claim("role",user.getRole().name())
           .issuedAt(now)
           .expiration(accessDate)
           .signWith(getSigningKey())
           .compact();
}

//kiem tra token
    public boolean validToken(String token){
try {
    Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token);
            return true;

}
       // JwtException lpo toke khong hop le
//IllegalArgumentException loi token null hoac rong
catch (JwtException | IllegalArgumentException e) {
    return false;
}
    }

    public String getUsernameForomToken(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build().parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}

