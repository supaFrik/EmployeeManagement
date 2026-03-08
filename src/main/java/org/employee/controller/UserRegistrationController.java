package org.employee.controller;

import jakarta.validation.Valid;
import org.employee.dto.UserRegistrationDto;
import org.employee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @ModelAttribute
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "/registration";
    }

    @PostMapping
    public String registerUserAccount(@Valid @ModelAttribute("user") UserRegistrationDto userRegistrationDto, Model model) {
        userService.save(userRegistrationDto);
        return "/redirect:/registration?success";
    }
}
