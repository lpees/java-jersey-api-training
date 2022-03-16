package br.com.lab510.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.lab510.models.User;

public class UserDao {

	public void addUserBd(User user) {
		Connection connMysql = ConnectionFactory.openConnection();

		try {
			connMysql.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO USER(");
			sql.append("FIRSTNAME, LASTNAME, EMAIL, DOCUMENT)");
			sql.append("VALUES (?, ?, ?, ?)");

			PreparedStatement insert = connMysql.prepareStatement(sql.toString());

			insert.setString(1, user.getFirstName());
			insert.setString(2, user.getLastName());
			insert.setString(3, user.getEmail());
			insert.setLong(4, user.getDocument());

			insert.executeUpdate();
			
			connMysql.commit();
			
		} catch (SQLException e) {
			try {
				connMysql.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (connMysql != null)
			try {
				connMysql.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static Long getUserById(Long document){
		Connection connMysql = ConnectionFactory.openConnection();
		
		try {
			connMysql.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * ");
			sql.append("FROM USER ");
			sql.append("WHERE DOCUMENT = ?");
			
			PreparedStatement query = connMysql.prepareStatement(sql.toString());
			query.setLong(1, document);
			
			ResultSet rsQuery = query.executeQuery();
			long id = 0;
			if(rsQuery.next()) {
				id = rsQuery.getLong("ID");
			}

			return id;
			
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				connMysql.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static User getUser (Long id) {
		Connection connMysql = ConnectionFactory.openConnection();
		
		try {
			connMysql.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * ");
			sql.append("FROM USER ");
			sql.append("WHERE ID = ?" );
			
			PreparedStatement query = connMysql.prepareStatement(sql.toString());
			query.setLong(1, id);
			
			ResultSet rsQuery = query.executeQuery();
			
			User user = null;
			
			if(rsQuery.next()) {
				user = new User();
				user.setId(rsQuery.getLong("ID"));
				user.setFirstName(rsQuery.getString("FIRSTNAME"));
				user.setLastName(rsQuery.getString("LASTNAME"));
				user.setEmail(rsQuery.getString("EMAIL"));
				user.setDocument(rsQuery.getLong("DOCUMENT"));
			}
			
			return user;
			
			}catch (SQLException e) {
				e.printStackTrace();
				
			return null;
			
			}finally {
				try {
					connMysql.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}			
	}
	
	public static void removeUser (long id) {
		Connection connMysql = ConnectionFactory.openConnection();
		
		try {
			connMysql.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM ");
			sql.append("USER ");
			sql.append("WHERE ID = ?");
			
			PreparedStatement queryDelete = connMysql.prepareStatement(sql.toString());
			queryDelete.setLong(1, id);
			
			queryDelete.executeUpdate();
			
			connMysql.commit();			
		}catch (SQLException e) {
			try {
				connMysql.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				connMysql.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateUser (Long id, User user) {
		Connection connMysql = ConnectionFactory.openConnection();
		
		try {
			connMysql.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE USER ");
			sql.append("SET FIRSTNAME = ?, ");
			sql.append("LASTNAME = ?, ");
			sql.append("EMAIL = ?, ");
			sql.append("DOCUMENT = ? ");			
			sql.append("WHERE ID = ?;");
			
			PreparedStatement queryUpdate = connMysql.prepareStatement(sql.toString());
			queryUpdate.setString(1, user.getFirstName());
			queryUpdate.setString(2, user.getLastName());
			queryUpdate.setString(3, user.getEmail());
			queryUpdate.setLong(4, user.getDocument());
			queryUpdate.setLong(5, id);
			
			queryUpdate.executeUpdate();
			
			connMysql.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			try {
				connMysql.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				connMysql.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}


