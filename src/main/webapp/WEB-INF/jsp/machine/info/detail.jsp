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

<!-- 검색 기능 만들기 -->
<div class="container mx-auto px-3 mt-3 justify-between">
  <div class="text-center">
    <div class="row">
      <form method="post" name="search" action="../room/searchList">
        <table class="pull-right">
          <tr>
            <td>
              <select class="form-control" name="searchField">
                <option value="0">전체</option>
                <option value="regDate">날짜</option>
                <option value="joinPm">미세먼지</option>
                <option value="joinTemperature">온도</option>
                <option value="joinHumidity">습도</option>
              </select>
            </td>
            <td>
              <input type="text" class="form-control" placeholder="검색어 입력" name="searchText" maxlength="100">
              <input type="text" class="hidden" name="roomName" value="${room1Name}">
            </td>
            <td>
              <button type="submit" class="btn btn-success">검색</button>
            </td>
          </tr>

        </table>
      </form>
    </div>
  </div>
</div>
<!--  -->


<div class="container mx-auto px-3 mt-5 alert alert-primary justify-between" role="alert">
  <h3 class="inline-block align-middle">위치 : ${room1Name}</h3>
  <span class="float-right inline-block align-middle mt-2">방 생성 날짜 : ${room1RegDate}</span>
</div>

<div class="mt-5">
  <div class="container mx-auto px-3">
  
  <!-- 그래프 기능 추가 하기 -->


    <c:forEach var="room" items="${room}">
      <c:set var="room1" value="${room.roomName }" />
      <div class="inline-flex mr-4">
        <div class="card border-secondary mb-3 " style="max-width: 18rem;">
          <div class="card-header">날짜 : ${room.regDate.substring(2, 16) }</div>
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


<!--  -->

<div class="container mx-auto px-3 mt-5">
  <nav aria-label="Page navigation example" class="d-flex justify-content-end">
    <ul class="pagination">
      <c:if test="${page.prev}">
        <li class="page-item">
          <a href="?roomName=${room1}&page=${page.pageParam.page-1}" class="page-link">이전</a>
        </li>
      </c:if>

      <c:forEach var="pageNum" begin="${page.startPage }" end="${page.endPage}" step="1">
        <li class="page-item ${pageNum == page.pageParam.page? "active" : "" }">
          <a class="page-link" href="?roomName=${room1}&page=${pageNum}">${pageNum }</a>
        </li>
      </c:forEach>

      <c:if test="${page.next}">
        <li class="page-item">
          <a href="?roomName=${room1}&page=${page.pageParam.page+1}" class="page-link">다음</a>
        </li>
      </c:if>
    </ul>
  </nav>
</div>


<%@ include file="../common/foot.jspf"%>