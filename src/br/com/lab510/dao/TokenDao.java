package br.com.lab510.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.lab510.modelos.Login;

public class TokenDao {
	
	public static void salvaTokenNaBase(long id, String token) {

		Connection conn = Conexoes.abreConexaoComOBanco();

		try {
			conn.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO TOKEN(");
			sql.append("IDUSUARIO, TOKEN)");
			sql.append("VALUES (?, ?)");

			PreparedStatement inserir = conn.prepareStatement(sql.toString());
			
			inserir.setLong(1, id);
			inserir.setString(2, token);

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
		Connection conn = Conexoes.abreConexaoComOBanco();
		StringBuilder sql = new StringBuilder();
		
		try {
			conn.setAutoCommit(false);
			sql.append("SELECT * ");
			sql.append("FROM TOKEN ");
			sql.append("WHERE IDUSUARIO = ? ");
			
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
