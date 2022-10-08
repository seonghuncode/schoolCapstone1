package com.ysh.exam.capstone.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysh.exam.capstone.service.MemberService;
import com.ysh.exam.capstone.util.Ut;
import com.ysh.exam.capstone.vo.Member;
import com.ysh.exam.capstone.vo.ResultData;

@Controller
public class UsrMemberController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	private MemberService memberService;

	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	//로그인 하면 해당 로그인 아이디 변수에 넣어 두기 --> 추후 로그인한 유저 정보를 DB에서 가지고 올때 사용하기 위함
	String userLoginId = "";

	@RequestMapping("/machine/member/join")
	public String showJoin() {
		return "/machine/usr/join";
	}

	@RequestMapping("/machine/member/dojoin")
	@ResponseBody
//	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
//			String email) {
	public Object doJoin(String loginId, String loginPw, String loginPw2, String name, String nickname,
			String cellphoneNo, String email) {

		if (Ut.empty(name)) {
//			return ResultData.from("F-3", "name(을)를 입력해 주세요.");
//			return Ut.jsReplace("name(을)를 입력해 주세요.", "/machine/member/join"); //기존 문제인 양식을 잘못 입력하면 모든 입력 값이 날라가 다시 입력해야 하기때문에 수정
			return Ut.test1("name(을)를 입력해 주세요.");

		}
		if (Ut.empty(nickname)) {
//			return ResultData.from("F-4", "nickname(을)를 입력해 주세요.");
//			return Ut.jsReplace("nickname(을)를 입력해 주세요.", "../member/dojoin");
			return Ut.test1("nickname(을)를 입력해 주세요.");

		}
		if (Ut.empty(email)) {
//			return ResultData.from("F-6", "email(을)를 입력해 주세요.");
//			return Ut.jsReplace("email(을)를 입력해 주세요.", "/machine/member/join");
			return Ut.test1("email(을)를 입력해 주세요.");

		}
		if (Ut.empty(loginId)) {
//			return ResultData.from("F-1", "loginId(을)를 입력해 주세요");
//			return Ut.jsReplace("loginId(을)를 입력해 주세요", "/machine/usr/join");
			return Ut.test1("loginId(을)를 입력해 주세요.");

		}
		if (Ut.empty(loginPw)) {
//			return ResultData.from("F-2", "loginPw(을)를 입력해 주세요.");
//			return Ut.jsReplace("loginPw(을)를 입력해 주세요.", "/machine/member/join");
			return Ut.test1("loginPw(을)를 입력해 주세요.");
		}
		if (!loginPw.equals(loginPw2)) {
//			return ResultData.from("F-2", "loginPw(을)를 입력해 주세요.");
//			return Ut.jsReplace("비밀번호가 일치 하지 않습니다. 재확인 해주세요", "/machine/member/join");
			return Ut.test1("비밀번호가 일치 하지 않습니다. 재확인 해주세요");

		}

		if (Ut.empty(cellphoneNo)) {
//			return ResultData.from("F-5", "cellphoneNo(을)를 입력해 주세요.");
//			return Ut.jsReplace("cellphoneNo(을)를 입력해 주세요.", "/machine/member/join");
			return Ut.test1("cellphoneNo(을)를 입력해 주세요.");

		}

		// joinRd에 들어 있는 데이터
		// S-1
		// 회원가입이 완료 되었습니다.
		// 7
		ResultData<Integer> joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);

		// Interger을 적는 이유는 아래서 joinRd앞에 형변환 (int)를 사용하지 않기 위해서

		if (joinRd.isFail()) {
//			return (ResultData) joinRd;
			return Ut.test1(Ut.f("%s", joinRd.getMsg()));

		}

		// 위의 joinRd에서 가지고 있는 데이터를 브라우저에게 보여 줄때 성공 여부랑, 메세지는 동일하게 하고 회원번호를 바꾸고 싶어 만든 코드
		// 이
		Member member = memberService.getMemberById(joinRd.getData1());
		System.out.println(joinRd);

//		return ResultData.newData(joinRd, "member", member);
		return Ut.jsReplace(Ut.f("%s님 회원가입이 완료 되었습니다.", member.getNickname()), "/");

//			return joinRd; 
//			만약 이렇게 하면 회원가입이 완료 되었다는 메세지와 함께  데이터에는 getLasdtInsertId가들어가게 된다
//			따라서 위처럼 데이터를 모든 회원 정보를 출력하기 위해 위처럼 바꾸었다.

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

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
//			return ResultData.from("F-3", "존재하지 않는 loginId입니다");
			return Ut.jsReplace("존재하지 않는 loginId입니다", "/machine/member/login");
		}

//		if (member.getLoginPw().equals(loginPw) == false) {
////			return ResultData.from("F-4", "비밀번호가 일치 하지 않습니다.");
//			return Ut.jsReplace("비밀번호가 일치 하지 않습니다.", "/machine/member/login");
//		}
		//비밀번호 암호화 저장후 불러올때 암호화 해독후 비교
		if (!passwordEncoder.matches(loginPw, member.getLoginPw())) {
			return Ut.jsReplace("비밀번호가 일치 하지 않습니다.", "/machine/member/login");
		}
		
		userLoginId = loginId;

		httpSession.setAttribute("loginedMemberId", member.getId());

//		return ResultData.from("S-1", Ut.f("%s님 횐영 합니다.", member.getNickname()));
		return Ut.jsReplace(Ut.f("%s님 환영합니다.", member.getNickname()), "/");

	}
	
	//유저 정보를 보여주기 위해 유저 정보를 가지고 오는 기능
	@RequestMapping("/machine/member/getUser")
	public Object getUserInformation(HttpSession httpSession, Model model) {
		
		//현재 세션으로 로그인 여부를 판단한다
		boolean isLogined = false;
		if (httpSession.getAttribute("loginedMemberId") == null) {
			isLogined = true;
		}
		if (isLogined) {
			 Ut.jsReplace("현재 로그아웃 되어 있습니다. 가능을 사용하기 위해 먼저 로그인을 해주세요.", "/"); //로그인이 되어 있어야 해당 기능을 요청 할 수 있기 때문에 해당 문제에 대해 return을 할 필요가 없다. 
		}
		
		String loginId = userLoginId; //loginId는 로그인시 변수에 저장 하여 따로 저장을 해둔다
		if(loginId == null) {
			return Ut.jsReplace("loginId값이 비어 있습니다. 다시 확인해 주세요", "/");
		}
		
		//loginId를 받아서 DB에서 해당 로그인 아이디와 매치되는 유저 정보를 가지고 와서 return 해준다
		return  memberService.getMemberByLoginId(loginId);

	}

}
