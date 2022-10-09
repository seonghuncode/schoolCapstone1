<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 소수점을 반올림 하기 위한 기능을 사용하기 위해 적어 주어야 한다 -->




<c:set var="pageTitle" value="데이터 리스트" />

<!-- 불러오기 -->
<%@ include file="../common/head.jspf"%>

<hr />

<div class="mt-5">
  <div class="container mx-auto px-3">



    <!-- 한 방에 대해 여러데이터 값 통계치 내기 -->
    <table border="1" class="table text-center">
      <thead>
        <tr class="table-primary">
          <th scope="col">번호</th>
          <th scope="col">방이름</th>
          <th scope="col">등록 날짜</th>
          <th scope="col">미세먼지</th>
          <th scope="col">온도</th>
          <th scope="col">습도</th>
          <th scope="col">데이터 갯수</th>
          <th scope="col">통계 보기</th>
          <th scope="col">삭제 / 수정</th>
        </tr>
      </thead>
      <tbody>
        <c:set var="cnt" value="0" />
        <!--중복되는 것을 카운트 해서 나중에 평균치를 내기 위해 변수 선언 -->

        <!-- 방이름 값을 저장고 아래 반복문에서 중복된 방이 나올경우 예외처리 -->
        <c:set var="checkRoomName" value="-1" />


        <c:forEach var="room" items="${rooms}" varStatus="status">
          <!-- 이중반복문으로 공통 방이 있는지 확인 -->


          <c:set var="roomId" value="${room.id}" />
          <!-- 우선 첫 번째 반복문 에서 id하나를 변수에 넣어 다음 반복문을 처음 부터 끝까지 돌리면서 비교하기 위해 변수 선언 -->


          <c:forEach var="checkRoom" items="${rooms}">

            <c:if test="${roomId eq checkRoom.id}">
              <c:set var="cnt" value="${cnt + 1}" />
              <c:set var="totalPm" value="${totalPm + checkRoom.joinPm}" />
              <c:set var="totalTemperature" value="${totalTemperature + checkRoom.joinTemperature}" />
              <c:set var="totalHumadity" value="${totalHumadity + checkRoom.joinHumadity}" />
            </c:if>
          </c:forEach>


          <!--  중복되는 방이름이 있다면 출력 하지 마라!! -->
          <!-- id값을 오름차순으로 하기 때문에 나중에 이미나온 변수값이 나중에 다시 나와 누락될 일이 없다-->
          <c:if test="${room.roomName ne checkRoomName}">

            <tr class="table-info">
              <td scope="row">${room.id }</td>
              <td>
                <a href="../room/doDetail?roomName=${room.roomName}"> ${room.roomName} </a>
              </td>
              <!-- 
              <td>${room.regDate.substring(2, 16) }</td>
              <td>${room.joinPm/cnt}</td>         
              <td>${room.joinTemperature/cnt}</td>
              <td>${room.joinHumadity/cnt}</td>
               -->
              <td>${room.regDate.substring(2, 16) }</td>
              <td>
                <fmt:formatNumber value="${totalPm/cnt}" pattern=".00" />
              </td>
              <td>
                <fmt:formatNumber value="${totalTemperature/cnt}" pattern=".00" />
              </td>
              <td>
                <fmt:formatNumber value="${totalHumadity/cnt}" pattern=".00" />
              </td>
              <td>${cnt}</td>
              <td>
                <a href="/machine/room/showGraph?roomname=${room.roomName}" type="button" class="btn btn-success">
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pie-chart-fill" viewBox="0 0 16 16">
                    <path d="M15.985 8.5H8.207l-5.5 5.5a8 8 0 0 0 13.277-5.5zM2 13.292A8 8 0 0 1 7.5.015v7.778l-5.5 5.5zM8.5.015V7.5h7.485A8.001 8.001 0 0 0 8.5.015z"></path>
                    </svg>
                  
                </a>
              </td>
              <td>
                <a href="/machine/room/doDelete?roomname=${room.roomName}" type="button" class="btn btn-outline-danger">
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                    class="bi bi-trash3" viewBox="0 0 16 16">
                  <path
                      d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z"></path>
                  </svg>
                </a>

                <!-- 수정허가  미완성-->
                <a href="/machine/room/modify?roomname=${room.roomName}" type="button" class="btn btn-outline-primary">
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                    class="bi bi-ui-checks" viewBox="0 0 16 16">
                  <path
                      d="M7 2.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-7a.5.5 0 0 1-.5-.5v-1zM2 1a2 2 0 0 0-2 2v2a2 2 0 0 0 2 2h2a2 2 0 0 0 2-2V3a2 2 0 0 0-2-2H2zm0 8a2 2 0 0 0-2 2v2a2 2 0 0 0 2 2h2a2 2 0 0 0 2-2v-2a2 2 0 0 0-2-2H2zm.854-3.646a.5.5 0 0 1-.708 0l-1-1a.5.5 0 1 1 .708-.708l.646.647 1.646-1.647a.5.5 0 1 1 .708.708l-2 2zm0 8a.5.5 0 0 1-.708 0l-1-1a.5.5 0 0 1 .708-.708l.646.647 1.646-1.647a.5.5 0 0 1 .708.708l-2 2zM7 10.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-7a.5.5 0 0 1-.5-.5v-1zm0-5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm0 8a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5z" />
                  </svg>
                </a>
              </td>
            </tr>

          </c:if>
          <c:set var="checkRoomName" value="${room.roomName}" />
          <!-- 초기에는 checkRoomName에 값이 없기에 중복이 안되게 한후 한번 그 다음 부터 값을 넣어 중복이 않되도록 한다 -->

          <!-- 위에서 선언한 변수에 중복된 값들을 모두 합친후 테이블에 출력후 다시 반복문을 돌때는 변수 초기화를 시켜준다 -->
          <c:set var="totalPm" value="0" />
          <c:set var="totalTemperature" value="0" />
          <c:set var="totalHumadity" value="0" />
          <c:set var="cnt" value="0" />


        </c:forEach>









      </tbody>
    </table>




  </div>
</div>



<%@ include file="../common/foot.jspf"%>
