package br.com.lab510.models;

public class Authentication {
	
	private long UserId;
	private long document;
	private String email;	
	private String psswd;
	private String newPsswd;
	
	public Authentication(long UserId, long document, String psswd) {
		this.document = document;
		this.psswd = psswd;
		this.UserId = UserId;
	}

	public String getNewPsswd() {
		return newPsswd;
	}


	public void setNewPsswd(String newPsswd) {
		this.newPsswd = newPsswd;
	}


	public long getUserId() {
		return UserId;
	}

	public void setUserId(long userId) {
		UserId = userId;
	}

	public long getDocument() {
		return document;
	}

	public void setDocument(long document) {
		this.document = document;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPsswd() {
		return psswd;
	}

	public void setPsswd(String psswd) {
		this.psswd = psswd;
	}

}
