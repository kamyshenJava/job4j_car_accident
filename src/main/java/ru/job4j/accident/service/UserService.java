package ru.job4j.accident.service;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        try {
            return userRepository.save(user);
        } catch (DataAccessException e) {
            return null;
        }
    }
}
