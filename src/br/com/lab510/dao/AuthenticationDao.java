package br.com.lab510.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.lab510.models.Authentication;

public class AuthenticationDao {
	
	public static void salvaTokenNaBase(String token) {

		Connection conn = ConnectionFactory.openConnection();

		try {
			conn.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO AUTHENTICATION(");
			sql.append("TOKEN)");
			sql.append("VALUES (?)");

			PreparedStatement inserir = conn.prepareStatement(sql.toString());
			;
			inserir.setString(1, token);

			inserir.executeUpdate();

			conn.commit();

		} catch (SQLException e) {
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
			
		} finally {
			
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}
	
	public static String buscaTokenNaBase (long idUsuario) {
		Connection conn = ConnectionFactory.openConnection();
		StringBuilder sql = new StringBuilder();
		
		try {
			conn.setAutoCommit(false);
			sql.append("SELECT * ");
			sql.append("FROM AUTHENTICATION ");
			sql.append("WHERE TOKEN = ? ");
			
			PreparedStatement consultar = conn.prepareStatement(sql.toString());
			consultar.setLong(1, idUsuario);
			
			ResultSet resultadoDaConsulta = consultar.executeQuery();
			String tokenSalvo = null;
			
			if(resultadoDaConsulta.next()) {
				tokenSalvo = resultadoDaConsulta.getString("TOKEN");
			}
			
			return tokenSalvo;
			
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
