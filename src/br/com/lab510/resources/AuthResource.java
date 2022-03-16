package br.com.lab510.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.lab510.authentication.AuthenticateLogin;
import br.com.lab510.authentication.Generator;
import br.com.lab510.dao.AuthenticationDao;
import br.com.lab510.models.Authentication;
import br.com.lab510.models.MessageResponse;
import br.com.lab510.models.UserLogged;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Path("authentication")
public class AuthResource {
	
	MessageResponse message = null;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response authLogin(String content) {
		Authentication login = new Gson().fromJson(content, Authentication.class);
				
		if (AuthenticateLogin.verifyPsswd(login)) {
			
			message = new MessageResponse(0, "Login efetuado com sucesso");
			message.setUserLogged(new UserLogged(login.getDocument(), Generator.createToken(login)));
			
			return Response.ok().entity(new Gson().toJson(message)).build();
		} else {
			message = new MessageResponse(1, "Login ou senha invalido");
			
			return Response.ok().entity(new Gson().toJson(message)).status(Response.Status.OK).build();
		}
		
				
	}
	
	@Path("token")
	@POST()
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response authToken(String content, @HeaderParam(Generator.TOKEN_HEADER) String authToken) {
		Authentication auth = new Gson().fromJson(content, Authentication.class);
		
		Jws<Claims> jwt = Generator.decode(authToken);
		String extractToken = jwt.getBody().getSubject();
		
		if(String.valueOf(auth.getDocument()).equals(extractToken)) {
			message = new MessageResponse(0, "Usuario autentitcado com sucesso");
		} else {
			message = new MessageResponse(1, "Usuario não autentitcado");
		}
		
		return Response.ok().entity(new Gson().toJson(message)).build();
	}

}
