package org.employee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    @GetMapping("/")
    public String root() {
        return "redirect:/employees/";
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm() {
        return "redirect:/employees/showNewEmployeeForm";
    }
}
