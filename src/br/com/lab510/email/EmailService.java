package br.com.lab510.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import br.com.lab510.manipulator.FileManipulator;

public class EmailService {
	
	public static void sendEmail(String content, String to, String subject) {
		
		HtmlEmail email = configHtmlEmail();

		try {
			
			email.addTo(to, "New account - LAB510");
			email.setFrom("leolopes4@gmail.com", "Team LAB510");
			email.setSubject(subject);
			email.setHtmlMsg(content);			
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
	
	public static String getEmailTemplate (String content) {
		String template = null;
		FileManipulator archive = new FileManipulator();
		
		if("senha_provisoria".equals(content)) {
			template = archive.readFile("template_senha_provisoria.html");
			
		}else if ("evento_callback".equals(content)) {
			template = archive.readFile("template_evento_callback.html");
		}
		
		return template;
		
	}	
	
}
