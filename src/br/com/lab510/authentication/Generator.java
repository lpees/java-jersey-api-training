package br.com.lab510.authentication;

import java.nio.charset.StandardCharsets;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

import com.google.common.hash.Hashing;

import br.com.lab510.models.Authentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Generator {
	
	private static final String KEY = "SECRET_TOKEN";
    public static final String TOKEN_HEADER = "Authentication";
	
	public static String getTmpPsswd() {
		RandomStringGenerator psswd =
		        new RandomStringGenerator.Builder()
		                .withinRange('0', 'z')
		                .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
		                .build();
		
		return psswd.generate(8);
		
	}
	
	public static String getHashPsswd(String psswd) {
		 String hashed = Hashing.sha256()
			        .hashString(psswd, StandardCharsets.UTF_8)
			        .toString();
		 
		 return hashed;
		
	}

	public static String createToken (Authentication login) {
	        return Jwts.builder()
	                .setSubject(String.valueOf(login.getDocument()))
	                .signWith(SignatureAlgorithm.HS512, KEY)
	                .compact();
	}

	public static Jws<Claims> decode(String token){
		return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
		}
	
}
