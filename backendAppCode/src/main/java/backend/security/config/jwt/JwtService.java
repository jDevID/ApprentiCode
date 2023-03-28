package backend.security.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
// JWT compact URL safe
// represents claims recorded as Json Objects
// 1) Header 2) Payload 3) Signature
// cfr http://www.jwt.io

// Header consists of: algo used, token type
// Payload contains the claims (data)
// Signature = verifies the sender

@Service
public class JwtService {
    // https://allkeysgenerator.com/Random/Security-Encryption-Key-Generator.aspx
    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }// last task after decoding token and extracting all claims

    private Claims extractAllClaims(String token) {  // Extract data from PayLoad
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey()) // needed to decode a token, secret used to insure privacy
                .build()                        // size 256 Minimal
                .parseClaimsJws(token)
                .getBody(); // Gotcha
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes); // one of them algo from Header, now all claims could be extracted
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    } // extractor Functional ------------  -------------

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) // my username/email
                .setIssuedAt(new Date(System.currentTimeMillis())) // when claim was created
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // roughly 24 Hrs validity
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    // What if I want to generate a Token from UserDetails itself and no more w/ extraClaims ?
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    } // done !

    // Token Validation ---------------------------------------------------------------------------------
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    // ---------------------------------------------------------------------------------------------------
}
