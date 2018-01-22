package br.com.lab510.recursos;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.google.gson.Gson;

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
		
		String usuarioJson = new Gson().toJson(usuario);
		
		return usuarioJson;
	}
	

}
