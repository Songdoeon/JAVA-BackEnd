package com.nhnacademy.familycertification.repository;

import com.nhnacademy.familycertification.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BirthDeathReportResidentRepository extends JpaRepository<BirthDeathReportResident,BirthDeathReportResident.Pk> {

    @Query("select b from BirthDeathReportResident b where b.pk.reportResidentSerialNumber = ?1 and b.pk.residentSerialNumber=?2")
    Optional<BirthDeathReportResident> getBirthDeathReportResident(Long serialNumber,Long targetSerialNumber);

    BirthDeathReportResident findByResident_ResidentSerialNumberAndPk_BirthDeathTypeCode(Long number,String code);



}
