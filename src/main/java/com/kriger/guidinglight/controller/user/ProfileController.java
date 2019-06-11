package com.kriger.guidinglight.controller.user;

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
    public void addUserPersonalData(Model model) {
        User loggedUser = profileService.getLoggedUser();
        UserPersonalData profile = profileService.getLoggedUserPersonalData(loggedUser);
        model.addAttribute("profile", profile);
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        if (model == null) {
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
