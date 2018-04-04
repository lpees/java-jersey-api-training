package br.com.lab510.modelos;

public class Login {
	
	private long idUsuario;
	private long cpf;
	private String email;	
	private String senha;
	
	public Login(long idUsuario, long cpf, String senha) {
		this.idUsuario = idUsuario;
		this.cpf = cpf;
		this.senha = senha;
	}
	
	public long getCpf() {
		return cpf;
	}
	
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	
	public long getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}
