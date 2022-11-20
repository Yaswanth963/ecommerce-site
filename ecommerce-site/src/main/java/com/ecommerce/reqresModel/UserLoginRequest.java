package com.ecommerce.reqresModel;

/**
 * 
 * @author yaswanth.perumalla
 * 
 *         User login data request format
 *
 */
public class UserLoginRequest {
	public UserLoginRequest() {
	}

	private String loginId;
	private String password;

	public String getUserName() {
		return loginId;
	}

	public void setUserName(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
