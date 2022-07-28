<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- pom.xml에 있는 JSTL을 사용할수 있게 import 해주는 것.
기존에 jstl을 의존성 주입을 해주었기 때문에 이렇게 코드만 작성하면 바로 사용 가능 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 정보 리스트</title>
</head>
<body>

  <!-- <div>${rooms}</div>  -->
  <!-- jstl사용 하기 -->
  <c:forEach var="room" items="${rooms}">

  </c:forEach>
  <!-- 
  forEach는 반복문으로 articles에 있는 것을 article에 담아 반복 한다
   -->

  <hr />

  <table border="1">
    <thead>
      <tr>
        <th>번호</th>
        <th>방이름</th>
        <th>등록 날짜</th>
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
          <td>${info.id}}</td>
          <td>${info.updateDate}</td>
          <td>${info.pm}</td>
          <td>${info.temperature}</td>
          <td>${info.humadity}</td>
      </c:forEach>
      </tr>
    </tbody>
  </table>






</body>
</html>
