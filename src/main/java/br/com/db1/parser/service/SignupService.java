package br.com.db1.parser.service;

import br.com.db1.parser.exception.BusinessException;
import br.com.db1.parser.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SignupService {

    private static final PasswordEncoder BCRYPT = new BCryptPasswordEncoder();

    @Autowired
    private UserService userService;

    public void createUser(User user) throws BusinessException {
        Optional<User> usr = userService.findUserByUsername(user.getUsername());

        if (usr.isPresent()) {
            throw new BusinessException("Username already exists.");
        }

        user.setPassword(BCRYPT.encode(user.getPassword()));
        userService.createUser(user);
    }
}
