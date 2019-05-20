package com.kriger.guidinglight.service;

import com.kriger.guidinglight.model.Role;
import com.kriger.guidinglight.model.User;
import com.kriger.guidinglight.repository.RoleRepository;
import com.kriger.guidinglight.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
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

    public boolean registerUser(User userToRegister) {
        User userCheck = userRepository.findByEmail(userToRegister.getEmail());

        if (userCheck != null)
            return false;

        Role userRole = roleRepository.findByRole(USER_ROLE);
        if (userRole != null) {
            userToRegister.getRoles().add(userRole);
        } else {
            userToRegister.addRoles(USER_ROLE);
        }

        userToRegister.setEnabled(false);
        userToRegister.setActivation(generateKey());
        userRepository.save(userToRegister);

        return true;
    }

    public String generateKey() {
        Random random = new Random();
        char[] word = new char[16];
        for (int j = 0; j < word.length; j++) {
            word[j] = (char) ('a' + random.nextInt(26));
        }
        return new String(word);
    }

    public boolean userActivation(String code) {
        User user = userRepository.findByActivation(code);
        if (user == null)
            return false;

        user.setEnabled(true);
        user.setActivation("");
        userRepository.save(user);
        return true;
    }

}
