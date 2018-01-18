package br.com.lab510.recursos;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
		
		Usuario usuarioCriado = UsuarioDao.buscaUsuarioNaBase(usuario.getCpf());
		
		URI url = URI.create("/usuario/LAB510/v1/" + usuarioCriado.getId());
		return Response.created(url).build();
	}

}
