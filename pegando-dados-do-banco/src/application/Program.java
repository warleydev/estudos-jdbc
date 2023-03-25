package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;

import db.DB;

public class Program {
	public static void main(String[] args) {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		Locale.setDefault(Locale.US);
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
			
			st = conn.createStatement();
			
			rs = st.executeQuery("select * from seller");
			
			while(rs.next()) {
				System.out.println("---------------------------------------------------");
				System.out.println("name: " + rs.getString("Name"));
				System.out.println("salary: " + String.format("R$ %.2f",rs.getDouble("BaseSalary")));
				System.out.println("birth date: " +  fmt.format(rs.getDate("BirthDate")));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
	
	}

}
