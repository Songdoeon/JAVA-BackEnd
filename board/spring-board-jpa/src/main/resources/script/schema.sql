CREATE TABLE IF NOT EXISTS `Posts` (
    `post_id` bigint NOT NULL auto_increment,
    `title`   VARCHAR(50) NOT NULL,
    `content`  VARCHAR(200) NOT NULL,
    `user_id`  VARCHAR(200) NOT NULL,
    `view_count`  int NOT NULL,
    `write_time` DATETIME,
    PRIMARY KEY(`post_id`,`user_id`)
);

create table if not exists `Users` (
    `user_id` VARCHAR(50) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    `name` VARCHAR(50) NOT NULL,
    `profileFileName` VARCHAR(50),

    primary key(`user_id`)
);

MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'admin', '1234','마르코','no-image.png' );
MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'student1', '1234','학생1','no-image.png' );
MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'student2', '1234','학생2','no-image.png' );
MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'student3', '1234','학생3','no-image.png' );
MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'student4', '1234','학생4','no-image.png' );
MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'student5', '1234','학생5','no-image.png' );
MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'student6', '1234','학생6','no-image.png' );
MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'student7', '1234','학생7','no-image.png' );
MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'student8', '1234','학생8','no-image.png' );
MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'student9', '1234','학생9','no-image.png' );
MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'student10', '1234','학생10','no-image.png' );
MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'student11', '1234','학생11','no-image.png' );

MERGE INTO `Posts` KEY ( `post_id`,`user_id` ) VALUES ( 1,'제목1', '내용1','admin',0,now() );
MERGE INTO `Posts` KEY ( `post_id`,`user_id` ) VALUES ( 2,'제목2', '내용2','student1',0,now() );
MERGE INTO `Posts` KEY ( `post_id`,`user_id` ) VALUES ( 3,'제목3', '내용3','student2',0,now() );



