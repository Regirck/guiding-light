package com.kriger.guidinglight.repository;

import com.kriger.guidinglight.model.User;
import com.kriger.guidinglight.model.UserPersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserPersonalDataRepository extends JpaRepository<UserPersonalData, Long> {

    @Query(value = "SELECT u FROM UserPersonalData u WHERE u.user = :user")
    UserPersonalData findPersonalDataByUser(@Param("user") User user);

}
