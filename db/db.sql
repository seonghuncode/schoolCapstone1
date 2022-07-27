

#########################################################################################################
#DB생성
DROP DATABASE IF EXISTS machine;
CREATE DATABASE machine;
USE machine;



CREATE TABLE room(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    roomName CHAR(100) NOT NULL,
    regDate DATETIME NOT NULL,    
    pm CHAR(100) NOT NULL,
    temperature CHAR(100) NOT NULL,
    humadity CHAR(100) NOT NULL
);


INSERT INTO room
SET regDate = NOW(),
roomName = "거실",
pm = "25",
temperature = "25",
humadity = "59";

INSERT INTO room
SET regDate = NOW(),
roomName = "내방",
pm = "20",
temperature = "20",
humadity = "66";

INSERT INTO room
SET regDate = NOW(),
roomName = "부엌",
pm = "17",
temperature = "28",
humadity = "48";


SELECT * FROM room;



#########################################################################################################

#mysql의 기능 : 가장 마지막에 insert한 번호가 나온다
SELECT LAST_INSERT_ID();


