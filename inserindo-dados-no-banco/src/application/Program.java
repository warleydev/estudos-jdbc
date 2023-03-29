package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import db.DB;

public class Program {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			/*
			 *INSERINDO NA TABELA department
			st = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, "Warley Melo");
			st.setString(2, "warleycm7@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("12/06/2005").getTime()));
			st.setDouble(4, 3700.00);
			st.setInt(5, 1);
			*/
			
			//INSERINDO NA TABELA department
			st = conn.prepareStatement(
					"insert into department (Name) values ('Medicinal'),('Mechanical')",
					java.sql.Statement.RETURN_GENERATED_KEYS);
			
			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Pronto! Id adicionado: "+ id);
				}
			}
			else {
				System.out.println("Nenhuma linha no banco foi afetada!");
			}
			
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
