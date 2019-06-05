package com.kriger.guidinglight.controller;

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
    public String registration(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String saveRegistration(@ModelAttribute("user") @Valid User user) {
        boolean userSaved = userService.registerUser(user);
        if (!userSaved) {
            return "redirect:/login?error";
        }
        emailService.sendRegistrationMessage(user);
        return "auth/login";
    }

    @GetMapping("/activation/{code}")
    public String activation(@PathVariable("code") String code) {
        boolean isActivation = userService.userActivation(code);
        if (!isActivation) {
            return "auth/login";
        }
        return "redirect:/login?activation";
    }

    //TODO forgot password route and business logic

    @GetMapping("/forgot_password")
    public String forgotPassword() {
        return "auth/forgot-password";
    }

    @PostMapping("/forgot_password")
    public String sendForgotPasswordEmail(@RequestParam String email){
        User user = userService.forgotPassword(email);
        if (user == null) {
            return "redirect:/login?forgotpassworderror";
        }
        log.info(user.getEmail());
        return "redirect:/login?forgotpassword";
    }

}
