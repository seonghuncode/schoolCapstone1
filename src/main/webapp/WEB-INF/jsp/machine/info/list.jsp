<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="데이터 리스트" />

<!-- 불러오기 -->
<%@ include file="../common/head.jspf"%>

<hr />

<div class="mt-5">
  <div class="container mx-auto px-3">

    <table border="1">
      <thead>
        <tr>
          <th>번호</th>
          <th>방이름</th>
          <th>등록 날짜</th>
          <th>미세먼지</th>
          <th>온도</th>
          <th>습도</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="room" items="${rooms}">
          <tr>
            <td>${room.id }</td>
            <td>
              <a href="../room/doDetail?roomName=${room.roomName }"> ${room.roomName} </a>
            </td>
            <!-- <td>${room.roomName}</td>  -->
            <td>${room.regDate.substring(2, 16) }</td>
            <td>${room.joinPm}</td>
            <td>${room.joinTemperature}</td>
            <td>${room.joinHumadity}</td>
        </c:forEach>
        </tr>

      </tbody>
    </table>

    <table border="1">
      <thead>
        <tr>
          <th>id</th>
          <th>업데이트 날짜</th>
          <th>미세먼지</th>
          <th>온도</th>
          <th>습도</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="info" items="${AllInfo}">
          <tr>
            <td>${info.id}</td>
            <td>${info.updateDate}</td>
            <td>${info.pm}</td>
            <td>${info.temperature}</td>
            <td>${info.humadity}</td>
            <td>${info.joinName}</td>
        </c:forEach>
        </tr>
      </tbody>
    </table>

  </div>
</div>



<%@ include file="../common/foot.jspf"%>
