package org.employee.service;

import org.employee.dto.UserRegistrationDto;
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

    public User registerUser(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        if(userRepository.existsByEmail(userRegistrationDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        user.setEmail(userRegistrationDto.getEmail());

        String encodedPassword = passwordEncoder.encode(userRegistrationDto.getPassword());
        user.setPassword(encodedPassword);
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
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
