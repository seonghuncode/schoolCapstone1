package talkToServer;

import com.google.gson.annotations.SerializedName;

public class UserInfo {
	// github에서 받아올 정보만 만들어 진다
	// DTO클래스

	@SerializedName("login")
	private String userId;
	private int followers;
	private int following;

}
