package com.ysh.exam.capstone.vo;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@ToString
public class UserNowId {

	private String loginId;
	
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	public String getLoginId() {
		return loginId;
	}


}
