package com.example.demo_test.services;

import com.example.demo_test.entities.User;
import com.example.demo_test.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        User createdUser = userRepository.save(user);
        return createdUser;
    }

    public List<User> readAll() {
        List<User> allUsers = userRepository.findAll();
        return allUsers;
    }

    public Optional<User> readById(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser;
    }

    public Optional<User> update(Long id, User user) {
        Optional<User> updateUser = userRepository.findById(id);
        if (updateUser.isEmpty()) {
            return Optional.empty();

        } else {
            updateUser.get().setName(user.getName());
            updateUser.get().setSurname(user.getSurname());
            updateUser.get().setActive(user.isActive());

            User responseUser = userRepository.save(updateUser.get());
            return Optional.of(responseUser);
        }
    }

    public Optional<User> updateWorking(Long id, boolean isActive) {
        Optional<User> updateUser = userRepository.findById(id);
        if (updateUser.isEmpty()) {
            return Optional.empty();

        } else {
            updateUser.get().setActive(isActive);

            User responseUser = userRepository.save(updateUser.get());
            return Optional.of(responseUser);
        }
    }

    public boolean delete(Long id) {
        boolean isThere = userRepository.existsById(id);
        if (isThere) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}