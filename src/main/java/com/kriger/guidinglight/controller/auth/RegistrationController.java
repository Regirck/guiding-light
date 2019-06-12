package com.kriger.guidinglight.controller.auth;

import com.kriger.guidinglight.model.User;
import com.kriger.guidinglight.service.EmailService;
import com.kriger.guidinglight.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;


    @GetMapping("/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String saveRegistration(@ModelAttribute("user") @Valid User user) {
        boolean userSaved = userService.registerUser(user);
        if (!userSaved) {
            return "redirect:/registration?error";
        }
        emailService.sendRegistrationMessage(user);
        return "redirect:/login?email_sent";
    }

    @GetMapping("/activation/{code}")
    public String activation(@PathVariable("code") String code) {
        boolean isActivation = userService.userActivation(code);
        if (!isActivation) {
            return "auth/login";
        }
        return "redirect:/login?activation";
    }

}
