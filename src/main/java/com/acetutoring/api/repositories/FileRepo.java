package com.acetutoring.api.repositories;

import com.acetutoring.api.entities.AceFile;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FileRepo extends JpaRepository<AceFile, Long> {
    @Query("SELECT af FROM AceFile af ORDER BY af.createdAt DESC")
    Optional<AceFile> findLatestFile();

    Optional<AceFile> findByName(String name);

    @Transactional
    void deleteByName(String name);
}
