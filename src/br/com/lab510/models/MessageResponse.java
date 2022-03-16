package br.com.lab510.models;

public class MessageResponse {
	
	private int responseCode;
	private String responseMessage;
	private UserLogged userLogged;
	
	public MessageResponse(int responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public UserLogged getUserLogged() {
		return userLogged;
	}

	public void setUserLogged(UserLogged userLogged) {
		this.userLogged = userLogged;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	
	

}
