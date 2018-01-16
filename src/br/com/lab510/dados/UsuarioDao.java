package br.com.lab510.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.lab510.modelos.Usuario;

public class UsuarioDao {

	public void salvaUsuarioNaBase(Usuario usuario) {
		Connection conexaoOracle = Conexao.abreConexaoComOBanco();

		try {
			conexaoOracle.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO USUARIO(");
			sql.append("ID, NOME, SOBRENOME, EMAIL, CPF");
			sql.append("VALUES (SQC_ID.NEXTVAL, ?, ?, ?, ?");

			PreparedStatement inserir = conexaoOracle.prepareStatement(sql.toString());

			inserir.setString(1, usuario.getNome());
			inserir.setString(2, usuario.getSobrenome());
			inserir.setString(3, usuario.getEmail());
			inserir.setLong(4, usuario.getCpf());

			inserir.executeUpdate();

			conexaoOracle.commit();
		} catch (SQLException e) {
			conexaoOracle.rollback();
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

}
