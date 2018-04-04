package br.com.lab510.recursos;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import br.com.lab510.email.ServicoEmail;
import br.com.lab510.manipulador.Texto;
import br.com.lab510.xstream.ManipuladorXML;


@Path("callback")
public class RecursoCallback {
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public static String recebeConteudo (String conteudo) {
		
		String jsonResponse = new Gson().toJson(ManipuladorXML.formataXmlParaObj(conteudo));
		String template = Texto.localizarSubstituir(ServicoEmail.pegaTemplate("evento_callback"), "{#conteudo}", jsonResponse);
		ServicoEmail.enviaEmail(template, "leolopes4@gmail.com", "Você recebeu um novo evento de notificação");
		
	return jsonResponse;
	}	
}
