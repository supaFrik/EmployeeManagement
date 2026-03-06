package org.employee.service;

import org.employee.entity.Role;
import org.employee.entity.User;
import org.employee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String email, String password, String firstName, String lastName) {
        User user = new User();
        if(userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }
        user.setEmail(email);

        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return userRepository.save(user);
    }
    public Optional<User> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User assignRoleToUser(User user, Role role) {
        user.getRoles().add(role);
        return userRepository.save(user);
    }
}
