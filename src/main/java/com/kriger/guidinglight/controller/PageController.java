package com.kriger.guidinglight.controller;

import com.kriger.guidinglight.model.User;
import com.kriger.guidinglight.service.EmailService;
import com.kriger.guidinglight.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.annotation.SessionScope;

import javax.validation.Valid;

@Slf4j
@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/tutorials")
    public String tutorials() {
        return "not_supported";
    }

    @GetMapping("/tests")
    public String tests() {
        return "not_supported";
    }

    @GetMapping("/forum")
    public String forum() {
        return "not_supported";
    }

    @GetMapping("/about")
    public String about() {
        return "not_supported";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String saveRegistration(@ModelAttribute("user") @Valid User user) {
        boolean userSaved = userService.registerUser(user);
        if (!userSaved) {
            return "redirect:/login?error";
        }
        emailService.sendMessage(user);
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
}
