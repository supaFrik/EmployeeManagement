package org.employee.controller;

import jakarta.validation.Valid;
import org.employee.dto.UserRegistrationDto;
import org.employee.entity.User;
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
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String registrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "/registrationForm";
    }

    @PostMapping
    public String registerUserAccount(@Valid @ModelAttribute("user") UserRegistrationDto userRegistrationDto, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "/registrationForm";
        }
        userService.registerUser(userRegistrationDto);
        return "/login";
    }
}
