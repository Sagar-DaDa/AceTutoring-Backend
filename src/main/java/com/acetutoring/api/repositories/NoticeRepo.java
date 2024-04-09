package com.acetutoring.api.repositories;

import com.acetutoring.api.entities.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepo extends JpaRepository<Notice, Long> {
}
