<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="메인 페이지" />

<!-- scss경로 -->
<c:set var="cssType" value="/resource/login.css" />

<!-- 불러오기 -->
<%@ include file="../common/head.jspf"%>


<section class="mt-5">
  <div class="container mx-auto px-3 mb-3"><i class="bi bi-lightbulb"></i>캡스톤 주제 : 로봇 청소기와 로봇 공기 청정기의 결합</div>
</section>

<div class="container mx-auto px-3">
  <p class="text-red-400"><i class="bi bi-bookmark-check-fill"></i>로그인전 기능 설명</p>
  <div class="accordion accordion-flush" id="accordionFlushExample">
    <div class="accordion-item">
      <h2 class="accordion-header" id="flush-headingOne">
        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#one"
          aria-expanded="false" aria-controls="flush-collapseOne">#1. 메인 메뉴로 이동</button>
      </h2>
      <div id="one" class="accordion-collapse collapse" aria-labelledby="flush-headingOne"
        data-bs-parent="#accordionFlushExample">
        <div class="accordion-body">
         왼쪽 상단 로봇 청정기 버튼 또는 홈 버튼을 누를 경우 현재 메인 페이지로 이동.
         
        </div>
      </div>
    </div>
    
    <div class="accordion-item">
      <h2 class="accordion-header" id="flush-headingOne">
        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#two"
          aria-expanded="false" aria-controls="flush-collapseOne">#2. 회원가입 기능</button>
      </h2>
      <div id="two" class="accordion-collapse collapse" aria-labelledby="flush-headingOne"
        data-bs-parent="#accordionFlushExample">
        <div class="accordion-body">
         우측 상단 nav bar 에서 회원가입 버튼을 누르면 회원 가입 페이지로 이동 하여 name, nickname, Email, loginId, loginPw, cellPhoneNum을 입력하여 회원 가입을 진행 한다. <br />
         (이때 이름, 닉네임, 아이디, 이메일은 기존에 존재 한다면 경고창으로 회원가입 진행이 불가 해진다.)
         
        </div>
      </div>
    </div>
    
    <div class="accordion-item">
      <h2 class="accordion-header" id="flush-headingOne">
        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#three"
          aria-expanded="false" aria-controls="flush-collapseOne">#3. 로그인 기능</button>
      </h2>
      <div id="three" class="accordion-collapse collapse" aria-labelledby="flush-headingOne"
        data-bs-parent="#accordionFlushExample">
        <div class="accordion-body">
         우측 상단 로그인 버튼을 클릭하면 loginId, Pw를 입력하는 로그인 페이지로 이동하여 로그인을 진행한다. <br />
         (이때 DB에 저장 되지 않은 아이디와 비밀번호 라면 아이디와 비밀번호를 다시 확인 하라는 alert경고창이 뜬다.) <br />
         (정상적으로 로그인이 왼료된다면 해당 아이디의 세션을 등록하여 로그아웃 이전까지 로그인 상태를 유지 한다.)
          
        </div>
      </div>
    </div>

  </div>


<!--  ======================   -->

 <p class="text-red-400 mt-3"><i class="bi bi-bookmark-check-fill"></i>로그인후 기능 설명</p>
    <div class="accordion accordion-flush" id="accordionFlushExample">
    <div class="accordion-item">
      <h2 class="accordion-header" id="flush-headingOne">
        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#one-1"
          aria-expanded="false" aria-controls="flush-collapseOne">#1. 메인 메뉴로 이동</button>
      </h2>
      <div id="one-1" class="accordion-collapse collapse" aria-labelledby="flush-headingOne"
        data-bs-parent="#accordionFlushExample">
        <div class="accordion-body">
         왼쪽 상단 로봇 청정기 버튼 또는 홈 버튼을 누를 경우 현재 메인 페이지로 이동.
         
        </div>
      </div>
    </div>
    
    <div class="accordion-item">
      <h2 class="accordion-header" id="flush-headingOne">
        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#two-2"
          aria-expanded="false" aria-controls="flush-collapseOne">#2. 로그아웃 기능</button>
      </h2>
      <div id="two-2" class="accordion-collapse collapse" aria-labelledby="flush-headingOne"
        data-bs-parent="#accordionFlushExample">
        <div class="accordion-body">
        우측 상단 로그아웃 버튼을 클릭하면 해당 로그인 유저의 세션을 끊어 로그아웃 상태로 만든다. <br />
        (만약 로그근이 되어 있지 않은 상황이라면 현재 로그인이 되어 있지 않다는 alert 경고창을 보여 준다.)
        </div>
      </div>
    </div>
    
    <div class="accordion-item">
      <h2 class="accordion-header" id="flush-headingOne">
        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#three-3"
          aria-expanded="false" aria-controls="flush-collapseOne">#3. 회원 정보, 방에 대한 간단 정보 열람, 방 이름 수정</button>
      </h2>
      <div id="three-3" class="accordion-collapse collapse" aria-labelledby="flush-headingOne"
        data-bs-parent="#accordionFlushExample">
        <div class="accordion-body">
        [Step1] <br />
         우측 상단 정보라는 버튼을 클릭 하면 현재 로그인 되어 있는 사용자의 이름, 닉네임, 아이디, 이메일, 로그인 일시에 대한 정보가 나오게 된다. <br />
         [Step2]
         하단에는 현재 등록 되어 있는 방에 대한 정보가 나온다(방id, 방이름, 수정)
         (수정 버튼을 클릭 하면 해당 방 이름을 수정 할 수 있는 페이지로 이동 한다.) <br />
         (수정 페이지 에서는 현재 존재 하는 방 이름을 하단에 보여주고 만약 변경 하는 방 이를이 존재하는 방 이름과 동일 하다면 경고창으로 변경을 막는다.) <br />
         
         ==상단 현재 방에 대한 정보를 클릭 하면 방에 대한 더 정확하고 디테일한 정보를 볼 수 있는 페이지로 이동한다.(데이터 정보)==
        </div>
      </div>
    </div>
    
       <div class="accordion-item">
      <h2 class="accordion-header" id="flush-headingOne">
        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#fore-4"
          aria-expanded="false" aria-controls="flush-collapseOne">#4. 방에 온도, 미세먼지, 습도, 통계 자료 리스트 + 해당 방에 대한 전체 데이터 확인</button>
      </h2>
      <div id="fore-4" class="accordion-collapse collapse" aria-labelledby="flush-headingOne"
        data-bs-parent="#accordionFlushExample">
        <div class="accordion-body">
        우측 상단 데이터 정보 버튼을 클릭 하면 <br />
        [Step1]
        방 번호 - 방이름 - 등록날짜 - 미세먼지 -온도 - 습도 - 저장된 데이터 갯수 - 통계보기 순으로 DB에 저장되어 있는 방에 대한 정보를 리스트 형태의 테이블로 보여 준다. <br />
        [Step2]
        방이름을 클릭 하면 해당 방에 대해 저장된 날짜, 방이름, 미세먼지, 온도, 습도에 대한 전체 데이터를 통계가 아닌 각각의 전테 데이터로 보여준다.
        (이때 찾고자 하는 해당 정보를 검색해서 필터링 해서 원하는 정보만 볼 수 있다, 페이징 기능으로 한 페이지에 6개 데이터씩 보여준다.)
        [Step3]
        통계보기 아이콘을 클릭 하면 해당 방에 대한 이름을 넘겨주어 해당 방에 대한 통계치를 계산 하는 알고리즘을 거쳐 원형 차트로 보여 준다.
        == 수정/삭제 기능도 존재 하지만 현재 사용X -> 추후 수정 할 예정 ==
        </div>
      </div>
    </div>
    

  </div>
  

  
</div>


   
  

</div class="mb-10">





<%@ include file="../common/foot.jspf"%>