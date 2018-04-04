package br.com.lab510.manipulador;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ArquivoProperties {
	
	public static Properties pegaProperties(String dirArquivo) {
		
		Properties props = new Properties();
		
		try {
			props.load(new FileInputStream(dirArquivo));
		}catch (IOException e) {
			e.printStackTrace();
		}
	
		return props;
	}
}