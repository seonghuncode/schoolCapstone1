<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- 제이쿼리 불러오기 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- 테일윈드 불러오기 -->
<!-- 노말라이즈, 라이브러리까지 한방에 해결 -->
<script src="https://unpkg.com/tailwindcss-jit-cdn"></script>

<!-- 폰트어썸 불러오기 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" />

<!-- 사이트 공통 CSS -->
<link rel="stylesheet" href="/resource/common.css" />
<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css">
<!-- 사이트 공통 JS -->
<script src="/resource/common.js" defer="defer"></script>
</head>
<body>

  
  <h2 class="row g-3 m-20">회원가입</h2>
 
  <form class="row g-3 m-20">
    <div class="col-12">
      <label for="inputName" class="form-label">name</label>
      <input type="text" class="form-control" id="inputName" placeholder="사용할 이름을 입력해 주세요">
    </div>
    <div class="col-12">
      <label for="nickname" class="form-label">nickname</label>
      <input type="text" class="form-control" id="nickname" placeholder="사용할 닉네임을 입력해 주세요">
    </div>
    <div class="col-md-6">
      <label for="inputEmail" class="form-label">Email</label>
      <input type="email" class="form-control" id="inputEmail" placeholder="사용할 이메일을 입력해 주세요">
    </div>
    <div class="col-12">
      <label for="inputloginId" class="form-label">loginId</label>
      <input type="text" class="form-control" id="inputloginId" placeholder="사용할 아이디를 입력해 주세요">
    </div>
    <div class="col-12">
      <label for="inputloginPw" class="form-label">loginPw</label>
      <input type="text" class="form-control" id="inputloginPw" placeholder="사용할 비밀번호를 입력해 주세요">
    </div>
    <div class="col-12">
      <label for="inputloginPw2" class="form-label">loginPw</label>
      <input type="text" class="form-control" id="inputloginPw2" placeholder="비밀번호 확인을 위해 한번더 입력해 주세요">
    </div>
    <div class="col-12">
      <label for="inputCellPhoneNum" class="form-label">cellphoneNum</label>
      <input type="text" class="form-control" id="inputCellPhoneNum" placeholder="휴대폰 번호를 입력해 주세요">
    </div>

    <div class="col-12">
      <button type="submit" class="btn btn-primary">Sign in</button>
      <div class="btn btn-primary sm:w-24">뒤로 가기</div>
    </div>
  </form>


</body>
</html>