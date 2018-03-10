package br.com.lab510.dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static final String USUARIO = "root";
	private static final String SENHA = "";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/lab510";

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
	
	public static void main(String[] args) {
		abreConexaoComOBanco();
	}
}
