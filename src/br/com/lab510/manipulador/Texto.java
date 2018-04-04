package br.com.lab510.manipulador;

public class Texto {
	
	public static String localizarSubstituir (String conteudo, String localizar, String substituir) {
		
		return conteudo.replace(localizar, substituir);
	}

}
