package com.ysh.exam.capstone.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	//컨트롤러 에서 화원가입이 진행이 되고 결과를 Member로 리턴해주기 위해서 Member를 만들어 준다
	//sqlyog에서 테이블 순서는 중요도 순으로 맞추어 적어주는 것이 좋다.
	
	
	private int id;
	private String regDate;
	private String updateDate;
	private String loginId;
	private String loginPw;
	private int authLevel;
	private String name;
	private String nickname;
	private String cellphoneNo;
	private String email;
	private boolean delStatus;  //tinyint의 경우 boolean으로 치환이 되는 규약이 있다.
	private boolean delDate;
	

}

