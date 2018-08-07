package br.com.db1.parser.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

public class TokenAuthenticationService {

    private static final long EXPIRATION_TIME = 860_000_000; // 10 days
    private static final String SECRET = "MySecret";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse response, String username) {
        String JWT = Jwts.builder()
                         .setSubject(username)
                         .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                         .signWith(SignatureAlgorithm.HS512, SECRET)
                         .compact();

        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (Objects.nonNull(token)) {
            String user = Jwts.parser()
                              .setSigningKey(SECRET)
                              .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                              .getBody()
                              .getSubject();

            if (Objects.nonNull(user)) {
                return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            }
        }
        return null;
    }
}
