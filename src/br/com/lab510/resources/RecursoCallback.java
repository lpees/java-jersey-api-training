package br.com.lab510.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import br.com.lab510.email.EmailService;
import br.com.lab510.manipulator.Text;
import br.com.lab510.xstream.ManipuladorXML;


@Path("callback")
public class RecursoCallback {
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public static String recebeConteudo (String conteudo) {
		
		String jsonResponse = new Gson().toJson(ManipuladorXML.formataXmlParaObj(conteudo));
		String template = Text.replaceWords(EmailService.getEmailTemplate("evento_callback"), "{#conteudo}", jsonResponse);
		EmailService.sendEmail(template, "leolopes4@gmail.com", "Você recebeu um novo evento de notificação");
		
	return jsonResponse;
	}	
}
