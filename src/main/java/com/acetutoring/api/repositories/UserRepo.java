package com.acetutoring.api.repositories;

import com.acetutoring.api.entities.Student;
import com.acetutoring.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.emailAddress = ?1")
    Optional<User> findByEmailAddress(String emailAddress);

    Optional<User> findByUserName(String username);

    Boolean existsByUserName(String userName);

    Boolean existsByEmailAddress(String emailAddress);

    Optional<User> findByUserNameOrEmailAddress(String userName, String emailAddress);
}
