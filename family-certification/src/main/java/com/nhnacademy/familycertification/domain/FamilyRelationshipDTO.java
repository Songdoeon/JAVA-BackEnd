package com.nhnacademy.familycertification.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FamilyRelationshipDTO {
    private Long familySerialNumber;
    private String familyRelationshipCode;
}