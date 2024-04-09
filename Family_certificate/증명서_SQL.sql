INSERT INTO User VALUES
	(1,"남길동","1913년 09월 14일 07시 22분","남","130914-*******","D",1),
    (2,"남석환","1954년 05월 14일 17시 30분","남","540514-*******","L",1),
    (3,"박한나","1955년 10월 22일 11시 15분","여","551022-*******","L",2),
    (4,"남기준","1979년 05월 10일 20시 45분","남","790510-*******","L",1),
    (5,"이주은","1982년 08월 21일 01시 28분","여","820821-*******","L",3),
    (6,"이선미","1985년 12월 05일 22시 01분","여","851205-*******","L",3),
    (7,"남기석","2012년 03월 15일 14시 59분","남","120315-*******","L",1);
    
INSERT INTO Address_list VALUES
	(1,"경기도 성남시 분당구 대왕판교로645번길"),
    (2,"서울특별시 중구 세종대로 110번길"),
    (3,"경기도 수원시 팔달구 효원로 1번길");

INSERT INTO Address_list VALUES
	(4,"강원도 고성군 금강산로 290번길");

    
INSERT INTO Birth_Location VALUES
	(1,"자택"),
    (2,"병원"),
    (3,"기타");
    
INSERT INTO birth_qualification VALUES
	(1,"부"),
    (2,"모"),
    (3,"호주"),
    (4,"동거친족"),
    (5,"기타");
    
INSERT INTO Death_Location VALUES 
	(1, "주택"),
	(2, "의료기관"),
	(3, "사회복지시설(양로원, 고아원 등)"),
	(4, "산업장"),
	(5, "공공시설(학교, 운동장 등)"),
	(6, "도로"),
	(7, "상업/서비스시설(상점, 호텔 등)"),
	(8, "농장(논밭, 축사, 양식장 등)"),
	(9, "병원 이송 중 사망"),
	(10, "기타");
    
INSERT INTO death_qualification VALUES
	(1,"동거친족"),
    (2,"비동거친족"),
    (3,"동거자"),
    (4,"기타");
    
INSERT INTO Birth_Information VALUES
	(1,"1913년 09월 14일 07시 22분",1,1,1),
    (2,"1954년 05월 14일 17시 30분",1,2,1),
    (3,"1955년 10월 22일 11시 15분",2,2,1),
    (4,"1979년 05월 10일 20시 45분",1,2,1),
    (5,"1982년 08월 21일 01시 28분",3,2,1),
    (6,"1985년 12월 05일 22시 01분",3,2,1),
    (7,"2012년 03월 15일 14시 59분",1,2,1);
    
INSERT INTO Death_Information VALUES
	(1,"2021년 04월 29일 09시 03분",4,1,1,2);
    
INSERT INTO certificatesep VALUES
	(1,"가족관계 증명서"),
    (2,"주민등록등본");

INSERT INTO certificate VALUES
	(1,"2021-10-25",4,1);


INSERT INTO Family_type VALUES
	(1,"본인"),
	(2,"부"),
    (3,"모"),
    (4,"배우자"),
    (5,"자녀");

INSERT INTO Family_tie VALUES
    (1,4,4,1),
    (2,4,2,2),
    (3,4,3,3),
    (4,4,5,4),
    (5,4,7,5);
    

-- 가족관계증명서 상단 조회영역
SELECT certificate.date, certificate.postchecknum, Address_list.content
FROM User Inner Join certificate On user.user_id = certificate.user_id
	Inner join certificatesep on certificate.certificatesep_id = certificatesep.certificatesep_id
    Inner Join Address_list on user.address_id = Address_list.Address_id
WHERE user.user_id = 4 and certificatesep.certificatesep_id = 1;

-- 가족구성원 조회 영역Household_member
SELECT Family_type.Family_name, user.name, user.Resident_registration_number, user.gender
FROM User Inner join Family_tie On user.user_id = Family_tie.target
join Family_type On Family_tie.Family_type_id = Family_type.Family_type_id
WHERE family_tie.myself=4;

-- 주민등록번호 상단 조회영역
SELECT certificate.date, certificate.postchecknum, User.name, Hoseholder.Reason, Hoseholder.Report_date
FROM User Inner Join certificate On user.user_id = certificate.user_id
	Inner join certificatesep on certificate.certificatesep_id = certificatesep.certificatesep_id
    Inner Join Address_list on user.address_id = Address_list.Address_id
    Inner Join User_has_Address as ua on Address_list.Address_id = ua.Address_id
    Inner Join Hoseholder on ua.householder_id = hoseholder.Householder_id
WHERE user.name = "남기준" and certificatesep.certificatesep_id = 2;

-- 전입주소 조회 영역
SELECT address_state.state_code, Content, create_at
FROM User
	Inner Join Hoseholder on User.user_id = Hoseholder.user_id
    Inner Join User_has_Address on Hoseholder.Householder_id
    Inner Join Address_list on User_has_Address.user_ad_id = Address_list.Address_id
    Inner Join address_state on User_has_Address.address_state_id = address_state.address_state_id
WHERE hoseholder.User_id = 4
Order by create_at DESC;


-- 세대 구성원 조회 영역
SELECT relationships,user.name,user.Resident_registration_number, Household_member.Report_date, Household_member.reason
FROM User 
	Inner Join Household_member on User.user_id = Household_member.user_id
	Inner Join Hoseholder on householder_id = hoseholder.Householder_id
WHERE hoseholder.User_id = 4;

SELECT distinct c.name, c.Capital , ifnull(ct.name, '수도없음') 수도, ifnull(ct.population, '수도없음') 수도인구
FROM country c left Join city ct on c.Capital = ct.id
where c.name like 'united%';



SET foreign_key_checks=1;
