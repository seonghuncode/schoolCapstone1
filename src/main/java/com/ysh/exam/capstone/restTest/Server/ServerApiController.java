package com.ysh.exam.capstone.restTest.Server;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ysh.exam.capstone.service.MemberService;
import com.ysh.exam.capstone.util.Ut;
import com.ysh.exam.capstone.vo.Member;
import com.ysh.exam.capstone.vo.ResultData;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/server")
@Slf4j
public class ServerApiController {
	
	///////////////////////////////////////
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private MemberService memberService;

	public ServerApiController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	String userLoginId = "";
	
	
	

    @GetMapping("/hello")
    public User get(@RequestParam String name, @RequestParam int age) {
        log.info("hello server요청");
        User user = new User();
        user.setName(name);
        user.setAge(age);
        log.info("user: "+ user);
        return user;
    }
    
    //post구현
    @PostMapping("/user/{userId}/name/{userName}")
    public User post(@RequestBody User user, @PathVariable int userId, @PathVariable String userName) {
        log.info("userId : {}, userName", userId, userName);
        log.info("client req : {}", user);
        return user;
    }
    
    
    
  

    
	//로그인 기능으로 test하기 - restTemplate
	@PostMapping("/machine/member/doLogin?loginId={loginId}&loginPw={loginPw}")
    public Object post(@RequestBody Object object,@PathVariable  String loginId, @PathVariable String loginPw, HttpSession httpSession) {
        log.info("loginId : {}, loginPw", loginId, loginPw);
        log.info("client req : {}", object);
        
        
    	boolean isLogined = false;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			// == loginMemberId에 이미 값이 들어 있다는 의미 == 로그인 되어 있는 상태
			isLogined = true;
		}

		if (isLogined) {
			return ResultData.from("F-5", "이미 로그인 되어 있습니다.");
//			return Ut.jsReplace("이미 로그인 되어 있습니다.", "/machine/member/login");
		}

		if (Ut.empty(loginId)) {
			return ResultData.from("F-1", "loginId를 입력해 주세요");
//			return Ut.jsReplace("loginId를 입력해 주세요", "/machine/member/login");
		}

		if (Ut.empty(loginPw)) {
			return ResultData.from("F-2", "loginPw(을)를 입력해 주세요.");
//			return Ut.jsReplace("loginPw(을)를 입력해 주세요.", "/machine/member/login");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return ResultData.from("F-3", "존재하지 않는 loginId입니다");
			//return Ut.jsReplace("존재하지 않는 loginId입니다", "/machine/member/login");
		}

//		if (member.getLoginPw().equals(loginPw) == false) {
////			return ResultData.from("F-4", "비밀번호가 일치 하지 않습니다.");
//			return Ut.jsReplace("비밀번호가 일치 하지 않습니다.", "/machine/member/login");
//		}
		//비밀번호 암호화 저장후 불러올때 암호화 해독후 비교
		if (!passwordEncoder.matches(loginPw, member.getLoginPw())) {
			//return Ut.jsReplace("비밀번호가 일치 하지 않습니다.", "/machine/member/login");
			return "비밀번호가 일치 하지 않습니다.";
		}
		
		userLoginId = loginId;

		httpSession.setAttribute("loginedMemberId", member.getId());

		return ResultData.from("S-1", Ut.f("%s님 횐영 합니다.", member.getNickname()));
//		return Ut.jsReplace(Ut.f("%s님 환영합니다.", member.getNickname()), "/");
        
    }
    
    @PostMapping("/user/header")
    public User header(@RequestHeader(value = "x-authorization") String header, @RequestBody User user){
        log.info("header : {}",header);
        log.info("body : {}", user);
        System.out.println("###header 통과 header: "+ header);

        return user;
    }
    
    
    
    
}