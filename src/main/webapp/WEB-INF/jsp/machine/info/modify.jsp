<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:set var="pageTitle" value="방 이름 변경 하기" />

<!-- 불러오기 -->
<%@ include file="../common/head.jspf"%>



<div class="container mt-3">
  <div class="jumbotron" style="padding-top: 20px;">
    <form method="post" action="../room/doModify">
      <h3 style="text-align: center;">방 이름 변경</h3>
      <div class="input-group mb-3">
       <span class="input-group-text" id="basic-addon1">현재 방 이름 : </span>
        <input  type="text" class="form-control" name="roomnameOld" maxlength="20" value="${room.roomName}">
      </div>
      <div class="form-group mt-3">
        <input type="text" class="form-control" placeholder="변경할 방 이름을 입력해 주세요." name="roomnameNew" maxlength="20">
      </div>
      <input type="submit" class="btn btn-primary form-control mt-3" value="방 이름 변경 하기">
    </form>
  </div>
</div>




<%@ include file="../common/foot.jspf"%>