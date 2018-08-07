package br.com.db1.parser.security;

import br.com.db1.parser.dto.LoginResponseDTO;
import br.com.db1.parser.model.User;
import br.com.db1.parser.service.UserService;
import br.com.db1.parser.util.BeanUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class TokenAuthenticationService {

    private static final Log LOG = LogFactory.getLog(TokenAuthenticationService.class);

    private static final long EXPIRATION_TIME = 300_000; // 5 minutes
    private static final String SECRET = "Z3~v;af2bjuncHx@QTmmuvq!Mztar7%!@vge9enxb4miyfqbtset";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    private static UserService service = BeanUtil.getBean(UserService.class);

    private TokenAuthenticationService() {
        throw new IllegalStateException("Utility class");
    }

    public static void addAuthentication(HttpServletResponse response, String username) {
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            response.getWriter().write(new ObjectMapper().writeValueAsString(new LoginResponseDTO(token)));
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            LOG.error("Error trying to send login response.", e);
        }
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (Objects.nonNull(token)) {
            try {
                String user = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();

                Optional<User> usr = service.findUserByUsername(user);

                return usr.isPresent() ? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) : null;

            } catch (Exception e) {
                LOG.error("Error on token validation." + e.getMessage(), e);
                return null;
            }
        }
        return null;
    }

    public static String createToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
}
