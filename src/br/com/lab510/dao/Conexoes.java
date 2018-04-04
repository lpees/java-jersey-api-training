package br.com.lab510.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import br.com.lab510.manipulador.ArquivoProperties;

public class Conexoes {
	
	private static String USUARIO = null;
	private static String SENHA = null;
	private static String DRIVER = null;
	private static String URL = null;
	
	static {
		Properties props = ArquivoProperties.pegaProperties("C:\\Users\\Leonardo\\Projetos\\Workspace\\lab510-backend\\properties\\dadosBD.properties");
		USUARIO = props.getProperty("prop.mysql.usuario");
		SENHA = props.getProperty("prop.mysql.senha");
		DRIVER = props.getProperty("prop.mysql.driver");
		URL = props.getProperty("prop.mysql.url");
	}
	

	public static Connection abreConexaoComOBanco() {

		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
			System.out.println("certo");
			return conn;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
