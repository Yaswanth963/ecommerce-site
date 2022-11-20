package com.ecommerce.reqresModel;

/**
 * 
 * @author yaswanth.perumalla
 * 
 *         User response data format
 *
 */
public class UserResponse {

	private boolean success;
	private String message;

	public UserResponse() {
	}

	public UserResponse(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
