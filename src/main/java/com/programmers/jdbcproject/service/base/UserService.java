package com.programmers.jdbcproject.service.base;

import com.programmers.jdbcproject.domain.User;
import com.programmers.jdbcproject.repository.UserRepository;

import java.util.List;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(String nickname) {
        userRepository.save(nickname);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public void delete(int id) {
        userRepository.delete(id);
    }
}
