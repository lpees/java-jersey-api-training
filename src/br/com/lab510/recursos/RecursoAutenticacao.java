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
	
	String token = Gerator.token();
	Mensagem mensagem = null;
	HttpSession sessao = null;
	NewCookie cookie = null;

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response autenticacaoLogin (String conteudo) {		
		Login login = new Gson().fromJson(conteudo, Login.class);
		Long idUsuario = UsuarioDao.buscaIdDoUsuarioNaBase(login.getCpf());
		
		boolean senhaVerificada = AutenticaLogin.verificaSenha(idUsuario, login.getSenha());
		
		
			if(senhaVerificada) {
			sessao.setAttribute("usuario", token);
			sessao.setMaxInactiveInterval(30*60);
			cookie = new NewCookie("SSID", token);
			
			mensagem = new Mensagem(0, "Login efetuado com sucesso");
			mensagem.setToken(token);
			
			TokenDao.salvaTokenNaBase(idUsuario, token);	
			
		}else {
			
			mensagem = new Mensagem(1, "Login ou senha invalido");
			mensagem.setToken(null);
		}
		
		return Response.ok().entity(new Gson().toJson(mensagem)).cookie(cookie).build();
	}

}
