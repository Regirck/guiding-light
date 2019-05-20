package com.kriger.guidinglight.controller;

import com.kriger.guidinglight.model.User;
import com.kriger.guidinglight.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class PageController {

    @Autowired
    private UserService userService;

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
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String saveRegistration(@ModelAttribute User user) {
        log.info(user.getEmail());
        return "auth/login";
    }
}
