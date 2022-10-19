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
			
		 return restTemplateService.join();
	 }

}