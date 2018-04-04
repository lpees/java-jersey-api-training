package br.com.lab510.recursos;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.lab510.autenticacao.AutenticaLogin;
import br.com.lab510.autenticacao.Gerator;

import br.com.lab510.dao.TokenDao;
import br.com.lab510.dao.UsuarioDao;

import br.com.lab510.modelos.Login;
import br.com.lab510.modelos.Mensagem;

@Path("autenticacao")
public class RecursoAutenticacao {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response autenticacaoLogin (String conteudo) {		
		
		Login login = new Gson().fromJson(conteudo, Login.class);
		
		boolean senhaVerificada = AutenticaLogin.verificaSenha(login.getCpf(), login.getSenha());
		
		String token = Gerator.token();
		Mensagem mensagem = null;
		NewCookie cookie = null;
		
			if(senhaVerificada) {
			cookie = new NewCookie("SSID", token);
			TokenDao.salvaTokenNaBase(token);
			mensagem = new Mensagem(0, "Login efetuado com sucesso");
			
			
		}else {
			mensagem = new Mensagem(1, "Login ou senha invalido");
		}
		
		return Response.ok().entity(new Gson().toJson(mensagem)).cookie(cookie).build();
	}

}
