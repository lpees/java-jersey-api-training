package br.com.lab510.modelos;

public class Mensagem {
	
	private int codeId;
	private String texto;
	private String token;
	
	public Mensagem(int codeId, String texto) {
		this.codeId = codeId;
		this.texto = texto;
	}
	
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public int getCodeId() {
		return codeId;
	}

	public void setCodeId(int codeId) {
		this.codeId = codeId;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
	

}
