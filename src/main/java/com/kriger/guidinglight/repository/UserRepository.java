package com.kriger.guidinglight.repository;

import com.kriger.guidinglight.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
