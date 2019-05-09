package com.kriger.guidinglight.controller;

import com.kriger.guidinglight.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

}
