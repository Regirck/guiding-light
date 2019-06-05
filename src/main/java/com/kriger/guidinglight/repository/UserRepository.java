package com.kriger.guidinglight.repository;

import com.kriger.guidinglight.model.User;
import com.kriger.guidinglight.model.UserPersonalData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);

    User findByEmail(String email);

    User findByActivation(String code);

}
