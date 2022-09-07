package com.ysh.exam.capstone.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ysh.exam.capstone.repository.MemberRepository;
import com.ysh.exam.capstone.util.Ut;
import com.ysh.exam.capstone.vo.Member;
import com.ysh.exam.capstone.vo.ResultData;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor //final을 사용하기 위해 필요한 어노테이션
public class MemberService {

//	private MemberRepository memberRepository;
//
//	public MemberService(MemberRepository memberRepository) {
//		this.memberRepository = memberRepository;
//	}
	private final MemberRepository memberRepository; //아래 final을 사용하기 위해서는 @RequiredArgsConstructor어노테이션을 사용해야 하기때문에 위처럼 선언이 불가능 한다
	
	private final PasswordEncoder passwordEncoder; // 암호화를 사용하기 위해 선언해준다 final을 붙여 주지 않으면 오류가 생긴다

	public ResultData<Integer> join(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {
		// 로그인 아이디 증복 체크
		Member oldMember = getMemberByLoginId(loginId);

		if (oldMember != null) { // == 존재한다면
			return ResultData.from("F-7", Ut.f("해당 로그인 아이디 %s는 이미 사용중 입니다", loginId));
		}

		// 이름+이메일 중복 체크
		oldMember = getMemberByName(name);

		if (oldMember != null) { // == 존재한다면
			return ResultData.from("F-8", Ut.f("해당 이름(%s)은 이미 사용중 입니다.", name));
		}
		
		//위에서 이름, 이메일 주복 체크 에서 중복된 이름은 잘 잡는데 반해 중복된 이메일을 잡지 못 해서 email만 따로 다시 만들어 주었다.
		oldMember = getMemberByEmail(email);

		if (oldMember != null) { // == 존재한다면
			return ResultData.from("F-8", Ut.f("해당 이메일(%s)은 이미 사용중 입니다.", email));
		}
		
		oldMember = getMemberByNickName(nickname);

		if (oldMember != null) { // == 존재한다면
			return ResultData.from("F-8", Ut.f("해당 닉네임(%s)은 이미 사용중 입니다.", nickname));
		}
		

//		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		//회원가입시 비밀번호 암호화 하여 저장
		loginPw = passwordEncoder.encode(loginPw);
		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);

		int id = memberRepository.getLastInsertId();

		return ResultData.from("S-1", "회원가입이 완료 되었습니다.", "id", id);
	}
	
	private Member getMemberByNickName(String nickname) {
		return memberRepository.getMemberByNickName(nickname);
	}

	private Member getMemberByEmail(String email) {
		return memberRepository.getMemberByEmail(email);
	}
	
	private Member getMemberByName(String name) {
		return memberRepository.getMemberByName(name);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
	

}
