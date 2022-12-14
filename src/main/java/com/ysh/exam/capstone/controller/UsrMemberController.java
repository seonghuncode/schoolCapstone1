package com.ysh.exam.capstone.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysh.exam.capstone.restTest.Client.RestTemplateService;
import com.ysh.exam.capstone.restTest.Client.login;
import com.ysh.exam.capstone.restTest.Client.userInfo;
import com.ysh.exam.capstone.restTest.Client.userJoin;
import com.ysh.exam.capstone.service.MemberService;
import com.ysh.exam.capstone.util.Ut;
import com.ysh.exam.capstone.vo.UserNowId;



@Controller
public class UsrMemberController {


//	private UserNowId userNowId;
//	 
//    @Autowired(required = false)
//    public void UsrMemberController(UserNowId userNowId) {
//        this.userNowId = userNowId;
//    }
	
	UserNowId userNowId = new UserNowId(); // ==> 로그인을 성공 하면 UserNowId객체에 로그인한 id를 저장해서 정보 기능에서 현재 로그인한 id를 통해 유저 정보를 불러와 사용하기 위함s
	
	
	@Autowired //@Autowired는 의존성 주입을 할 때 사용하는 Annotation으로 의존 객체의 타입에 해당하는 bean을 찾아 주입하는 역할을 한다.
	private RestTemplateService restTemplateService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	private MemberService memberService;

	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	

	// 로그인 하면 해당 로그인 아이디 변수에 넣어 두기 --> 추후 로그인한 유저 정보를 DB에서 가지고 올때 사용하기 위함
	String userLoginId = "";

	@RequestMapping("/machine/member/join")
	public String showJoin() {
		return "/machine/usr/join";
	}

	@RequestMapping("/machine/member/dojoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String loginPw2, String name, String nickname,
			String cellphoneNo, String email) {

		// {login_id}/{login_pw}/{nickname}/{name}/{email}/{phone}")

		if (Ut.empty(name)) {
			return Ut.test1("name(을)를 입력해 주세요.");

		}
		if (Ut.empty(nickname)) {
			return Ut.test1("nickname(을)를 입력해 주세요.");

		}
		if (Ut.empty(email)) {
			return Ut.test1("email(을)를 입력해 주세요.");

		}
		if (Ut.empty(loginId)) {
			return Ut.test1("loginId(을)를 입력해 주세요.");

		}
		if (Ut.empty(loginPw)) {
			return Ut.test1("loginPw(을)를 입력해 주세요.");
		}
		if (!loginPw.equals(loginPw2)) {
			return Ut.test1("비밀번호가 일치 하지 않습니다. 재확인 해주세요");

		}

		if (Ut.empty(cellphoneNo)) {
			return Ut.test1("cellphoneNo(을)를 입력해 주세요.");

		}

//		//-----------------------------------------------------------------------------------------------------------다영님 서버랑 연결 하기 위해 주석
//		// joinRd에 들어 있는 데이터
//		// S-1
//		// 회원가입이 완료 되었습니다.
//		// 7
//		// 아이디, 이름, 이메일, 닉네임이 DB에 존재 하는지 확인하는 코드
//		ResultData<Integer> joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);
//		// Interger을 적는 이유는 아래서 joinRd앞에 형변환 (int)를 사용하지 않기 위해서
//
//		if (joinRd.isFail()) {
//			return Ut.test1(Ut.f("%s", joinRd.getMsg()));
//
//		}
//
//		// 위의 joinRd에서 가지고 있는 데이터를 브라우저에게 보여 줄때 성공 여부랑, 메세지는 동일하게 하고 회원번호를 바꾸고 싶어 만든 코드
//		// 이
//		Member member = memberService.getMemberById(joinRd.getData1());
//		System.out.println(joinRd);
//
////		return ResultData.newData(joinRd, "member", member);
//		return Ut.jsReplace(Ut.f("%s님 회원가입이 완료 되었습니다.", member.getNickname()), "/");
//
////			return joinRd; 
////			만약 이렇게 하면 회원가입이 완료 되었다는 메세지와 함께  데이터에는 getLasdtInsertId가들어가게 된다
////			따라서 위처럼 데이터를 모든 회원 정보를 출력하기 위해 위처럼 바꾸었다.
//		//-----------------------------------------------------------------------------------------------------------다영님 서버랑 연결 하기 위해 주석
		
		
		//--------------------------------------다영님 서버랑 연결하기 위해서 추가한 코드
//		매개변수로 보내줘야 하는 값들 String login_id, String login_pw, String nickname, String name, String email, String phone
		userJoin answer = restTemplateService.join(loginId, loginPw, nickname, name, email, cellphoneNo);
		if(answer.getResult().equals("FALSE")) {
			return Ut.test1("이미 존재하는 회원 정보 입니다. 회원 정보를 변경 후 재시도 해주세요!!");
		}
		
		//--------------------------------------다영님 서버랑 연결하기 위해서 추가한 코드
		
		return Ut.jsReplace(Ut.f("%s님 회원가입이 완료 되었습니다.", nickname), "/");
	

	}

	@RequestMapping("/machine/member/doLogout")
	@ResponseBody
	public String doLogout(HttpSession httpSession) {
		boolean isLogined = false;

		if (httpSession.getAttribute("loginedMemberId") == null) {
			isLogined = true;
		}

		if (isLogined) {
//			return ResultData.from("S-1", "이미 로그아웃 상태 입니다.");
			return Ut.jsReplace("이미 로그아웃 상태 입니다.", "/");
		}

		httpSession.removeAttribute("loginedMemberId");

//		return ResultData.from("S-2", "로그아웃 되었습니다.");
		return Ut.jsReplace("로그아웃 되었습니다.", "/");

	}

	@RequestMapping("/machine/member/login")
	public String showLogin(HttpSession httpSession) {
		return "/machine/usr/login";
	}

	// 로그인 기능 만들기
	@RequestMapping("/machine/member/doLogin")
	@ResponseBody // 스프링 에서는 적어주기만 하면 자동으로 세션이 들어 온다
	public Object doLogin(HttpSession httpSession, String loginId, String loginPw) {

		boolean isLogined = false;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			// == loginMemberId에 이미 값이 들어 있다는 의미 == 로그인 되어 있는 상태
			isLogined = true;
		}

		if (isLogined) {
//			return ResultData.from("F-5", "이미 로그인 되어 있습니다.");
			return Ut.jsReplace("이미 로그인 되어 있습니다.", "/machine/member/login");
		}

		if (Ut.empty(loginId)) {
//			return ResultData.from("F-1", "loginId를 입력해 주세요");
			return Ut.jsReplace("loginId를 입력해 주세요", "/machine/member/login");
		}

		if (Ut.empty(loginPw)) {
//			return ResultData.from("F-2", "loginPw(을)를 입력해 주세요.");
			return Ut.jsReplace("loginPw(을)를 입력해 주세요.", "/machine/member/login");
		}

		
		//--------------------------------------------------------다영님 서버연결로 변경
//		Member member = memberService.getMemberByLoginId(loginId);
//		if (member == null) {
//			return Ut.jsReplace("존재하지 않는 loginId입니다", "/machine/member/login");
//		}
//
//		if (!passwordEncoder.matches(loginPw, member.getLoginPw())) {
//			return Ut.jsReplace("비밀번호가 일치 하지 않습니다.", "/machine/member/login");
//		}
		
		login result = restTemplateService.doLogin(loginId, loginPw);
	
		if(result.getResult().equals("FALSE")) {
			return Ut.jsReplace("존재하지 않는 아이디 또는 비밀번호 입니다. 다시 확인해 주세요!", "/machine/member/login");
		}

		userLoginId = loginId;
		
		userNowId.setLoginId(loginId); //로그인을 진행 하면서 현재 로그인 아이디를 class에 저장하여 다른 곳에서 현재 로그인 되어 있는 로그인 아이를 사용할 수 있도록 해준다.
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		System.out.println(userNowId.getLoginId());
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	
		
//		httpSession.setAttribute("loginedMemberId", member.getId());
		httpSession.setAttribute("loginedMemberId", loginId);
		
		//-----------------------------해당 id를 통해 서버에서 일치하는 id값에 대한 유저정보를 가지고 와서 닉네임으로 환영인사 하는 기능
		userInfo[] user = restTemplateService.showUserInfo(loginId);
		String nickname = user[0].getNickname(); //유저 정보가 배열에 담겨 오는대 항산 배열에 하나의 객체만 담기기 때문에 0변째
		//------------------------------

		//return Ut.jsReplace(Ut.f("%s님 환영합니다.", member.getNickname()), "/");
		return Ut.jsReplace(Ut.f("%s님 환영합니다.", nickname), "/");
		
		//--------------------------------------------------------다영님 서버연결로 변경

	}

	// 유저 정보를 보여주기 위해 유저 정보를 가지고 오는 기능
	@RequestMapping("/machine/member/getUser")
	public Object getUserInformation(HttpSession httpSession, Model model) {

		// 현재 세션으로 로그인 여부를 판단한다
		boolean isLogined = false;
		if (httpSession.getAttribute("loginedMemberId") == null) {
			isLogined = true;
		}
		if (isLogined) {
			Ut.jsReplace("현재 로그아웃 되어 있습니다. 가능을 사용하기 위해 먼저 로그인을 해주세요.", "/"); // 로그인이 되어 있어야 해당 기능을 요청 할 수 있기 때문에 해당 문제에
																				// 대해 return을 할 필요가 없다.
		}

		//userLoginId는 로그인시 변수에 저장해 두어 놓은 현재 로그인 되어 있는 id값이 들어가 있디.
		String loginId = userLoginId; // loginId는 로그인시 변수에 저장 하여 따로 저장을 해둔다
		if (loginId == null) {
			return Ut.jsReplace("loginId값이 비어 있습니다. 다시 확인해 주세요", "/");
		}

		// loginId를 받아서 DB에서 해당 로그인 아이디와 매치되는 유저 정보를 가지고 와서 return 해준다
		return memberService.getMemberByLoginId(loginId);

	}
	
	
	
	
}

