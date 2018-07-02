package br.com.lab510.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.lab510.models.Authentication;

public class PsswdDao {

	public static void addPsswdBd(Authentication login) {

		Connection conn = ConnectionFactory.openConnection();
	
		try {
			conn.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO CREDENTIALS(");
			sql.append("USER_ID, DOCUMENT, HASH)");
			sql.append("VALUES (?, ?, ?)");

			PreparedStatement insert = conn.prepareStatement(sql.toString());
			
			insert.setLong(1, login.getUserId());
			insert.setLong(2, login.getDocument());
			insert.setString(3, login.getPsswd());

			insert.executeUpdate();

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
	
	public static String getPsswd (long document) {
		Connection conn = ConnectionFactory.openConnection();
		StringBuilder sql = new StringBuilder();
		
		try {
			conn.setAutoCommit(false);
			sql.append("SELECT * ");
			sql.append("FROM CREDENTIALS ");
			sql.append("WHERE DOCUMENT = ?");
			
			PreparedStatement query = conn.prepareStatement(sql.toString());
			query.setLong(1, document);
			
			ResultSet queryRs = query.executeQuery();
			String psswdHashed = null;
			if(queryRs.next()) {
				psswdHashed = queryRs.getString("HASH");
			}
			
			return psswdHashed;
			
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
	
	public static void updatePsswd(Authentication login) {
		Connection conn = ConnectionFactory.openConnection();
		StringBuilder sql = new StringBuilder();
		
		try {
			conn.setAutoCommit(false);
			sql.append("UPDATE CREDENTIALS ");
			sql.append("SET HASH = ? ");
			sql.append("WHERE DOCUMENT = ?");
			
			PreparedStatement query = conn.prepareStatement(sql.toString());
			query.setString(1, login.getNewPsswd());
			query.setLong(2, login.getDocument());
			
			query.executeUpdate();
			
			conn.commit();
		}catch (SQLException e) {
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

}
