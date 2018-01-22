package br.com.lab510.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.lab510.modelos.Usuario;

public class UsuarioDao {

	public void salvaUsuarioNaBase(Usuario usuario) {
		Connection conexaoMysql = Conexao.abreConexaoComOBanco();

		try {
			conexaoMysql.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO USUARIO(");
			sql.append("NOME, SOBRENOME, EMAIL, CPF)");
			sql.append("VALUES (?, ?, ?, ?)");
			
			System.out.println(sql.toString());

			PreparedStatement inserir = conexaoMysql.prepareStatement(sql.toString());

			inserir.setString(1, usuario.getNome());
			inserir.setString(2, usuario.getSobrenome());
			inserir.setString(3, usuario.getEmail());
			inserir.setLong(4, usuario.getCpf());

			inserir.executeUpdate();

			conexaoMysql.commit();
		} catch (SQLException e) {
			try {
				conexaoMysql.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (conexaoMysql != null)
			try {
				conexaoMysql.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Long buscaIdDoUsuarioNaBase(Long cpf){
		Connection conexaoMysql = Conexao.abreConexaoComOBanco();
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * ");
			sql.append("FROM USUARIO ");
			sql.append("WHERE CPF = ?");
			
			PreparedStatement consultar = conexaoMysql.prepareStatement(sql.toString());
			consultar.setLong(1, cpf);
			
			ResultSet resultadoDaConsulta = consultar.executeQuery();
			long id = 0;
			if(resultadoDaConsulta.next()) {
				id = resultadoDaConsulta.getLong("ID");
			}

			return id;
			
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				conexaoMysql.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static Usuario buscaUsuarioNaBase (Long id) {
		Connection conexaoMysql = Conexao.abreConexaoComOBanco();
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * ");
			sql.append("FROM USUARIO ");
			sql.append("WHERE ID = ?" );
			
			PreparedStatement consultar = conexaoMysql.prepareStatement(sql.toString());
			consultar.setLong(1, id);
			
			ResultSet resultadoDaConsulta = consultar.executeQuery();
			
			Usuario usuario = null;
			
			if(resultadoDaConsulta.next()) {
				 usuario = new Usuario();
				usuario.setId(resultadoDaConsulta.getLong("ID"));
				usuario.setNome(resultadoDaConsulta.getString("NOME"));
				usuario.setSobrenome(resultadoDaConsulta.getString("SOBRENOME"));
				usuario.setEmail(resultadoDaConsulta.getString("EMAIL"));
				usuario.setCpf(resultadoDaConsulta.getLong("CPF"));
			}
			
			return usuario;
			
			}catch (SQLException e) {
				e.printStackTrace();
				
			return null;
			
			}finally {
				try {
					conexaoMysql.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}			
	}
}


