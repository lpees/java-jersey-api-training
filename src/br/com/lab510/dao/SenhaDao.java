package br.com.lab510.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.lab510.modelos.Login;

public class SenhaDao {

	public static void salvaSenhaNaBase(Login senha) {

		Connection conn = Conexoes.abreConexaoComOBanco();

		try {
			conn.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO LOGIN(");
			sql.append("ID_USUARIO, EMAIL, SENHA_CRIPTOGRAFADA)");
			sql.append("VALUES (?, ?, ?)");

			PreparedStatement inserir = conn.prepareStatement(sql.toString());
			
			inserir.setLong(1, senha.getIdUsuario());
			inserir.setString(2, senha.getEmail());
			inserir.setString(3, senha.getSenha());

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
	
	public static String buscaSenhaNaBase (long cpf) {
		Connection conn = Conexoes.abreConexaoComOBanco();
		StringBuilder sql = new StringBuilder();
		
		try {
			conn.setAutoCommit(false);
			sql.append("SELECT * ");
			sql.append("FROM LOGIN ");
			sql.append("WHERE CPF = ?");
			
			PreparedStatement consultar = conn.prepareStatement(sql.toString());
			consultar.setLong(1, cpf);
			
			ResultSet resultadoDaConsulta = consultar.executeQuery();
			String hashSenha = null;
			if(resultadoDaConsulta.next()) {
				hashSenha = resultadoDaConsulta.getString("SENHA_CRIPTOGRAFADA");
			}
			
			return hashSenha;
			
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
