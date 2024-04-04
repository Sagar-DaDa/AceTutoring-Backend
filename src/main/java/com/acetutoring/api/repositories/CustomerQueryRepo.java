package com.acetutoring.api.repositories;

import com.acetutoring.api.entities.CustomerQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerQueryRepo extends JpaRepository<CustomerQuery, Long> {
}
