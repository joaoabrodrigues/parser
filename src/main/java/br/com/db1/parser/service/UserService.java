package br.com.db1.parser.service;

import br.com.db1.parser.model.User;
import br.com.db1.parser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Optional<User> findUserByUsername(String username) {
        return repository.findById(username);
    }

    public void createUser(User user) {
        repository.save(user);
    }

    public List<User> findAllUsers() {
        return repository.findAll();
    }
}
