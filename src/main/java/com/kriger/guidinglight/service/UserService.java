package com.kriger.guidinglight.service;

import com.kriger.guidinglight.model.Role;
import com.kriger.guidinglight.model.User;
import com.kriger.guidinglight.model.UserPersonalData;
import com.kriger.guidinglight.repository.RoleRepository;
import com.kriger.guidinglight.repository.UserPersonalDataRepository;
import com.kriger.guidinglight.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.kriger.guidinglight.util.RegistrationAndLoginUtil.generateRandomActivationKey;


@Service
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final String USER_ROLE = "USER";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserDetailsImpl(user);
    }

    public User findByUserForTheId(long id) {
        return userRepository.findById(id);
    }

    public User findByUserForTheEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean changePassword(User user, User savedUser) {
        if (user.getActivation().equals(savedUser.getActivation())) {
            savedUser.setPassword(user.getPassword());
            savedUser.setActivation("");
            userRepository.save(savedUser);
            return true;
        }
        return false;
    }

    public boolean registerUser(User user) {
        User userCheck = userRepository.findByEmail(user.getEmail());

        if (userCheck != null) {
            return false;
        }

        Role userRole = roleRepository.findByRole(USER_ROLE);
        if (userRole != null) {
            user.getRoles().add(userRole);
        } else {
            user.addRoles(USER_ROLE);
        }

        user.setEnabled(false);

        String activationKey = createRandomUniqueActivationKeyForTheUser();
        user.setActivation(activationKey);

        userRepository.save(user);
        return true;
    }

    public User forgotPassword(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return null;
        }
        String activationKey = createRandomUniqueActivationKeyForTheUser();
        user.setActivation(activationKey);
        userRepository.save(user);
        return user;

    }

    private String createRandomUniqueActivationKeyForTheUser() {
        while (true) {
            String activationKey = generateRandomActivationKey();
            User activationKeyIsExistsOnDatabase = userRepository.findByActivation(activationKey);
            if (activationKeyIsExistsOnDatabase == null) {
                return activationKey;
            }
        }
    }


    public boolean userActivation(String code) {
        User user = userRepository.findByActivation(code);
        if (user == null)
            return false;

        user.setEnabled(true);
        user.setActivation("");

        Role userRole = roleRepository.findByRole(USER_ROLE);
        user.getRoles().add(userRole);

        userRepository.save(user);
        return true;
    }

}
