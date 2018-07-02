package br.com.lab510.authentication;

import br.com.lab510.dao.PsswdDao;
import br.com.lab510.models.Authentication;

public class AuthenticateLogin {

	public static boolean verifyPsswd(Authentication login) {

		boolean isAutheticate;

		if (Generator.getHashPsswd(login.getPsswd()).equals(PsswdDao.getPsswd(login.getDocument()))) {
			isAutheticate = true;
		} else {
			isAutheticate = false;
		}

		return isAutheticate;

	}

}
