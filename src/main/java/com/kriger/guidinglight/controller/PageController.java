package com.kriger.guidinglight.controller;

import com.kriger.guidinglight.model.User;
import com.kriger.guidinglight.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        log.info("Open index page!");
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
        log.info(user.toString());
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String saveRegistration(@ModelAttribute("user") @Valid User user) {
        log.info("Save registration!");
        log.info(user.getEmail());
        userService.registerUser(user);
        return "auth/login";
    }

    @GetMapping("/activation/{code}")
    public String activation(@PathVariable("code") String code) {
        userService.userActivation(code);
        return "auth/login";
    }
}
