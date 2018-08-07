package br.com.db1.parser.security;

import br.com.db1.parser.model.User;
import br.com.db1.parser.service.UserService;
import br.com.db1.parser.util.BeanUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class AuthManager implements AuthenticationManager {

    private static final PasswordEncoder BCRYPT = new BCryptPasswordEncoder();
    private static UserService service = BeanUtil.getBean(UserService.class);

    @Override
    public Authentication authenticate(Authentication authentication) {
        UsernamePasswordAuthenticationToken credentials = (UsernamePasswordAuthenticationToken) authentication;

        Optional<User> optUser = service.findUserByUsername(credentials.getPrincipal().toString());

        User user = optUser.orElseThrow(() -> new UsernameNotFoundException("Username not found."));

        boolean pwdMatch = BCRYPT.matches(credentials.getCredentials().toString(), user.getPassword());

        if (!pwdMatch) {
            throw new BadCredentialsException("Password incorrect.");
        }

        return authentication;
    }
}
