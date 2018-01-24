package br.com.lab510.modelos;

public class Usuario {
	
	private long id, cpf;
	private String nome, sobrenome, email;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "ID: " + getId() + "Nome: " + getNome() + "Sobrenome: " + getSobrenome() + "Email: " + getEmail() + "CPF: " + getCpf() ;
	}
	

}
