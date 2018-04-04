package br.com.lab510.autenticacao;

import java.nio.charset.StandardCharsets;

import javax.ws.rs.core.Cookie;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

import com.google.common.hash.Hashing;

public class Gerator {
	
	public static String senhaProvisoria() {
		RandomStringGenerator senha =
		        new RandomStringGenerator.Builder()
		                .withinRange('0', 'z')
		                .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
		                .build();
		
		return senha.generate(8);
		
	}
	
	public static String gerarHash(String senha) {
		 String hashed = Hashing.sha256()
			        .hashString(senha, StandardCharsets.UTF_8)
			        .toString();
		 
		 return hashed;
		
	}
	
	public static String token () {
		RandomStringGenerator senha =
		        new RandomStringGenerator.Builder()
		                .withinRange('0', 'z')
		                .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
		                .build();
		
		return senha.generate(20);
	}
	
}
