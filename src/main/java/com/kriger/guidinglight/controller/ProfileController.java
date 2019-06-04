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

    @ModelAttribute
    public boolean addUserPersonalData(Model model) {
        User loggedUser = profileService.getLoggedUser();
        if (loggedUser == null) {
            return false;
        }
        UserPersonalData profile = profileService.getLoggedUserPersonalData(loggedUser);
        if (profile == null) {
            return false;
        }
        model.addAttribute("profile", profile);
        return true;
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        boolean isExists = addUserPersonalData(model);
        if (!isExists) {
            return "auth/login";
        }
        return "profile";
    }

    @PostMapping("/profile")
    public String saveProfile(@ModelAttribute("profile") @Valid UserPersonalData profile) {
        profileService.saveUserPersonalData(profile);
        return "redirect:/";
    }

}
