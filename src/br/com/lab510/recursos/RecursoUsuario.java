package br.com.lab510.recursos;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;

import br.com.lab510.dao.SenhaDao;
import br.com.lab510.dao.UsuarioDao;
import br.com.lab510.email.ServicoEmail;
import br.com.lab510.manipulador.Texto;
import br.com.lab510.modelos.Login;
import br.com.lab510.modelos.Usuario;
import br.com.lab510.autenticacao.Gerator;


@Path("usuario")
public class RecursoUsuario {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response adicionarUsuario (String conteudo) {
		Usuario usuario = new Gson().fromJson(conteudo, Usuario.class);
		new UsuarioDao().salvaUsuarioNaBase(usuario);		
		
		long idUsuario = UsuarioDao.buscaIdDoUsuarioNaBase(usuario.getCpf());
		String senhaProvisoria = Gerator.senhaProvisoria();
		
		String template = Texto.localizarSubstituir(ServicoEmail.pegaTemplate("senha_provisoria"), "{#senha}", senhaProvisoria);
		ServicoEmail.enviaEmail(template, usuario.getEmail(), "Este é seu primeiro acesso ao LAB510");
		
		SenhaDao.salvaSenhaNaBase(new Login(idUsuario, 
				usuario.getCpf(), 
				Gerator.gerarHash(senhaProvisoria)));
		
		URI url = URI.create("lab510/v1/usuario/" + idUsuario);
		return Response.created(url).build();
	}
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String buscaUsuario (@PathParam("id") Long id) {
		Usuario usuario = UsuarioDao.buscaUsuarioNaBase(id);
		
		String usuarioJson = new Gson().toJson(usuario);
		
		return usuarioJson;
	}
	
	@Path("{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizaUsuario (@PathParam("id") Long id, String conteudo) {
		Usuario usuario = new Gson().fromJson(conteudo, Usuario.class);
		new UsuarioDao().atualizaUsuarioNaBase(id, usuario);
	
		return Response.ok().build();
	}
	
	@Path("{id}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response removeUsuario (@PathParam("id") Long id) {
		UsuarioDao.deletaUsuarioDaBase(id);
		
		return Response.ok().build();
	}
	
}
