package br.com.lab510.models;

public class UserLogged {
	
	private Long username;
	private String token;
	
	public UserLogged(Long username, String token) {
		this.username = username;
		this.token = token;
	}
	
	public Long getUsername() {
		return username;
	}
	public void setUsername(Long username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	
}
