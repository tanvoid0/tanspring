package com.tanvoid0.tanspring.models.user.career.certificate;

import com.tanvoid0.tanspring.models.user.career.Career;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findByCareer(Career career);
}
