package com.nhnacademy.familycertification.repository;

import com.nhnacademy.familycertification.entity.CertificateIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long>,CertificateIssueRepositoryCustom{


}