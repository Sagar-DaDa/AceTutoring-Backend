package com.acetutoring.api.repositories;

import com.acetutoring.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
