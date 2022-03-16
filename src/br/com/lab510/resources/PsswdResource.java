package br.com.lab510.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.lab510.authentication.AuthenticateLogin;
import br.com.lab510.authentication.Generator;
import br.com.lab510.dao.PsswdDao;
import br.com.lab510.models.Authentication;
import br.com.lab510.models.MessageResponse;

@Path("password")
public class PsswdResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePsswd(String content) {
		
		MessageResponse message = null;
		
		Authentication updatePsswd = new Gson().fromJson(content, Authentication.class);
		String hashPsswd = Generator.getHashPsswd(updatePsswd.getNewPsswd());
		
		if(AuthenticateLogin.verifyPsswd(updatePsswd)) {
			updatePsswd.setNewPsswd(hashPsswd);
			PsswdDao.updatePsswd(updatePsswd);
			message = new MessageResponse(0, "Senha atualizada com sucesso");
			
		}else {
			message = new MessageResponse(1, "Senha atual esta incorreta");
		}
		
		return Response.ok().entity(new Gson().toJson(message)).build();		
		
	}

}
