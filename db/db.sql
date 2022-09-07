

#########################################################################################################
#DB생성
DROP DATABASE IF EXISTS machine;
CREATE DATABASE machine;
USE machine;

#방에 대한 정보를 담는 테이블
CREATE TABLE room(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    roomName CHAR(100) NOT NULL,
    regDate DATETIME NOT NULL,
    infoId INT(10) UNSIGNED NULL 
);
#기존 infoId는 unsigned not null이였지만 방을 추가시 조인을 할때 indoId외 rooId를 매치 시킬때 새로 만들어지는 방에 대해서는 infoId가 null이 되어 오류가 발생
#임시적으로 null로 바꾸어 오류 나오지 않게(사로만드는 방의infoId가 null값이면 나중에 rooId외 매칭이 될수 없다.)

#테스트 데이터
INSERT INTO room
SET roomName = "거실",
regDate = NOW(),
infoId = "1";

INSERT INTO room
SET roomName = "부엌",
regDate = NOW(),
infoId = "2";

INSERT INTO room
SET roomName = "주방",
regDate = NOW(),
infoId = "3";

INSERT INTO room
SET roomName = "내방",
regDate = NOW(),
infoId = "4";

INSERT INTO room
SET roomName = "안방",
regDate = NOW(),
infoId = "5";

SELECT * FROM room;


#하드웨어로 부터 얻은 정보를 담는 테이블
CREATE TABLE info(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    updateDate DATETIME NOT NULL,
    pm CHAR(100) NOT NULL,
    temperature CHAR(100) NOT NULL,
    humadity CHAR(100) NOT NULL,
    roomId INT(10) UNSIGNED NOT NULL
);

#테스트 데이터
INSERT INTO info
SET updateDate = NOW(),
pm = "20",
temperature = "26",
humadity = "31",
roomId = "1";

INSERT INTO info
SET updateDate = NOW(),
pm = "11",
temperature = "22",
humadity = "22",
roomId = "1";

INSERT INTO info
SET updateDate = NOW(),
pm = "19",
temperature = "27",
humadity = "33",
roomId = "1";

INSERT INTO info
SET updateDate = NOW(),
pm = "39",
temperature = "22",
humadity = "45",
roomId = "2";

INSERT INTO info
SET updateDate = NOW(),
pm = "39",
temperature = "25",
humadity = "31",
roomId = "3";

INSERT INTO info
SET updateDate = NOW(),
pm = "22",
temperature = "31",
humadity = "33",
roomId = "4";

INSERT INTO info
SET updateDate = NOW(),
pm = "21",
temperature = "23",
humadity = "44",
roomId = "5";



INSERT INTO info
SET updateDate = NOW(),
pm = "20",
temperature = "26",
humadity = "31",
roomId = "1";

INSERT INTO info
SET updateDate = NOW(),
pm = "10",
temperature = "27",
humadity = "40",
roomId = "4";

INSERT INTO info
SET updateDate = NOW(),
pm = "31",
temperature = "19",
humadity = "27",
roomId = "4";


INSERT INTO info
SET updateDate = NOW(),
pm = "31",
temperature = "27",
humadity = "33",
roomId = "5";

INSERT INTO info
SET updateDate = NOW(),
pm = "28",
temperature = "26",
humadity = "28",
roomId = "5";

SELECT * FROM info;

SELECT * FROM room;
	
	
	
	


#########################################################################################################

#mysql의 기능 : 가장 마지막에 insert한 번호가 나온다
SELECT LAST_INSERT_ID();



#(테이블 순서는 중요도 순으로 웬만하면 맞추어 준다)
#회원 테아블 생성 
CREATE TABLE `member`(
id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
loginId CHAR(20) NOT NULL, #20자면 충분하다
loginPw CHAR(60) NOT NULL, #비밀번호를 60자러 하는 이유는 나중에 암호회를 해서 저장 해야 하기 때문이다.(우리나라 법으로 비밀번호는 원문으로 저장하면 안되게 되어 있다.)
authLevel SMALLINT(2) UNSIGNED DEFAULT 3 COMMENT '권한레벨(3=일반, 7=관리자)',  #default값을 지정해주고, 잘모르는 것은 comment를 남길수 있다
`name` CHAR(20) NOT NULL,
nickname CHAR(20) NOT NULL,
cellphoneNo CHAR(20) NOT NULL,
email CHAR(50) NOT NULL,
delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '탈퇴여부(0=탈퇴전,1=탈퇴)', #tinyint를 사용하는 이유는 실제 회원가입시 insert하고 탈퇴를 할때 회원정보를 지우는 것이 아니라
delDate DATETIME COMMENT '탈퇴날짜'  #탈퇴들 안했으면 데이터기 없으므로 not null이 없다     #delStatus같은 상태값을 만들어 1에서 0으로 바꾸는 형식으로 숨기는 방식으로 진행이 된다
);



#회원, 테스트 데이터 생성(관리자 회원) -> 비밀번호 admin
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = '$2a$10$oTs/i8Mqmphh2ppBBWicKe1imBT7.UR72kPo77CcL/141nOywtl4q',
authLevel = 7,
`name` = '관리자',
nickname = '관리자',
cellphoneNo = '01011111111',
email = 'test1@naver.com';



#회원, 테스트 데이터 생성(일반 회원) -> 비밀번호 user1
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user1',
loginPw = '$2a$10$IcM4zuGqYpJo20GVtxFwluG9fmr.VvfU8o9F./xr4.uYpr/kNn0oW',
authLevel = 3, #default 가 3이기 때문에 안넣어도 된다
`name` = '사용자1',
nickname = '사용자1',
cellphoneNo = '01022222222',
email = 'test2@naver.com';



INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user2',
loginPw = '$2a$10$BWArnymnSKQ9HdK1z.P/1ul6W82KObrv90HoaH09XzLclu0ZfIB2i',
`name` = '사용자2',
nickname = '사용자2',
cellphoneNo = '01033333333',
email = 'test3@naver.com';



SELECT * FROM `member`



#기존의 게시물은 memberid가 없었기 때문에 2번 으로 지정 해준다.
UPDATE article
SET memberId = 2
WHERE memberId = 0;




			SELECT A.*,
			M.pm AS joinPm,
			M.temperature AS joinTemperature,
			M.humadity AS joinHumadity
			FROM room AS A
			LEFT JOIN info AS M
			ON A.infoId = M.roomId
			ORDER BY A.id ASC
	

	



