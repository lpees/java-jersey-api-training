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
import com.google.gson.GsonBuilder;

import br.com.lab510.dados.UsuarioDao;
import br.com.lab510.modelos.Usuario;

@Path("usuario")
public class RecursoUsuario {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response adicionarUsuario (String conteudo) {
		Usuario usuario = new Gson().fromJson(conteudo, Usuario.class);
		new UsuarioDao().salvaUsuarioNaBase(usuario);
		
		Long idUsuario = UsuarioDao.buscaIdDoUsuarioNaBase(usuario.getCpf());
		
		URI url = URI.create("/usuario/LAB510/v1/" + idUsuario);
		return Response.created(url).build();
	}
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String buscaUsuario (@PathParam("id") Long id) {
		Usuario usuario = UsuarioDao.buscaUsuarioNaBase(id);
		System.out.println("certo");
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
