package br.com.lab510.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import br.com.lab510.manipulator.FileManipulator;

public class ConnectionFactory {

	private static String USER = null;
	private static String PSSWD = null;
	private static String DRIVER = null;
	private static String URL = null;

	static {
		Properties props = FileManipulator
				.getProperties("C:\\Users\\Leonardo\\Projetos\\lab510-backend\\properties\\dadosBD.properties");
		USER = props.getProperty("prop.mysql.user");
		PSSWD = props.getProperty("prop.mysql.psswd");
		DRIVER = props.getProperty("prop.mysql.driver");
		URL = props.getProperty("prop.mysql.url");
	}

	public static Connection openConnection() {

		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PSSWD);
			return conn;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
