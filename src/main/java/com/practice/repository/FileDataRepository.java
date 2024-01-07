package com.practice.repository;

import com.practice.entity.FileSystemData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileSystemData, Long> {

    Optional<FileSystemData> findByName(String fileName);
}
