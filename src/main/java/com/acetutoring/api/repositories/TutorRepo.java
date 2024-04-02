package com.acetutoring.api.repositories;

import com.acetutoring.api.entities.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepo extends JpaRepository<Tutor, Long > {
}
