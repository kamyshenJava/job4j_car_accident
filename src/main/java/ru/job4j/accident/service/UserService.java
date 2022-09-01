package ru.job4j.accident.service;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> save(User user) {
        try {
            return Optional.of(userRepository.save(user));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }
}
