package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program {
	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"UPDATE seller "
					+ "SET Name = ? "
					+ "WHERE "
					+ "(Name = ?)");
			st.setString(1, "Victoria Melo");
			st.setString(2, "Victoria Carvalho");
			
			int rowsAffected = st.executeUpdate();
			System.out.println("PRONTO! " + rowsAffected + " linhas foram afetadas.");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
