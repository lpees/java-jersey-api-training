package br.com.lab510.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.lab510.modelos.Usuario;

public class UsuarioDao {

	public void salvaUsuarioNaBase(Usuario usuario) {
		Connection conexaoMysql = Conexoes.abreConexaoComOBanco();

		try {
			conexaoMysql.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO USUARIO(");
			sql.append("NOME, SOBRENOME, EMAIL, CPF)");
			sql.append("VALUES (?, ?, ?, ?)");

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
		Connection conexaoMysql = Conexoes.abreConexaoComOBanco();
		
		try {
			conexaoMysql.setAutoCommit(false);
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
		Connection conexaoMysql = Conexoes.abreConexaoComOBanco();
		
		try {
			conexaoMysql.setAutoCommit(false);
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
	
	public static void deletaUsuarioDaBase (long id) {
		Connection conexaoMysql = Conexoes.abreConexaoComOBanco();
		
		try {
			conexaoMysql.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM ");
			sql.append("USUARIO ");
			sql.append("WHERE ID = ?");
			
			PreparedStatement deletar = conexaoMysql.prepareStatement(sql.toString());
			deletar.setLong(1, id);
			
			deletar.executeUpdate();
			
			conexaoMysql.commit();			
		}catch (SQLException e) {
			try {
				conexaoMysql.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				conexaoMysql.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void atualizaUsuarioNaBase (Long id, Usuario usuario) {
		Connection conexaoMysql = Conexoes.abreConexaoComOBanco();
		
		try {
			conexaoMysql.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE USUARIO ");
			sql.append("SET NOME = ?, ");
			sql.append("SOBRENOME = ?, ");
			sql.append("EMAIL = ?, ");
			sql.append("CPF = ? ");			
			sql.append("WHERE ID = ?;");
			
			PreparedStatement atualizar = conexaoMysql.prepareStatement(sql.toString());
			atualizar.setString(1, usuario.getNome());
			atualizar.setString(2, usuario.getSobrenome());
			atualizar.setString(3, usuario.getEmail());
			atualizar.setLong(4, usuario.getCpf());
			atualizar.setLong(5, id);
			
			atualizar.executeUpdate();
			
			conexaoMysql.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			try {
				conexaoMysql.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				conexaoMysql.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}


