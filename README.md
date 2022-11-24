# schoolCapstone1
3학년2학기 캡스톤

# 웹웹.

<hr/>

처음 계획
우선 처음 캡스톤 주제로 움직이는 공기 청정기로 정하게 되었고
기능의 큰 틀은 청청기가 방안을 돌아 다니면서 레이타 센서로 방의 구조를 파악하고 방 구조를 처음에 정의를 해두면 해당 방에 대해 미세먼지, 온도, 습도에 대한 데이터를 보내 주어
웹에서 데이터들을 사용자가 보기 쉽게 가공하여 보여주기 위해 필요한 기능 별로 아래 설명을 해두었 습니다.

<hr/>

웹 기능/구조
1. 로그인 기능 : 로그인이 되어 있지 않은 상태 에서만 보여준다
![1](https://user-images.githubusercontent.com/93322852/190896120-c04489e6-d536-4f10-8f77-eb22fddb63f9.png)
  -DB에서 일치 하는 아이디외 비밀번호가 존재 하면 성공
  
  <hr/>
  
2. 로그아웃 기능 : 로그인이 되어 있는 경우 에만 버튼 활성화
![2](https://user-images.githubusercontent.com/93322852/190896208-3ade92da-2bb5-4b89-b07b-ed65bf986be7.png)
  -로그인 성공시 닉네임이 관리자인 경우 alert창으로 성공 메세지 후 홈 화면으로 이동 한다
 ![image](https://user-images.githubusercontent.com/93322852/190896254-0bb6904b-71b9-4c04-852e-5f9e2d1a5300.png)
  -성공시 메인화면의 산단 메뉴바에 로그아웃, 방만들기, 데이터 정보에 대한 버튼 추가
  
  <hr/>
  
3. 회원가입 기능 : 로그인이 되어 있지 않은 상황에만 버튼 활성화
![image](https://user-images.githubusercontent.com/93322852/190896293-ebcffba4-0ffe-4124-af86-2baccc680f0f.png)
  -회원가입의 경우 name, loginId, email에 대해서는 기존에 DB에 이미 존재 하는 정보라면 alert창으로 오류 메세지로 중복에 대한 메세지를 보여주고 다시 회원가입 페이지로 이동
  -마찬가지로 비밀번호의 경우도 비밀번호, 확인요 비밀번호가 일치 하는지 확인후 모든 조건이 만족하면 alert창으로 성공 메세지 후  메인 화면으로 이동
  
  <hr/>
  
4. 홈으로 가는 버튼 : 홈으로 가는 버튼을 누르면 홈 화면으로 이동
  -버튼 클릭시 홈 화면으로 이동한다
  
  <hr/>
  
5. 방만들기 : 로그인이 되어 있는 상태에서 방을 추가 하면 DB에 저장 -> 방을 초반에 만들어 사용할 예정이므로 추후 방을 추가 하는 작업을 하지 않기 때문에 필요 앖는 기능
![5](https://user-images.githubusercontent.com/93322852/190896430-e2de08ef-ebd6-4371-a2b8-fefe3d372315.png)
  -기존에 존재하는 방이름과 동일 하지 않는 방 이름이라면 성공 후 홈 화면 으로 이동
  -아래에 기존 방이름이 어떤것 들이 있는지 보여주나다
  
  <hr/>
  
  5-1 방만들기 기능을 -> 정보 기능으로 수정 (22.10.09) : 유저 정보와 방에 대한 정보, 수정 기능을 하는 정보 페이지로 수정
  ![1](https://user-images.githubusercontent.com/93322852/194770399-0f45e0ef-6bb4-4b33-bd93-173179257d84.png)
  ==> 방 만들기 -> 정보
  ![2](https://user-images.githubusercontent.com/93322852/194770409-4fcb54d6-c285-4286-b28d-26679eb47c6b.png)
  ==> 상단에는 현재 로그인 하고 있는 user에 대한 정보를 보여 주고, 하단 에는 테이블로 방에 대한 정보 + 방 이름 수정 기능 위치
  ![3](https://user-images.githubusercontent.com/93322852/194770414-593ffe19-2c55-4508-ad05-d94833aad1b9.png)
  ==>수정 기능을 클릭 하면 방이름을 수정 할 수 있는 페이지로 이동후 저장을 클릭하면 방 아름 변경(기존에 존재하는 방 이름과 동일할 경우 경고창 으로 막는다.)
  프로젝트 기능상 방 만들기 기능은 필요X -> 해당 페이지를 방 정보 기능으로 수정
  
  어려웠던 점 : 방을 만드는 기능을 만들때 해당 방이 이미 존재 하는지 정보를 DB에서 불러 올때 방에 처음 쿼리문으로 방 정보를 불러올때 해당방에 대해 하나의 정보만 있으면 오류가   나지 않았지만 한 방에 대해 여러 데이터기 존재할 경우 쿼리문에서 여러개의 데이터에 대해 처리를 하지 못해 오류가 났었는데 처음에는 문제를 잘 인식하지 못 해 해결하는데 어려움이   있었다. 해결 방법으로는 join된 테이블 에는 해당 방에 대한 데이터가 여러개 있을경우 방이름도 여러개가 생성 되었기에 문제가 발생 하였고 해결하기 위해서 정보를 불러오는 것에서   room테이블을 불러오는 것으로 수정을 하여 해결 하였다.
  
  <hr/>
  
6. 종합적 데이터 정보 리스트 : 화면의 데이터 정보 버튼을 누르면 해당 방에 대한 데이터의 평균치로 리스트를 보여준다
![6](https://user-images.githubusercontent.com/93322852/190896461-5fa459ff-b6cb-40f9-a781-d298c29dacd3.png)
  -우선 방에 해당 하는 여러 데이터들을 평균치로 계산하여 평균 데이터 정보로 모든 방에 대한 정보를 보여준다
  -방이름을 클릭하면 해당 방에 대한 정보들만 나오는 디테일 화면으로 이동
  -우측 상단 삭제, 수정 아이콘으로 기능 추가
  
  어려웠던 점 : 데이터를 보여줄때는 join된 테이블에서 불러온 데이터를 보여주는 것 이었는데 그러다 보니 방 한개에 대해 여러 데이터가 존재 할 경우 해당 방에 대한 이름괴 데이터가   일대일 관계로 평균치로 나오는 것 으로 수정하는데 있어 어려움이 많았다.
  이를 해결 하기 위해 jstl기능으로 jsp에서 join된 값을 받아 계산을 처리한 후 if문으로 조건을 만족하는 데이터만 화면에 보여주게 함으로써 문제를 해결하였다.
  
  <hr/>
  
7. 해당 방에 대한 데이터만  : 해당 방 이름을 클릭하면 해당 방에 대한 데이터들을 시간, 일로 나타낸다
![7](https://user-images.githubusercontent.com/93322852/190896823-82c899f6-515c-4865-b919-a7dcc0377e33.png)
  -해당 방에 대한 데이터들을 시간 별로 모두 보여준다.
  
  <hr/>
  
8. 삭제 : 해당 방에 대해 삭제 아이콘을 누르면 삭제 된다.
  -데이터 리스트 버튼을 클릭하며 우측 삭제 아이콘을 누르면 alert창으로 삭제 완료 메세지를 보여 주고 데이커 리스트 화면으로 이동한다
  
  <hr/>
  
9. 수정 : 해당 방에 대해 수정 아이콘을 클릭 하면 수정 화면으로 이동후 수정 한다(만약 기존의 방 이름으로 수정을 시도 하면 alert창으로 경고)
![8](https://user-images.githubusercontent.com/93322852/190896895-a9dae8da-bcf7-4b08-8a88-783a6b9a0551.png)
  -데이터 리스트 에서 우측 수정 아이콘을 클릭 하면 수정 화면으로 이동하여 수정 하는 방 이름이 기존에 존재한는 방 이름과 중복이 되지 않는다면 alert로 성공 메세지를 알려주고
  기존의 방이름과 중복이 된다면 alert로 경고 메세지를 보여주고 데이터 리스트 화면으로 이동 한다.
  
<hr/>

10. 페이징 기능 : 데테일한 정보를 보기 위해 해당 방에 대한 모든 온도, 습도, 미세먼지에 대한 정보들을 한페이지에 6개씩 보여주는 기능
  ![paging](https://user-images.githubusercontent.com/93322852/193008842-5e9ba6a9-e4e0-455c-88d2-fe74441bdf75.png)
  어려웠던 점 : 스프링 에서 제공하는 pageable API를 사용하면 페이징 기능을 간단하게 구현할 수 있지만 그러기 위해서는 JPA형식으로 코드를 짰어야 했는데 그렇지 않아서 해당 api를 사   용하기 위해서는 구조의 큰 틀을 변경해야 하기 때문에 다른 방법을 찾은데 구글링 하는 시간이 많이 걸렸다. 
  다행히 dto로 기능을 만들어 페이지 기능을 직접 만들어 주어 DB에 있는 데이터 총 갯수외 시작 페이지만 설정해주어 각각 버튼을 눌렀을때 화면에 뿌려지는 데이터에 limit 시작숫자, 한번   에 보여지는 숫자로 DB에서 데이터를 불러와 a태그를 통해 해당 페이지로 이동하는 형태로 기능을 마무리 했다.
  
  <hr/>
  
11. 검색 기능 : 온도, 미세먼지, 습도에 대해 원하는 값을 검색하면 해당 값들만 보여 준다
  ![search](https://user-images.githubusercontent.com/93322852/193009496-62d1470a-c795-403a-a2b9-711b5401ba6e.png)
  어려웠던 점 : 검색 기능의 경우 어떤 방법으로 리스트에서 검색된 내용만 보여지게 할지 구글링을 하다 방법이 너무 많아 이것저것 시도하면서 시간이 많이 들어갔던것 같고 sql문에서       LIKE%%로 해당 검색 문자를 보내와 받아온 값으로 화면에 뿌려주려고 했는데 계속 알 수 없는 오류가 떠서 구글링 결과 jdbcType=VARCHAR를 넣어 주어야 한다는 것을 알게 되었다. null 값   이 들어올 수도 있기 때문에 미리 기본값을 넣어주어야 오류를 피할 수 있다.
  
  <hr/>
  
12. 통계 자료 그래프로 보여 주는 기능 : 통계 차트를 보고 싶은 방을 클릭 하면 해당 방에 대한 온도,습도,미세먼지에 대한 통계치를 차트로 보여 준다.
![5](https://user-images.githubusercontent.com/93322852/194770570-f794948b-8aa9-43a9-80f2-d4140f745506.png)
==>초기 단계
==>문제점
https://www.phpschool.com/gnuboard4/bbs/board.php?bo_table=qna_html&wr_id=272558 ==> 그래프를 다른 그래프로 변경 참고(여러개 데이터를 나타내는 해결방법)
https://tomining.tistory.com/8 ==> 그래프 참고 자료
jstl문법과 javascript문법을 같이 사용하고 있는데 jstl문법이 다 끝나고 javascript가 실행되어 마지막 그래프만 나오는 문제
![6](https://user-images.githubusercontent.com/93322852/194770575-43fc6719-d74b-4ac3-b83a-dc760a6abffb.png)
==>모든 방에 대한 리스트를 보여주는 화면에서 보고 샆은 방에 대해 차트 아이콘을 클릭하면 해당 roomName값을 넘겨주어 해당 방에 대한 통계값을 계산하여 차트로 보여 준다.
![7](https://user-images.githubusercontent.com/93322852/194770582-665d41d4-5dac-4620-b6a7-7f4fa153eedd.png)
==>차트로 보여진 결과 화면
==>최종 적으로 차트 기능을 추가 하면서 문제와 해결했던 방법<==
그래프 기능을 추가 하면서 시간이 오래 걸렸는데 이유는 우선 처음 에는 Basic Line Chart를 사용하려고 하였으나 구현하는 것에 있어 간단하다는 장점외에 프로젝트에 맞게 가공하는데 있어 적합하지 않아 구글 차트 api를 사용하게 되었다.
구글 차트를 사용함에 있어서는 그래프를 여러개 추가 하기 위해서는 각각의 데이터를 지속적으로 추가하여 body태그 에서 데이터 갯수 만큼 불러와야 다수의 차트를 출력할 수 있다는 문제점 때문에 기존에
모든 방에 대한 차트를 한페이지에 보여 주는 기능에서 해당 방에 대한 이름 값을 주면 해당 방에 대한 통계 치만 보여주는 페이지로 이동하는 기능 으로 수정을 하였고
기능을 계속 수정 하고 오류를 해결 하는 과정 에서 오래 걸렸고
특히 Uncaught SyntaxError: missing ) after argument list라는 오류를 해결하는데 많은 시간이 걸렸습니다.
오류 내용 참고 자료 ==> https://chobopark.tistory.com/199 

<hr/>

13. 메인 페이지 구성 : 웹 페이지에 대한 기능 설명
![1](https://user-images.githubusercontent.com/93322852/194898869-0e16d539-d3b8-40e6-9292-887c06b53992.png)
![2](https://user-images.githubusercontent.com/93322852/194898884-de24a963-82bc-4f3d-b4b5-f11adf875364.png)
![3](https://user-images.githubusercontent.com/93322852/194898895-574d685a-da2d-4a04-9a00-6d23ff5c2fad.png)
각각의 버튼들에 대한 기능 설명 동작 과정에 대해 간단한 기술 설명
문제점 : JSP파일에서 부트스트랩에 대한 css를 읽어 오지 못해 기능이 정상적으로 동작하지 않는 문제가 있어 -> https://codevang.tistory.com/223해당 블로그를 받아 도움을 받았 습니다. 다른 헤더 파일 에서 불러 오는데에 문제가 있어 추가적인 cdnjs를 통해 부트스트랙을 다시 한번 링크를 연결해 주어 문제를 해결했 습니다.

<hr/>

14. 서버 연결 하기 <br />
(http로 전송 하는 경우 외부 서버와 연결이 정상적으로 되었다.)<br />
![1](https://user-images.githubusercontent.com/93322852/198220558-8c4c7f9b-7423-4853-a59e-55af5fd5e0d5.png)<br />
![2](https://user-images.githubusercontent.com/93322852/198220583-b6bcae33-c722-44b1-8463-70e8a33cf99f.png)<br />
(https로 보낼경우 문제점 발생)<br />
오류 발생 원인 <br />
-SSL 인증서가 신뢰하는 기관 인증서가 없거나 SSL/TLS암호화 버전이 맞지 않는 경우 발생<br />
-연결하려는 서버의 인증서가 신뢰하는 인증기관 인증서 목록(keystore)에 없을 경우 - 사설 인증서일 경우.<br />
-서버/클라이언트 사이에 사용하려는 TLS 버전이 맞지 않을 때(TLS 1.0 만 지원하는 서버에 1.2로 hand shaking 요청등)<br />
-TLS 통신에 사용하려는 cipher suite 가 오래되거나 지원하지 않음. (JDK 1.8 부터는 sha1 지원 안되고 sha256 이상을 사용해야 한다고 한다.)<br />
==> 해결 방안 <br />
![5](https://user-images.githubusercontent.com/93322852/198222101-d177f5d1-1ff9-4e2f-ae0c-e50053290038.png)
![6](https://user-images.githubusercontent.com/93322852/198222118-fac8a8d5-fc37-4fdd-81e5-c7320b2cce7c.png)
![7](https://user-images.githubusercontent.com/93322852/198222132-75ea55fa-9956-4134-9792-ae9e47a10feb.png)

서버 현재 문제점(22.10.27기준)<br />
현재 서버 두개중 어떠한 서버로 사용할지 결정X<br />
다영님 서버의 경우 회원가입시 데이터를 보내주고 성공하면 true값을 return받아야 하는데 null값으로 리턴 되는게  <br />

참고 자료<br />
https://goddaehee.tistory.com/m/268
https://osujin.github.io/2017/06/09/HttpsURLConnection%EC%9D%84-%EC%9D%B4%EC%9A%A9%ED%95%9C-%ED%86%B5%EC%8B%A0%EC%8B%9C-SSL-%EC%9D%B8%EC%A6%9D%EC%84%9C-%EC%88%98%EB%8F%99%EC%9C%BC%EB%A1%9C-%EC%A7%80%EC%A0%95%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95/
https://offbyone.tistory.com/m/262
https://bobr2.tistory.com/entry/SSL-%EC%9D%B8%EC%A6%9D%EC%84%9C-%EC%97%86%EC%9D%B4-https-%ED%86%B5%EC%8B%A0%ED%95%98%EB%8A%94-%EB%B2%95-%EC%98%88%EC%A0%9C
https://ram2ram2.tistory.com/16



  ------------------------------------------------------------------------------------------------------------------------------------------------------------------
  ==문제 상황==
  현재 DB에서 room테이블의 infoId와 + info테이블의 roomId를 통해 조인을 시키는데 방을 만들때 infoId에 null값이 들어가게 되어 추후 방에 대한 정보와 매칭이 될 수 없다.
  따라서 현재 테이블의 infoId값을 autoincrement를 해주어야 하는데 이를 위해서는 primarykey를 걸어주어야 하는데 한테이블에 두개 거는 법을 구글링 해서 걸어주고 infoId역시 걸어   주어 수정 해주기
  
  <hr/>
  
  ==참고 싸이트== 
  https://wsss.tistory.com/(웹 디자인에 대한 코드 참고)
  https://nerdcave.com/tailwind-cheat-sheet(테일윈드를 사용하면서 기능들을 검색할때 사용)
  https://noonnu.cc/(폰트 디자인을 참고할때 사용)
  https://getbootstrap.com/docs/5.2/getting-started/introduction/(CSS디자인 틀을 참고할때 사용)
  https://icons.getbootstrap.com/(아이콘 디자인을 참고할때 사용)
  
  <hr/>
  
  ==웹 에서 서버로 부터 필요한 기능들 정리==
![10](https://user-images.githubusercontent.com/93322852/193010267-b80268f0-1e7b-45a6-a061-ea0dfd98ac2b.png)

![10](https://user-images.githubusercontent.com/93322852/193010278-45ecf3f1-bef9-46c7-a876-e299d1299533.png)

![10](https://user-images.githubusercontent.com/93322852/193010298-ce5f9807-28d4-409b-bd65-1598590742c6.png)

![10](https://user-images.githubusercontent.com/93322852/193010366-9de853e9-4cef-4a03-aaca-56836aa55ee0.png)

![10](https://user-images.githubusercontent.com/93322852/193010368-985bb5f4-8303-4dc4-89e8-edaaf85d85cc.png)

![10](https://user-images.githubusercontent.com/93322852/193010378-7c97a12b-3287-47a1-916d-59d232cb19cb.png)

![10](https://user-images.githubusercontent.com/93322852/193010390-ad47b769-94e1-47e7-a9f2-31c10230b896.png)

![10](https://user-images.githubusercontent.com/93322852/193010407-d47b11db-ab27-4b9b-be3f-6809a04fa239.png)

