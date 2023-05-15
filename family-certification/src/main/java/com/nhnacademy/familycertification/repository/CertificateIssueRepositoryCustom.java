package com.nhnacademy.familycertification.repository;

import com.nhnacademy.familycertification.domain.CertificateIssueDTO;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CertificateIssueRepositoryCustom {
    CertificateIssueDTO getCertificateInfoByResidentSerialNumber(Long serialNumber, String typeCode);
}