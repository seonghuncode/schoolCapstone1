<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:set var="pageTitle" value="정보 " />

<!-- 불러오기 -->
<%@ include file="../common/head.jspf"%>


<!-- 기존 방을 추가 하는 기능 -->
<!-- 
<div class="mt-5 mx-auto">
  <div class="container mx-auto px-3  ">
    <div style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); width: 70%">

      <a class="navbar-brand " href="/machine/room/showRooms">현재 방에 대한 정보</a>

      <div class="container mt-3">
        <div class="jumbotron" style="padding-top: 20px;">
          <form method="post" action="../room/doInformation">
            <h3 style="text-align: center;">방 추가</h3>
            <div class="form-group mt-3">
              <input type="text" class="form-control" placeholder="추가할 방 이름을 20글자 이내로 입력해 주세요." name="roomname"
                maxlength="20">
            </div>
            <input type="submit" class="btn btn-primary form-control mt-3" value="방 추가 하기">
          </form>
        </div>
              </div>
    </div>


  </div>
</div>
        
         -->

<div class="mt-5 mx-auto">
  <div class="container mx-auto px-3  ">
    <a class="navbar-brand " href="/machine/room/showRooms">현재 방에 대한 정보</a>

    <!-- 유저 정보를 보여주는 기능 -->
    <fieldset class="my-2">
      <legend>[유저 정보]</legend>
      <div class="row my-2">
        <div class="col">
          유저 이름
          <div class="form-control grid text-center" aria-label="First name">${user.name}</div>
        </div>
        <div class="col">
          유저 아이디
          <div class="form-control grid text-center" aria-label="loginId">${user.loginId}</div>
        </div>
        <div class="col">
          유저 닉네임
          <div class="form-control" grid text-center aria-label="nickname">${user.nickname}</div>
        </div>
        <div class="col">
          유저 닉네임
          <div class="form-control grid text-center" aria-label="email">${user.email}</div>
        </div>
        <div class="col">
          로그인 일시
          <div class="form-control grid text-center" aria-label="regDate">${user.regDate}</div>
        </div>
      </div>
    </fieldset>
    <!-- 방이름 값을 저장고 아래 반복문에서 중복된 방이 나올경우 예외처리 -->
    <c:set var="checkRoomName" value="-1" />
    <c:set var="num" value="1" />
    <!-- 데이터에 대한 카운트를 세기 위한 변수 선언 if문에 걸릴때만 +!씩 해준다 -->

    <fieldset class="my-2">
      <legend>[방 정보 / 수정]</legend>
      <table class="table">
        <thead class="table-light">
          <tr>
            <th scope="col">번호</th>
            <th scope="col">방 Id</th>
            <th scope="col">방 이름</th>
            <th scope="col">수 정</th>
          </tr>
        </thead>
        <tbody class="table-warning">

          <c:forEach var="room" items="${rooms}" varStatus="status">
            <!-- 이중반복문으로 공통 방이 있는지 확인 -->

            <!--  중복되는 방이름이 있다면 출력 하지 마라!! -->
            <!-- id값을 오름차순으로 하기 때문에 나중에 이미나온 변수값이 나중에 다시 나와 누락될 일이 없다-->
            <c:if test="${room.roomName ne checkRoomName}">
              <tr>
                <th scope="row">${num}</th>
                <td>${room.id }</td>
                <td>${room.roomName }</td>
                <td>
                  <a href="/machine/room/modify?roomname=${room.roomName}" type="button" class="btn btn-outline-primary">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                      class="bi bi-ui-checks" viewBox="0 0 16 16">
                  <path
                        d="M7 2.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-7a.5.5 0 0 1-.5-.5v-1zM2 1a2 2 0 0 0-2 2v2a2 2 0 0 0 2 2h2a2 2 0 0 0 2-2V3a2 2 0 0 0-2-2H2zm0 8a2 2 0 0 0-2 2v2a2 2 0 0 0 2 2h2a2 2 0 0 0 2-2v-2a2 2 0 0 0-2-2H2zm.854-3.646a.5.5 0 0 1-.708 0l-1-1a.5.5 0 1 1 .708-.708l.646.647 1.646-1.647a.5.5 0 1 1 .708.708l-2 2zm0 8a.5.5 0 0 1-.708 0l-1-1a.5.5 0 0 1 .708-.708l.646.647 1.646-1.647a.5.5 0 0 1 .708.708l-2 2zM7 10.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-7a.5.5 0 0 1-.5-.5v-1zm0-5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm0 8a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5z" />
                  </svg>
                  </a>
                </td>
              </tr>
              <c:set var="num" value="${num + 1}" />
            </c:if>

            <c:set var="checkRoomName" value="${room.roomName}" />
            <!-- 초기에는 checkRoomName에 값이 없기에 중복이 안되게 한후 한번 그 다음 부터 값을 넣어 중복이 않되도록 한다 -->

          </c:forEach>

        </tbody>
      </table>
    </fieldset>
    <!-- 방에 대한 정보 리스트 테이블 -->


    <a href="/" type="button" class="btn btn-warning float-right mt-3"> 뒤로 가기 </a>
  </div>
</div>







<%@ include file="../common/foot.jspf"%>