package com.rivigo.responseDto;

import com.rivigo.database.classes.User;

public class LoginResponseDto extends BaseResponse {

	private User user;
	private String accessToken;

	public LoginResponseDto() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
