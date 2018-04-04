package br.com.lab510.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import br.com.lab510.autenticacao.Gerator;
import br.com.lab510.manipulador.ArquivoGenerico;
import br.com.lab510.manipulador.Texto;

public class ServicoEmail {
	
	public static void enviaEmail(String conteudo, String destinatario, String assunto) {
		
		HtmlEmail email = configHtmlEmail();

		try {
			
			email.addTo(destinatario, "Novo cliente - LAB510");
			email.setFrom("leolopes4@gmail.com", "Equipe LAB510");
			email.setSubject(assunto);
			email.setHtmlMsg(conteudo);			
			email.send();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SimpleEmail configEmail () {
		
		SimpleEmail email = new SimpleEmail();

		email.setDebug(true);
		email.setStartTLSEnabled(true);
		email.setHostName("smtp.gmail.com");

		email.setSmtpPort(465);
		email.setAuthentication("leonardo.nascimento@maxipago.com", "Le@2uzumaki");
		email.setSSL(true);
		email.setTLS(true);
		
		return email;
	}
	
	public static HtmlEmail configHtmlEmail() {
		HtmlEmail email = new HtmlEmail();
		
		email.setSSLOnConnect(true);
		email.setHostName("smtp.gmail.com");
		email.setSslSmtpPort("465");
		email.setAuthenticator(new DefaultAuthenticator ("leonardo.nascimento@maxipago.com", "Le@2uzumaki"));
		
		return email;
	}
	
	public static String pegaTemplate (String conteudo) {
		String template = null;
		ArquivoGenerico arquivo = new ArquivoGenerico();
		
		if("senha_provisoria".equals(conteudo)) {
			template = arquivo.leArquivo("template_senha_provisoria.html");
			
		}else if ("evento_callback".equals(conteudo)) {
			template = arquivo.leArquivo("template_evento_callback.html");
		}
		
		return template;
		
	}	
	
}
