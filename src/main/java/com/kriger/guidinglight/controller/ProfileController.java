package com.kriger.guidinglight.controller;

import com.kriger.guidinglight.model.User;
import com.kriger.guidinglight.model.UserPersonalData;
import com.kriger.guidinglight.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/profile")
    public String profile(Model model) {
        User loggedUser = profileService.getLoggedUser();
        if (loggedUser == null) {
            return "auth/login";
        }
        UserPersonalData profile = profileService.getLoggedUserPersonalData(loggedUser);
        if (profile == null) {
            return "auth/login";
        }
        model.addAttribute("profile", profile);
        return "profile";
    }

    @PostMapping("/profile")
    public String saveProfile(@ModelAttribute("profile") @Valid UserPersonalData profile) {
        log.info(profile.getFirstName());
        log.info(profile.getLastName());
        return "redirect:/";
    }

}
