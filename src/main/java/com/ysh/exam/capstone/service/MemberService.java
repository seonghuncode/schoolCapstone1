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
		oldMember = getMemberByNameAndEmail(name, email);

		if (oldMember != null) { // == 존재한다면
			return ResultData.from("F-8", Ut.f("해당 이름(%s)과 이메일(%s)은 이미 사용중 입니다.", name, email));
		}
		//중복 이메일 존재해도 회원가입 가능한 오류 잡기
		System.out.println("==================================================================================================");
		System.out.println("==================================================================================================");
		System.out.println(name);
		System.out.println(email);
		System.out.println(oldMember);
		

//		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		//회원가입시 비밀번호 암호화 하여 저장
		loginPw = passwordEncoder.encode(loginPw);
		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);

		int id = memberRepository.getLastInsertId();

		return ResultData.from("S-1", "회원가입이 완료 되었습니다.", "id", id);
	}

	private Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name, email);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
	

}
