package com.nhnacademy.familycertification.service;

import com.nhnacademy.familycertification.domain.CertificateIssueDTO;
import com.nhnacademy.familycertification.entity.CertificateIssue;
import com.nhnacademy.familycertification.repository.CertificateIssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class CertificateIssueService{

    private final CertificateIssueRepository certificateIssueRepository;


    public CertificateIssueDTO getCertificateInfoByResidentSerialNumber(Long certificateConfirmationNumber, String typeCode) {
        return certificateIssueRepository.getCertificateInfoByResidentSerialNumber(certificateConfirmationNumber, typeCode);
    }

//    public List<CertificateIssue> getList(Long id){
//        return certificateIssueRepository.getResident(id);
//    }

    public Page paging(Long id,Pageable pageable){
        return certificateIssueRepository.findByResident_ResidentSerialNumber(id,pageable);

    }
}