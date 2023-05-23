package com.nhnacademy.familycertification.repository;

import com.nhnacademy.familycertification.entity.CertificateIssue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long>,CertificateIssueRepositoryCustom{

//    @Query("SELECT c from CertificateIssue c where Resident.residentSerialNumber=?1")
//    List<CertificateIssue> getResident(Long id);

    Page<CertificateIssue> findByResident_ResidentSerialNumber(Long id,Pageable pageable);

}