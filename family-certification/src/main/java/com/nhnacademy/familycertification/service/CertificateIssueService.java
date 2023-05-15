package com.nhnacademy.familycertification.service;

import com.nhnacademy.familycertification.domain.CertificateIssueDTO;
import com.nhnacademy.familycertification.repository.CertificateIssueRepository;
import com.nhnacademy.familycertification.repository.CertificateIssueRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CertificateIssueService{

    private final CertificateIssueRepository certificateIssueRepository;


    public CertificateIssueDTO getCertificateInfoByResidentSerialNumber(Long certificateConfirmationNumber, String typeCode) {
        return certificateIssueRepository.getCertificateInfoByResidentSerialNumber(certificateConfirmationNumber, typeCode);
    }
}