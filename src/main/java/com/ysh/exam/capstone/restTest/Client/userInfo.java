package com.ysh.exam.capstone.restTest.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class userInfo {
	 private int id;
	 private String created_at;
	 private String name;
	 private String login_id;
	 private String nickname;
	 private String email;
}
