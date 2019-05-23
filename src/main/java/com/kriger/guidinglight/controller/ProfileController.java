package com.kriger.guidinglight.controller;

import com.kriger.guidinglight.model.User;
import com.kriger.guidinglight.model.UserPersonalData;
import com.kriger.guidinglight.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/profile")
    public String userProfile(Model model) {
        User loggedUser = profileService.getLoggedUser();
        if (loggedUser == null) {
            return "auth/login";
        }
        UserPersonalData loggedUserPersonalData = profileService.getLoggedUserPersonalData(loggedUser);
        if (loggedUserPersonalData == null) {
            System.out.println("This is null");
            return "auth/login";
        }
        log.info(loggedUserPersonalData.getFirstName());
        model.addAttribute("personal", loggedUserPersonalData);
        return "profile";
    }

}
