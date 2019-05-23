package com.kriger.guidinglight.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class PageController {

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
