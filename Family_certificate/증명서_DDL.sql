
-- -----------------------------------------------------
-- Table `mydb`.`Address_state`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Address_state` (
  `Address_state_id` BIGINT NOT NULL,
  `state_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Address_state_id`))
ENGINE = InnoDB;

USE `myfamily` ;

-- -----------------------------------------------------
-- Table `myfamily`.`Address_list`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`Address_list` (
  `Address_id` BIGINT NOT NULL,
  `Content` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Address_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myfamily`.`Family_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`Family_type` (
  `Family_type_id` BIGINT NOT NULL,
  `Family_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Family_type_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myfamily`.`Family_tie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`Family_tie` (
  `Family_tie_id` BIGINT NOT NULL,
  `myself` BIGINT NOT NULL,
  `target` BIGINT NOT NULL,
  `Family_type_id` BIGINT NOT NULL,
  PRIMARY KEY (`Family_tie_id`),
  INDEX `fk_Family_tie_User1_idx` (`myself` ASC) VISIBLE,
  INDEX `fk_Family_tie_User2_idx` (`target` ASC) VISIBLE,
  INDEX `fk_Family_tie_Family_type_idx` (`Family_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_Family_tie_Family_type`
    FOREIGN KEY (`Family_type_id`)
    REFERENCES `myfamily`.`Family_type` (`Family_type_id`),
  CONSTRAINT `fk_Family_tie_User1`
    FOREIGN KEY (`myself`)
    REFERENCES `myfamily`.`User` (`User_id`),
  CONSTRAINT `fk_Family_tie_User2`
    FOREIGN KEY (`target`)
    REFERENCES `myfamily`.`User` (`User_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myfamily`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`User` (
  `User_id` BIGINT NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Birth` VARCHAR(45) NOT NULL,
  `Gender` CHAR(1) NOT NULL,
  `Resident_registration_number` VARCHAR(300) NOT NULL,
  `Death_or_live` CHAR(1) NOT NULL,
  `Address_id` BIGINT NOT NULL,
  `Family_tie_id` BIGINT NOT NULL,
  PRIMARY KEY (`User_id`),
  UNIQUE INDEX `User_id_UNIQUE` (`User_id` ASC) VISIBLE,
  INDEX `fk_User_Address_list1_idx` (`Address_id` ASC) VISIBLE,
  INDEX `fk_User_Family_tie_idx` (`Family_tie_id` ASC) VISIBLE,
  CONSTRAINT `fk_User_Address_list1`
    FOREIGN KEY (`Address_id`)
    REFERENCES `myfamily`.`Address_list` (`Address_id`),
  CONSTRAINT `fk_User_Family_tie`
    FOREIGN KEY (`Family_tie_id`)
    REFERENCES `myfamily`.`Family_tie` (`Family_tie_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myfamily`.`Birth_Location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`Birth_Location` (
  `Location_id` BIGINT NOT NULL,
  `Location_name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`Location_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myfamily`.`birth_qualification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`birth_qualification` (
  `qualification_id` BIGINT NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`qualification_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myfamily`.`Birth_Information`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`Birth_Information` (
  `Birth_information_id` BIGINT NOT NULL,
  `Birth_date` VARCHAR(45) NOT NULL,
  `Birth_address` BIGINT NOT NULL,
  `User_User_id` BIGINT NOT NULL,
  `Birth_Location_Location_id` BIGINT NOT NULL,
  `birth_qualification_qualification_id` BIGINT NOT NULL,
  PRIMARY KEY (`Birth_information_id`),
  INDEX `fk_Birth_Information_User1_idx` (`User_User_id` ASC) VISIBLE,
  INDEX `fk_Birth_Information_Birth_Location1_idx` (`Birth_Location_Location_id` ASC) VISIBLE,
  INDEX `fk_Birth_Information_birth_qualification1_idx` (`birth_qualification_qualification_id` ASC) VISIBLE,
  CONSTRAINT `fk_Birth_Information_User1`
    FOREIGN KEY (`User_User_id`)
    REFERENCES `myfamily`.`User` (`User_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Birth_Information_Birth_Location1`
    FOREIGN KEY (`Birth_Location_Location_id`)
    REFERENCES `myfamily`.`Birth_Location` (`Location_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Birth_Information_birth_qualification1`
    FOREIGN KEY (`birth_qualification_qualification_id`)
    REFERENCES `myfamily`.`birth_qualification` (`qualification_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myfamily`.`death_qualification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`death_qualification` (
  `qualification_id` BIGINT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`qualification_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myfamily`.`Death_Location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`Death_Location` (
  `Location_id` BIGINT NOT NULL,
  `Location_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Location_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myfamily`.`Death_Information`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`Death_Information` (
  `Death_information_id` BIGINT NOT NULL,
  `Death_date` VARCHAR(45) NOT NULL,
  `Death_address_` BIGINT NOT NULL,
  `User_User_id` BIGINT NOT NULL,
  `death_qualification_qualification_id` BIGINT NOT NULL,
  `Death_Location_Location_id` BIGINT NOT NULL,
  PRIMARY KEY (`Death_information_id`),
  INDEX `fk_Death_Information_User1_idx` (`User_User_id` ASC) VISIBLE,
  INDEX `fk_Death_Information_death_qualification1_idx` (`death_qualification_qualification_id` ASC) VISIBLE,
  INDEX `fk_Death_Information_Death_Location1_idx` (`Death_Location_Location_id` ASC) VISIBLE,
  CONSTRAINT `fk_Death_Information_User1`
    FOREIGN KEY (`User_User_id`)
    REFERENCES `myfamily`.`User` (`User_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Death_Information_death_qualification1`
    FOREIGN KEY (`death_qualification_qualification_id`)
    REFERENCES `myfamily`.`death_qualification` (`qualification_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Death_Information_Death_Location1`
    FOREIGN KEY (`Death_Location_Location_id`)
    REFERENCES `myfamily`.`Death_Location` (`Location_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myfamily`.`Hoseholder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`Hoseholder` (
  `Householder_id` BIGINT NOT NULL,
  `Reason` VARCHAR(45) NOT NULL,
  `Report_date` VARCHAR(45) NOT NULL,
  `User_User_id` BIGINT NOT NULL,
  PRIMARY KEY (`Householder_id`),
  INDEX `fk_Hoseholder_User1_idx` (`User_User_id` ASC) VISIBLE,
  CONSTRAINT `fk_Hoseholder_User1`
    FOREIGN KEY (`User_User_id`)
    REFERENCES `myfamily`.`User` (`User_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myfamily`.`Household_member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`Household_member` (
  `member` BIGINT NOT NULL,
  `Hoseholder_id` BIGINT NOT NULL,
  `User_id` BIGINT NOT NULL,
  `reason` VARCHAR(45) NOT NULL,
  `report_date` VARCHAR(45) NOT NULL,
  `relationships` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`member`),
  INDEX `fk_Household_member_Hoseholder1_idx` (`Hoseholder_id` ASC) VISIBLE,
  INDEX `fk_Household_member_User1_idx` (`User_id` ASC) VISIBLE,
  CONSTRAINT `fk_Household_member_Hoseholder1`
    FOREIGN KEY (`Hoseholder_id`)
    REFERENCES `myfamily`.`Hoseholder` (`Householder_id`),
  CONSTRAINT `fk_Household_member_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `myfamily`.`User` (`User_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myfamily`.`Report_person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`Report_person` (
  `Report_Person_id` BIGINT NOT NULL,
  `Email` VARCHAR(45) NULL DEFAULT NULL,
  `Phone` VARCHAR(45) NULL DEFAULT NULL,
  `User_id` BIGINT NOT NULL,
  PRIMARY KEY (`Report_Person_id`),
  INDEX `fk_Report_person_User1_idx` (`User_id` ASC) VISIBLE,
  CONSTRAINT `fk_Report_person_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `myfamily`.`User` (`User_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myfamily`.`address_state`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`address_state` (
  `address_state_id` BIGINT NOT NULL,
  `state_code` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`address_state_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myfamily`.`User_has_Address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`User_has_Address` (
  `user_ad_id` BIGINT NOT NULL,
  `Address_id` BIGINT NOT NULL,
  `Householder_id` BIGINT NOT NULL,
  `create_at` VARCHAR(45) NOT NULL,
  `address_state_id` BIGINT NOT NULL,
  PRIMARY KEY (`user_ad_id`),
  INDEX `fk_User_has_Address_list_Address_list1_idx` (`Address_id` ASC) VISIBLE,
  INDEX `fk_User_has_Address_list_HouseHolder_idx` (`Householder_id` ASC) VISIBLE,
  INDEX `fk_User_has_Address_list_address_state_idx` (`address_state_id` ASC) VISIBLE,
  CONSTRAINT `fk_User_has_Address_list_Address_list1`
    FOREIGN KEY (`Address_id`)
    REFERENCES `myfamily`.`Address_list` (`Address_id`),
  CONSTRAINT `fk_User_has_Address_list_address_state`
    FOREIGN KEY (`address_state_id`)
    REFERENCES `myfamily`.`address_state` (`address_state_id`),
  CONSTRAINT `fk_User_has_Address_list_HouseHolder`
    FOREIGN KEY (`Householder_id`)
    REFERENCES `myfamily`.`Hoseholder` (`Householder_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myfamily`.`certificatesep`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`certificatesep` (
  `certificatesep_id` BIGINT NOT NULL,
  `Name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`certificatesep_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myfamily`.`certificate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`certificate` (
  `date` VARCHAR(45) NOT NULL,
  `postchecknum` VARCHAR(45) NOT NULL,
  `User_id` BIGINT NOT NULL,
  `certificatesep_id` BIGINT NOT NULL,
  PRIMARY KEY (`postchecknum`),
  INDEX `fk_certificate_User1_idx` (`User_id` ASC) VISIBLE,
  INDEX `fk_certificate_certificatesep_idx` (`certificatesep_id` ASC) VISIBLE,
  CONSTRAINT `fk_certificate_certificatesep`
    FOREIGN KEY (`certificatesep_id`)
    REFERENCES `myfamily`.`certificatesep` (`certificatesep_id`),
  CONSTRAINT `fk_certificate_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `myfamily`.`User` (`User_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myfamily`.`report_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`report_type` (
  `report_type_id` BIGINT NOT NULL,
  `report_type_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`report_type_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myfamily`.`reporter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`reporter` (
  `report_date` DATE NOT NULL,
  `user_id` BIGINT NOT NULL,
  `report_id` BIGINT NOT NULL,
  `report_type_report_type_id` BIGINT NOT NULL,
  PRIMARY KEY (`report_id`),
  INDEX `fk_reporter_Report_person1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_reporter_report_type1_idx` (`report_type_report_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_reporter_Report_person1`
    FOREIGN KEY (`user_id`)
    REFERENCES `myfamily`.`Report_person` (`Report_Person_id`),
  CONSTRAINT `fk_reporter_report_type1`
    FOREIGN KEY (`report_type_report_type_id`)
    REFERENCES `myfamily`.`report_type` (`report_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
