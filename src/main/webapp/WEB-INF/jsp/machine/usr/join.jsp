


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="회원가입 페이지" />
<c:set var="cssType" value="/resource/common.js" />

<!-- 불러오기 -->
<%@ include file="../common/head.jspf"%>


<div class="mt-5">
  <div class="container mx-auto px-3">



    <form class="row g-3 m-20"  method="POST" action="../member/dojoin">
      <div class="col-12">
        <label for="inputName" class="form-label">name</label>
        <input name="name" type="text" class="form-control" id="inputName" placeholder="사용할 이름을 입력해 주세요" >
      </div>
      <div class="col-12">
        <label for="nickname" class="form-label">nickname</label>
        <input name="nickname" type="text" class="form-control" id="nickname" placeholder="사용할 닉네임을 입력해 주세요">
      </div>
      <div class="col-md-6">
        <label for="inputEmail" class="form-label">Email</label>
        <input name="email" type="email" class="form-control" id="inputEmail" placeholder="사용할 이메일을 입력해 주세요">
      </div>
      <div class="col-12">
        <label for="inputloginId" class="form-label">loginId</label>
        <input name="loginId" type="text" class="form-control" id="inputloginId" placeholder="사용할 아이디를 입력해 주세요">
      </div>
      <div class="col-12">
        <label for="inputloginPw" class="form-label">loginPw</label>
        <input name="loginPw" type="password" class="form-control" id="inputloginPw" placeholder="사용할 비밀번호를 입력해 주세요">
      </div>
      <div class="col-12">
        <label for="inputloginPw2" class="form-label">loginPw</label>
        <input name="loginPw2" type="password" class="form-control" id="inputloginPw2" placeholder="비밀번호 확인을 위해 한번더 입력해 주세요">
      </div>
      <div class="col-12">
        <label for="inputCellPhoneNum" class="form-label">cellphoneNum</label>
        <input name="cellphoneNo" type="text" class="form-control" id="inputCellPhoneNum" placeholder="휴대폰 번호를 입력해 주세요">
      </div>

      <div class="col-12">
        <button type="submit" class="btn btn-primary">회원가입</button>
        <button type="button" class="btn btn-primary" onclick="history.back();">뒤로 가기</button>
      </div>
      
          
    </form>
    

  </div>
</div>




<%@ include file="../common/foot.jspf"%>
