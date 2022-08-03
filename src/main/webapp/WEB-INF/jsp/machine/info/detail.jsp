<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:set var="pageTitle" value="디테일 정보" />

<!-- 불러오기 -->
<%@ include file="../common/head.jspf"%>



<div class="mt-5">
  <div class="container mx-auto px-3">

    <div class="card border-secondary mb-3" style="max-width: 18rem;">
      <div class="card-header">${room.regDate.substring(2, 16) }</div>
      <div class="card-body text-secondary">
        <h5 class="card-title">${room.roomName }</h5>
        <p class="card-text">미세먼지 : ${room.joinPm}</p>
        <p class="card-text">온도 : ${room.joinTemperature}</p>
        <p class="card-text">습도 : ${room.joinHumadity}</p>
      </div>
    </div>



    <c:if test="${room.roomName eq '부엌'}">

      <a>저의 이름은 새박입니다.</a>

    </c:if>


    

  </div>
</div>




<%@ include file="../common/foot.jspf"%>