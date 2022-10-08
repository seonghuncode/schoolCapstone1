<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 소수점을 반올림 하기 위한 기능을 사용하기 위해 적어 주어야 한다 -->




<c:set var="pageTitle" value="통계 그래프" />

<!-- 불러오기 -->
<%@ include file="../common/head.jspf"%>



<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">

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

      <!-- 실질적으로 계산되어 출력되는 값들 --> 
        
          console.log(${room.id })
          
         console.log("${room.roomName}") 
        
        console.log("${room.regDate.substring(2, 16)}")
          
            console.log(<fmt:formatNumber value="${totalPm/cnt}" pattern=".00" />)
          
          
            console.log(<fmt:formatNumber value="${totalTemperature/cnt}" pattern=".00" />)
          
          
            console.log(<fmt:formatNumber value="${totalHumadity/cnt}" pattern=".00" />)
          
          ${cnt}
          
          //--------------------------------------------------------------------------


	google.load("visualization", "1", {
		packages : [ "corechart" ]
	});
	google.setOnLoadCallback(drawChart);

	function drawChart() {
		var data = google.visualization.arrayToDataTable([
				['방이름', '메세먼지', '온도', '습도' ], [ '"${room.roomName}"', parseFloat("${totalPm/cnt}"), 400, 400 ],
				[ '"${room.roomName}"', parseFloat("${totalPm/cnt}"), 460 , 400], [ '"${room.roomName}"', parseFloat("${totalPm/cnt}"), 1120 , 400],
				[ '"${room.roomName}"', parseFloat("${totalPm/cnt}"), 540, 400 ] ]);
		var data2 = google.visualization.arrayToDataTable([
				[ 'Year', 'Sales', 'Expenses' ], [ '2004', 1000, 400 ],
				[ '2005', 1170, 460 ], [ '2006', 660, 1120 ],
				[ '2007', 1030, 540 ] ]);
		var options = {
			title : 'Company Performance'
		};
		var options2 = {
			title : 'Company Performance'
		};
		var chart = new google.visualization.LineChart(document
				.getElementById('chart_div'));
		chart.draw(data, options);

		var chart2 = new google.visualization.LineChart(document
				.getElementById('chart_div2'));
		chart2.draw(data2, options2);
		console.log("실행");
	}
	
	//------------------------------------------------------------
	</c:if>
        
        <c:set var="checkRoomName" value="${room.roomName}" />
            <!-- 초기에는 checkRoomName에 값이 없기에 중복이 안되게 한후 한번 그 다음 부터 값을 넣어 중복이 않되도록 한다 -->

            <!-- 위에서 선언한 변수에 중복된 값들을 모두 합친후 테이블에 출력후 다시 반복문을 돌때는 변수 초기화를 시켜준다 -->
            <c:set var="totalPm" value="0" />
            <c:set var="totalTemperature" value="0" />
            <c:set var="totalHumadity" value="0" />
            <c:set var="cnt" value="0" />
     

          </c:forEach>
          
</script>







<div class="container mt-3">
  <div id="chart_div" style="width: 900px; height: 500px;"></div>
  <div id="chart_div2" style="width: 900px; height: 500px;"></div>
</div>




<%@ include file="../common/foot.jspf"%>