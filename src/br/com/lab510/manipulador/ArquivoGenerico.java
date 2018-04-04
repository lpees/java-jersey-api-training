package br.com.lab510.manipulador;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ArquivoGenerico  {

	public String leArquivo(String nomeArquivo) {
		StringBuilder conteudoEmail = new StringBuilder();
				
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(nomeArquivo), "UTF-8"));
			
			String linhaAtual = br.readLine();

			while (linhaAtual != null) {
				conteudoEmail.append(linhaAtual).append("\n");
				linhaAtual = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conteudoEmail.toString();
	}

	public static String substituirPalavra(String conteudo, String localizaPalavra, String substituiPalavra) {
		return conteudo.replace(localizaPalavra, substituiPalavra);
	}

}
