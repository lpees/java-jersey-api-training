package br.com.lab510.dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static final String USUARIO = "system";
	private static final String SENHA = "123456";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";

	public static Connection abreConexaoComOBanco() {

		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);

			return conn;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

}
