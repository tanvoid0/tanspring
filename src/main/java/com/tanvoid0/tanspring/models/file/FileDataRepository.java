package com.tanvoid0.tanspring.models.file;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileData, Long> {
  Optional<FileData> findByName(String fileName);

  boolean existsByName(String filename);

  void deleteByName(String filename);
}
