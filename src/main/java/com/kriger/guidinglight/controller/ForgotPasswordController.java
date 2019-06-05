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
public class ForgotPasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;


    @GetMapping("/forgot_password")
    public String forgotPassword() {
        return "auth/forgot-password";
    }

    @PostMapping("/forgot_password")
    public String sendForgotPasswordEmail(@RequestParam String email) {
        User user = userService.forgotPassword(email);
        if (user == null) {
            return "redirect:/login?forgotpassworderror";
        }
        emailService.sendForgotPasswordMessage(user);
        return "redirect:/login?forgotpassword";
    }

    @GetMapping("/password_recovery/{email}&{code}")
    public String passwordRecovery(@PathVariable("email") String email,
                                   @PathVariable("code") String code,
                                   Model model) {
        User user = userService.findByUserForTheEmail(email);
        if (user == null) {
            return "redirect:/login?passwordrecoveryerror";
        }
        if (!user.getActivation().equals(code)) {
            return "redirect:/login?passwordrecoveryerror";
        }
        model.addAttribute(user);
        log.info(user.getActivation());
        return "auth/password-recovery";
    }

    @PostMapping("/password_recovery")
    public String changePasswordFromTheRecovery(@ModelAttribute("user") User user){
        log.info(String.valueOf(user.getId()));
        log.info(user.getActivation());
        log.info(user.getPassword());
        return "auth/login";
    }
}
