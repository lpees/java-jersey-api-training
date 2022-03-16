package br.com.lab510.resources;

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

import br.com.lab510.authentication.Generator;
import br.com.lab510.dao.PsswdDao;
import br.com.lab510.dao.UserDao;
import br.com.lab510.email.EmailService;
import br.com.lab510.manipulator.Text;
import br.com.lab510.models.Authentication;
import br.com.lab510.models.User;


@Path("user")
public class UserResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser (String content) {
		User user = new Gson().fromJson(content, User.class);
		new UserDao().addUserBd(user);		
		
		long idUser = UserDao.getUserById(user.getDocument());
		String tmpPsswd = Generator.getTmpPsswd();
		
		EmailService.sendEmail(Text.replaceWords(EmailService.getEmailTemplate("senha_provisoria"), "{#senha}", tmpPsswd), user.getEmail(), "Este é seu primeiro acesso ao LAB510");
		
		PsswdDao.addPsswdBd(new Authentication(idUser, 
				user.getDocument(), 
				Generator.getHashPsswd(tmpPsswd)));
		
		URI url = URI.create("user/" + idUser);
		return Response.created(url).build();
	}
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getUser (@PathParam("id") Long id) {
		return new Gson().toJson(UserDao.getUser(id));
	}
	
	@Path("{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser (@PathParam("id") Long id, String content) {
		new UserDao().updateUser(id, new Gson().fromJson(content, User.class));
	
		return Response.ok().build();
	}
	
	@Path("{id}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response removeUser (@PathParam("id") Long id) {
		UserDao.removeUser(id);
		return Response.ok().build();
	}
	
}
