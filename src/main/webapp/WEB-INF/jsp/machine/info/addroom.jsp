<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:set var="pageTitle" value="방 생성 하기" />

<!-- 불러오기 -->
<%@ include file="../common/head.jspf"%>



<div class="mt-5 mx-auto">
  <div class="container mx-auto px-3  ">
    <div style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); width: 70%">

      <a class="navbar-brand " href="/machine/room/showRooms">현재 방에 대한 정보</a>

      <div class="container mt-3">
        <div class="jumbotron" style="padding-top: 20px;">
          <form method="post" action="../room/doAdd">
            <h3 style="text-align: center;">방 추가</h3>
            <div class="form-group mt-3">
              <input type="text" class="form-control" placeholder="추가할 방 이름을 20글자 이내로 입력해 주세요." name="roomname"
                maxlength="20">
            </div>
            <input type="submit" class="btn btn-primary form-control mt-3" value="방 추가 하기">
          </form>
        </div>

        <!-- ================================================================================================================================================= -->
        <!-- 반복문을 돌려서 존재하는 방 이름을 나열 한다 -->
        <div class="mt-10 card">
          <div class="card-body">
            현재 존재 하는 방 이름 :
            <!-- 방이름 값을 저장고 아래 반복문에서 중복된 방이 나올경우 예외처리 -->
            <c:set var="checkRoomName" value="-1" />

            <c:forEach var="room" items="${rooms}" varStatus="status">
              <!-- 이중반복문으로 공통 방이 있는지 확인 -->
              <c:set var="roomId" value="${room.id}" />
              <!-- 우선 첫 번째 반복문 에서 id하나를 변수에 넣어 다음 반복문을 처음 부터 끝까지 돌리면서 비교하기 위해 변수 선언 -->

              <!--  중복되는 방이름이 있다면 출력 하지 마라!! -->
              <!-- id값을 오름차순으로 하기 때문에 나중에 이미나온 변수값이 나중에 다시 나와 누락될 일이 없다-->
              <c:if test="${room.roomName ne checkRoomName}">
                <span class="m-1 text-red-400">${room.roomName}</span>
              </c:if>
              <c:set var="checkRoomName" value="${room.roomName}" />
              <!-- 초기에는 checkRoomName에 값이 없기에 중복이 안되게 한후 한번 그 다음 부터 값을 넣어 중복이 않되도록 한다 -->

            </c:forEach>

          </div>
        </div>
        <!-- 현재 존재 하는 방에 대한 이름 나열 -->
        <!-- ================================================================================================================================================= -->

      </div>
    </div>


  </div>
</div>




<%@ include file="../common/foot.jspf"%>