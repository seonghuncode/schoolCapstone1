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
	 
	 @GetMapping("/test")
	 public Object test() {
		 return restTemplateService.test();
	 }
	 
	 //준석 서버
	 @GetMapping("/login")
	 public Object doLogin() {
		 return restTemplateService.login();
	 }
	 
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

}