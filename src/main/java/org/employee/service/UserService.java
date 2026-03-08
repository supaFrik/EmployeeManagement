package org.employee.service;

import org.employee.dto.UserRegistrationDto;
import org.employee.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto userRegistrationDto);
}
