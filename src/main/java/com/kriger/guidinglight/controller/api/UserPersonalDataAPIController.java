package com.kriger.guidinglight.controller.api;

import com.kriger.guidinglight.model.UserPersonalData;
import com.kriger.guidinglight.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class UserPersonalDataAPIController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/profile/get/{id}")
    public ResponseEntity<?> getAllUserPersonalData(@PathVariable("id") Long id) {
        UserPersonalData userPersonalData = profileService.getUserPersonalData(id);
        if (userPersonalData == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userPersonalData, HttpStatus.OK);
    }

}
