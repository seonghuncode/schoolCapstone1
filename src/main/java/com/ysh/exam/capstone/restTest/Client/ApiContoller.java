package com.ysh.exam.capstone.restTest.Client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ysh.exam.capstone.vo.Member;

//Controller

@RestController
@RequestMapping("/api/client")
public class ApiContoller {
	

	private final RestTemplateService restTemplateService;

	public ApiContoller(RestTemplateService restTemplateService) {
		this.restTemplateService = restTemplateService;
	}

	// get메서드 구현
//    @GetMapping("/hello")
//    public UserResponse getHello() {
//        return restTemplateService.hello();
//    }

//	//post 구현
//	@GetMapping("/hello")
//    public UserResponse postHello() {
//        return restTemplateService.post();
//    
//	}

	
	//restTemplate exchange구현
	@GetMapping("/hello")
	public UserResponse getHello() {
		return restTemplateService.hello();
	}

	@GetMapping("/post")
	public UserResponse post() {
		return restTemplateService.post();
	}

	@GetMapping("/exchange")
	public ResponseEntity exchange() {
		return restTemplateService.exchange();
	}
	
	//restTemplage naver 구현 items 빈값으로 나옴
	 @GetMapping("/naver")
	    public ResponseEntity naver() {
	        return restTemplateService.naver();
	    }
	 
//	 @GetMapping("/test")
//	 public Object test() {
//		 return restTemplateService.test();
//	 }
	 
//	 //준석 서버
//	 @GetMapping("/login")
//	 public Object doLogin() {
//		 return restTemplateService.login();
//	 }
	 
	 //다영님 서버
	 @GetMapping("/join")
	 public Object doJoin() {
		 
		 String login_id = "1234";
		 String login_pw = "1234";
		 String nickname = "1234";
		 String name = "1234";
		 String email = "1234";
		 String phone = "1234";
		 return restTemplateService.join( login_id, login_pw, nickname, name, email,phone);
	 }
	 
	 //다영님 서버
	 @GetMapping("/allRoomInfo")
	 public Object allRoomInfo() {
		 System.out.println(restTemplateService.allRoomInfo());
		 return restTemplateService.allRoomInfo();
	 }
	 
	 //다영님 서버 로그인
	 @GetMapping("/login")
	 public login doLogin() {
		 String login_id = "11";
		 String login_pw = "11";
		 return restTemplateService.doLogin(login_id, login_pw);
	 }
	 
	 //다영님 서버 방이름 수정
	 @GetMapping("/modify")
	 public Modify doModify() {
		 String oldRoomName = "eee";
		 String newRoomName = "방1";
		 return restTemplateService.doModify(oldRoomName, newRoomName);
	 }
	 
	 //다영님 서버 특정 방 검색후 해당 방 가지고 오기
	 @GetMapping("findRoom")
	 public Object doFindroom() {
		 String roomName = "방1";
		 return restTemplateService.findRoom(roomName);
	 }
	 
	 //다영님 서버 특정 방에 대해 데이터를 삭제 하는 기능
	 @GetMapping("/delete")
	 public Result doDelete() {
		 String roomName = "www";
		 return restTemplateService.deleteRoomData(roomName);
	 }
	 
	 //다영님 서버랑 연결 : 유저 아이디를 넘겨 주면 해당 id와 일치 하는 유저 정보를 넘겨준다
	 @GetMapping("/userInfo")
	 public userInfo[] showUserInfo() {
		 String userId = "11";
		 return restTemplateService.showUserInfo(userId);
	 }
	 
	 //다영님 서버랑 연결 : 디데일 하게 보는 기능 해당 방에 대해 5개 데이터만 보내주는 역할
	 @GetMapping("/stat_web")
	 public allRoomInfo[] showDetailData() {
		 String roomName = "방10";
		 int start = 1;
		 int amount = 2;
		 return restTemplateService.showDetailData(roomName, start, amount);
	 }
	 
	 //다영님 서버 연결 : 검색 기능 날짜에 대한 검색 기능
	 @GetMapping("/searchRegDate")
	 public allRoomInfo[] showSearchRegDate() {
		 String searchText = "05";
		 String roomName = "방10";
		 int start = 1;
		 int amount = 3;
		 return restTemplateService.showSearchReDate(searchText, roomName, start, amount);
	 }
	 
	 //다영님 서버 연결 : 검색 기능 미세먼지에 관한 검색 기능
	 @GetMapping("/searchFindDust")
	 public allRoomInfo[] showSearchFindDust() {
		 String searchText = "2";
		 String roomName = "방10";
		 int start = 1;
		 int amount = 3;
		 return restTemplateService.showSearchFindDust(searchText, roomName, start, amount);
	 }
	 
	 //다영님 서버 연결 : 검섹기능 온도에 관한 검색 하기
	 @GetMapping("/searchFindTemp")
	 public allRoomInfo[] showSearchFindTemp() {
		 String searchText = "2";
		 String roomName = "방10";
		 int start = 1;
		 int amount = 3;
		 return restTemplateService.showSearchFindTemp(searchText, roomName, start, amount);
	 }
	 
	 //다영님 서버 연결 : 검색기능 -> 습도에 대한 검색
	 @GetMapping("/searchFindHumidity")
	 public allRoomInfo[] showSearchFindHumidity() {
		 String searchText = "2";
		 String roomName = "방10";
		 int start = 1;
		 int amount = 3;
		 return restTemplateService.showSearchFindHumidity(searchText, roomName, start, amount);
	 }
	 
	 

}