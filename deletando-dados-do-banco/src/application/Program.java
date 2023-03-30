package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DBIntegrityException;

public class Program {
	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"DELETE FROM department "
					+ "WHERE "
					+ "Id = ? ");
			st.setInt(1, 5);
			
			int rowsAffected = st.executeUpdate();
			System.out.println("PRONTO! " + rowsAffecte8d + " linhas foram afetadas.");
		}
		catch (SQLException e) {
			throw new DBIntegrityException("Você não pode excluir um departamento que possui trabalhadores!");
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
