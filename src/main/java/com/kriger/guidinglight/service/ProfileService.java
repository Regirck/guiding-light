package com.kriger.guidinglight.service;

import com.kriger.guidinglight.model.User;
import com.kriger.guidinglight.model.UserPersonalData;
import com.kriger.guidinglight.repository.UserPersonalDataRepository;
import com.kriger.guidinglight.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPersonalDataRepository userPersonalDataRepository;

    public User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean authenticated = auth.isAuthenticated();
        if (authenticated) {
            String loggedUserEmail = auth.getName();
            User userRepositoryByEmail = userRepository.findByEmail(loggedUserEmail);
            return userRepositoryByEmail;
        }
        return null;
    }

    public UserPersonalData getLoggedUserPersonalData(User user) {
        UserPersonalData userPersonalData = userPersonalDataRepository.findPersonalDataByUser(user);
        if (userPersonalData == null) {
            UserPersonalData build = UserPersonalData.builder()
                    .user(user)
                    .build();
            userPersonalDataRepository.save(build);
            return userPersonalDataRepository.findPersonalDataByUser(user);
        }
        return userPersonalData;
    }

}
