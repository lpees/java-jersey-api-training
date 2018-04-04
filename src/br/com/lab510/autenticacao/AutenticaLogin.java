package br.com.lab510.autenticacao;

import br.com.lab510.dao.SenhaDao;

public class AutenticaLogin {
	
	public static boolean verificaSenha (long id, String senha) {
		
		boolean autenticacao;
		
		String senhaDigitada = Gerator.gerarHash(senha);
		String senhaNaBase = SenhaDao.buscaSenhaNaBase(id);
		
		if(senhaDigitada.equals(senhaNaBase)) {
			autenticacao = true;
		}else {
			autenticacao = false;
		}
		
		return autenticacao;
		
	}
	

}
