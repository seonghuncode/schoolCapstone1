#DB생성
DROP DATABASE IF EXISTS machine;
CREATE DATABASE machine;
USE machine;



CREATE TABLE room(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    roomName CHAR(100) NOT NULL
);

INSERT INTO room
SET regDate = NOW(),
roomName = "거실";

INSERT INTO room
SET regDate = NOW(),
roomName = "내방";

INSERT INTO room
SET regDate = NOW(),
roomName = "부엌";

SELECT * FROM room;





#pm : 미세먼지, temperature : 온도, humadity : 습도, regDate : 등록 시간대
CREATE TABLE info(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    pm CHAR(100) NOT NULL,
    temperature CHAR(100) NOT NULL,
    humadity CHAR(100) NOT NULL
);
#char(용랼 절약이 없다, 검색이 빠르다) , varchar(용량 절약이 있다) 



#미세먼지, 온도 , 습도에 대한 정보(10분 단위로 정보를 등록 받는다고 가정(regDate))
INSERT INTO info
SET regDate = NOW(),
pm = "2",
temperature = "27",
humadity = "35";

INSERT INTO info
SET regDate = NOW(),
pm = "25",
temperature = "25",
humadity = "59";

INSERT INTO info
SET regDate = NOW(),
pm = "20",
temperature = "20",
humadity = "66";

INSERT INTO info
SET regDate = NOW(),
pm = "10",
temperature = "19",
humadity = "30";

INSERT INTO info
SET regDate = NOW(),
pm = "17",
temperature = "28",
humadity = "48";

#id가 1씩 증가 하니까  ex. id =1은 처음 들어온 데이터 2는 10분 후에 들어온 데이터 가정

SELECT * FROM info;



#해결 할 것
#추가적으로 info의 정보가 어느 방에서 왔는지 받는 값이 있어야 한다??



#########################################################################################################

#mysql의 기능 : 가장 마지막에 insert한 번호가 나온다
SELECT LAST_INSERT_ID();


