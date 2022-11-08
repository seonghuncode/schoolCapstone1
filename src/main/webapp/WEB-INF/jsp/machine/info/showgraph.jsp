<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 소수점을 반올림 하기 위한 기능을 사용하기 위해 적어 주어야 한다 -->




<c:set var="pageTitle" value="통계 정보" />

<!-- 불러오기 -->
<%@ include file="../common/head.jspf"%>




<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
<!-- 해당 방 이름에 대한 통계를 내기 위한 알고리즘 -->
<c:set var="roomId" value="${room.id}" />
<c:set var="checkSameExistName" value="${nowRoomName}" />
	   <c:set var="roomId" value="${room.id}" />
		   <c:set var="regDate" value="${room.regDate.created_at(2, 16)}" />	   
       <c:set var="cnt" value="${cnt + 1}" />
       <c:set var="totalPm" value="${totalPm + checkRoom.findedust}" />
       <c:set var="totalTemperature" value="${totalTemperature + checkRoom.temp}" />
       <c:set var="totalHumadity" value="${totalHumadity + checkRoom.humidify}" />

<c:forEach var="room" items="${rooms}" varStatus="status"> <!-- 상위 반복문 데이터가 끝날때 까지 반복문 -->
   <c:forEach var="checkRoom" items="${rooms}"> <!--  상위 반복문에서 첫 방에대해(실질적으로 해당 변수는 반복문으로만 사용하고 쓰지x)  -->
     <c:if test="${nowRoomName eq room.room_name}">  <!-- 현재 방이름과 방이름이 동일 할때만 -->
          <c:set var="roomId" value="${room.id}" />
        	  <c:set var="created_at" value="${room.created_at.substring(2, 16)}" />
          <c:set var="cnt" value="${cnt + 1}" />
          <c:set var="totalPm" value="${totalPm + checkRoom.findedust}" />
          <c:set var="totalTemperature" value="${totalTemperature + checkRoom.temp}" />
          <c:set var="totalHumadity" value="${totalHumadity + checkRoom.humidify}" />
        </c:if>
        </c:forEach>
         
           
      </c:forEach>
      console.log(${roomId})
      console.log("${nowRoomName}") 
       console.log("${created_at}") 
      console.log(<fmt:formatNumber value="${totalPm/cnt}" pattern=".00" />)
      console.log(<fmt:formatNumber value="${totalTemperature/cnt}" pattern=".00" />)
      console.log(<fmt:formatNumber value="${totalHumadity/cnt}" pattern=".00" />)
      ${cnt}
      
      <!--원형 그래프 부분 코그 -->
      google.load("visualization", "1", {
  		packages : [ "corechart" ]
  	});
  	google.setOnLoadCallback(drawChart);
  	<!--원형 그래프 부분 코그 -->
  	
  	<!-- 꺾은선 그래프 부분 -->
    google.charts.load('current', {'packages':['bar']});
    google.charts.setOnLoadCallback(drawChart);
    <!-- 꺾은선 그래프 부분 -->

  	function drawChart() {
  		
  		<!--원형 그래프 부분 코그 -->
  		var data = google.visualization.arrayToDataTable([
				['방이름', '미세먼지', '온도', '습도' ], 
				[ '미세먼지', parseFloat("${totalPm/cnt}"), parseFloat("${totalTemperature/cnt}"), parseFloat("${totalHumadity/cnt}") ],
				[ '온도', parseFloat("${totalTemperature/cnt}"), parseFloat("${totalTemperature/cnt}") , parseFloat("${totalHumadity/cnt}")], 
				[ '습도', parseFloat("${totalHumadity/cnt}"), parseFloat("${totalTemperature/cnt}"), parseFloat("${totalHumadity/cnt}") ] ]);

  		var options = {
  			title : '${nowRoomName}에 대한 통계(백분율)'
  		};
  		
  		var chart = new google.visualization.PieChart(document
  				.getElementById('chart_div'));
  		chart.draw(data, options);
  		<!--원형 그래프 부분 코그 -->
  		//
  		
  			<!-- 꺾은선 그래프 부분 -->
  	  var data2 = google.visualization.arrayToDataTable([
    	  ['방이름', '미세먼지', '온도', '습도' ], 
			[ '${nowRoomName}', parseFloat("${totalPm/cnt}"), parseFloat("${totalTemperature/cnt}"), parseFloat("${totalHumadity/cnt}") ],
			 ]);

      var options2 = {
        chart: {
          title: '${nowRoomName}에 대한 통계',
          subtitle: '미세먼지, 온도, 습도에 대한 수치',
        },
        bars: 'horizontal' // Required for Material Bar Charts.
      };

      var chart2 = new google.charts.Bar(document.getElementById('barchart_material1'));

      chart2.draw(data2, options2);
  	<!-- 꺾은선 그래프 부분 -->
  		
  	}
  



</script>



<div class="container mt-3">
  <div class="flex items-center">
    <span id="chart_div" style="width: 700px; height: 500px;"></span>
    <span id="barchart_material1" style="width: 500px; height: 200px;"></span>
  </div>
  <a href="/" type="button" class="btn btn-warning float-right mt-1"> 뒤로 가기 </a>
</div>




<%@ include file="../common/foot.jspf"%>