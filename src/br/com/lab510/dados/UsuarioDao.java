package br.com.lab510.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.lab510.modelos.Usuario;

public class UsuarioDao {

	public void salvaUsuarioNaBase(Usuario usuario) {
		Connection conexaoOracle = Conexao.abreConexaoComOBanco();

		try {
			conexaoOracle.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO USUARIO(");
			sql.append("ID, NOME, SOBRENOME, EMAIL, CPF)");
			sql.append("VALUES (SQC_ID.NEXTVAL, ?, ?, ?, ?)");
			
			System.out.println(sql.toString());

			PreparedStatement inserir = conexaoOracle.prepareStatement(sql.toString());

			inserir.setString(1, usuario.getNome());
			inserir.setString(2, usuario.getSobrenome());
			inserir.setString(3, usuario.getEmail());
			inserir.setLong(4, usuario.getCpf());

			inserir.executeUpdate();

			conexaoOracle.commit();
		} catch (SQLException e) {
			try {
				conexaoOracle.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (conexaoOracle != null)
			try {
				conexaoOracle.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Usuario buscaUsuarioNaBase(Long cpf){
		Connection conexaoOracle = Conexao.abreConexaoComOBanco();
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * ");
			sql.append("FROM USUARIO ");
			sql.append("WHERE CPF = ?");
			
			PreparedStatement consultar = conexaoOracle.prepareStatement(sql.toString());
			consultar.setLong(1, cpf);
			
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
			
		}catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
		
		
	}

}
