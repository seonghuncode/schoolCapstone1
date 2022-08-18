<!-- 

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/53a8c415f1.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/resource/login.css" />
<script src="https://unpkg.com/tailwindcss-jit-cdn"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css">
</head>
<body>

 -->



<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<c:set var="pageTitle" value="로그인 페이지" />
<c:set var="cssType" value="/resource/login.css" />

<!-- 불러오기 -->
<%@ include file="../common/head.jspf"%>




<form class="table-box-type-1" method="POST" action="../member/doLogin">
  <div class="wrap">
    <div class="login">
      <h2>Log-in</h2>
      <div class="login_id">
        <h4>loginId</h4>
        <input type="text" name="loginId" id="" placeholder="아이디">
      </div>
      <div class="login_pw">
        <h4>Password</h4>
        <input type="password" name="loginPw" id="" placeholder="비밀번호">
      </div>
      <div class="submit">
        <input type="submit" value="submit">
        <a href="/" type="button" class="btn btn-light float-right mt-24"> 뒤로 가기 </a>
      </div>
      <div id="back2"></div>
    </div>
  </div>
</form>






<%@ include file="../common/foot.jspf"%>