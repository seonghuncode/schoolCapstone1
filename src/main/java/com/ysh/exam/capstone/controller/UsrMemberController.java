package com.ysh.exam.capstone.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysh.exam.capstone.service.MemberService;
import com.ysh.exam.capstone.util.Ut;
import com.ysh.exam.capstone.vo.Member;
import com.ysh.exam.capstone.vo.ResultData;

@Controller
public class UsrMemberController {

	private MemberService memberService;

	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping("/machine/member/join")
	public String showJoin() {
		return "/machine/usr/join";
	}

	@RequestMapping("/machine/member/dojoin")
	@ResponseBody
//	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
//			String email) {
	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {

		if (Ut.empty(loginId)) {
			return ResultData.from("F-1", "loginId(을)를 입력해 주세요");

		}

		if (Ut.empty(loginPw)) {
			return ResultData.from("F-2", "loginPw(을)를 입력해 주세요.");

		}

		if (Ut.empty(name)) {
			return ResultData.from("F-3", "name(을)를 입력해 주세요.");

		}

		if (Ut.empty(nickname)) {
			return ResultData.from("F-4", "nickname(을)를 입력해 주세요.");

		}

		if (Ut.empty(cellphoneNo)) {
			return ResultData.from("F-5", "cellphoneNo(을)를 입력해 주세요.");

		}

		if (Ut.empty(email)) {
			return ResultData.from("F-6", "email(을)를 입력해 주세요.");

		}

		// joinRd에 들어 있는 데이터
		// S-1
		// 회원가입이 완료 되었습니다.
		// 7
		ResultData<Integer> joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);

		// Interger을 적는 이유는 아래서 joinRd앞에 형변환 (int)를 사용하지 않기 위해서

		if (joinRd.isFail()) {
			return (ResultData) joinRd;
		}

		// 위의 joinRd에서 가지고 있는 데이터를 브라우저에게 보여 줄때 성공 여부랑, 메세지는 동일하게 하고 회원번호를 바꾸고 싶어 만든 코드
		// 이
		Member member = memberService.getMemberById(joinRd.getData1());
//		return ResultData.newData(joinRd, "member", member);
		return ResultData.newData(joinRd, "member", member);

//			return joinRd; 
//			만약 이렇게 하면 회원가입이 완료 되었다는 메세지와 함께  데이터에는 getLasdtInsertId가들어가게 된다
//			따라서 위처럼 데이터를 모든 회원 정보를 출력하기 위해 위처럼 바꾸었다.

	}

	@RequestMapping("/machine/member/doLogout")
	@ResponseBody
	public ResultData doLogout(HttpSession httpSession) {
		boolean isLogined = false;

		if (httpSession.getAttribute("loginedMemberId") == null) {
			isLogined = true;
		}

		if (isLogined) {
			return ResultData.from("S-1", "이미 로그아웃 상태 입니다.");
		}

		httpSession.removeAttribute("loginedMemberId");

		return ResultData.from("S-2", "로그아웃 되었습니다.");

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
			return Ut.jsReplace(Ut.f("이미 로그인 되어 있습니다."));
		}

		if (Ut.empty(loginId)) {
//			return ResultData.from("F-1", "loginId를 입력해 주세요");
			return Ut.jsReplace(Ut.f("loginId를 입력해 주세요"));
		}

		if (Ut.empty(loginPw)) {
//			return ResultData.from("F-2", "loginPw(을)를 입력해 주세요.");
			return Ut.jsReplace(Ut.f("loginPw(을)를 입력해 주세요."));
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
//			return ResultData.from("F-3", "존재하지 않는 loginId입니다");
			return Ut.jsReplace(Ut.f("존재하지 않는 loginId입니다"));
		}

		if (member.getLoginPw().equals(loginPw) == false) {
//			return ResultData.from("F-4", "비밀번호가 일치 하지 않습니다.");
			return Ut.jsReplace(Ut.f("비밀번호가 일치 하지 않습니다."));
		}

		httpSession.setAttribute("loginedMemberId", member.getId());

//		return ResultData.from("S-1", Ut.f("%s님 횐영 합니다.", member.getNickname()));
		return Ut.jsReplace(Ut.f("%s님 환영합니다.", member.getNickname()), "/");
		

	}

}
