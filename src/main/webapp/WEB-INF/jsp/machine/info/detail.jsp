<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:set var="pageTitle" value="디테일 정보" />

<!-- 불러오기 -->
<%@ include file="../common/head.jspf"%>



<!-- 방이름을 상단에 보여 주기 위한 코드 -->
<c:forEach var="room" items="${room}">
  <c:set var="room1Name" value="${room.roomName }" />
  <c:set var="room1RegDate" value="${room.regDate.substring(2, 16) }" />
</c:forEach>

<div class="container mx-auto px-3 mt-5 alert alert-primary justify-between" role="alert">
<h3 class="inline-block align-middle">위치 : ${room1Name}</h3>
<span class="float-right inline-block align-middle mt-2">방 생성 날짜 : ${room1RegDate}</span>
</div>

<div class="mt-5">
  <div class="container mx-auto px-3">

    <c:forEach var="room" items="${room}">
      <c:set var="room1" value="${room.roomName }" />
      <div class="inline-flex mr-4">
        <div class="card border-secondary mb-3 " style="max-width: 18rem;">
          <div class="card-header">날짜 : ${room.joinUpdateDate.substring(2, 16) }</div>
          <div class="card-body text-secondary">
            <h5 class="card-title">${room.roomName }</h5>
            <p class="card-text">미세먼지 : ${room.joinPm}</p>
            <p class="card-text">온도 : ${room.joinTemperature}</p>
            <p class="card-text">습도 : ${room.joinHumadity}</p>
          </div>
        </div>
      </div>
    </c:forEach>

  </div>
</div>

<div class="container mx-auto px-3 mt-5">
<button type="button" class="btn btn-light" onclick="history.back();">뒤로 가기</button>
</div>


<%@ include file="../common/foot.jspf"%>