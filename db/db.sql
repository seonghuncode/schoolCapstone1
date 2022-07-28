

#########################################################################################################
#DB생성
DROP DATABASE IF EXISTS machine;
CREATE DATABASE machine;
USE machine;


CREATE TABLE room(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    roomName CHAR(100) NOT NULL,
    regDate DATETIME NOT NULL
);

#테스트 데이터
INSERT INTO room
SET roomName = "거실",
regDate = NOW();

INSERT INTO room
SET roomName = "부엌",
regDate = NOW();

INSERT INTO room
SET roomName = "주방",
regDate = NOW();

SELECT * FROM room;



CREATE TABLE info(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    updateDate DATETIME NOT NULL,
    pm CHAR(100) NOT NULL,
    temperature CHAR(100) NOT NULL,
    humadity CHAR(100) NOT NULL
);

#테스트 데이터
INSERT INTO info
SET updateDate = NOW(),
pm = "20",
temperature = "26",
humadity = "30";

INSERT INTO info
SET updateDate = NOW(),
pm = "39",
temperature = "22",
humadity = "45";

INSERT INTO info
SET updateDate = NOW(),
pm = "39",
temperature = "25",
humadity = "31";

INSERT INTO info
SET updateDate = NOW(),
pm = "22",
temperature = "31",
humadity = "33";

INSERT INTO info
SET updateDate = NOW(),
pm = "21",
temperature = "23",
humadity = "44";

SELECT * FROM info;


#########################################################################################################

#mysql의 기능 : 가장 마지막에 insert한 번호가 나온다
SELECT LAST_INSERT_ID();




















