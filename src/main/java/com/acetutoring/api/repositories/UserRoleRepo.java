package com.acetutoring.api.repositories;

import com.acetutoring.api.entities.Student;
import com.acetutoring.api.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRoleRepo extends JpaRepository<UserRole, Long> {
    @Query("SELECT r FROM UserRole r WHERE r.roleName = ?1")
    Optional<UserRole> findByRoleName(String roleName);
}
