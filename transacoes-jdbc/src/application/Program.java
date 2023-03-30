package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {
	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		
		
		
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			conn.setAutoCommit(false); // só vai salvar qnd fizer cnn.commit()
			
			int alteracao1 = st.executeUpdate("UPDATE seller SET BaseSalary = 3700 WHERE DepartmentId = 1");
			
			//simulando um erro na execuçao 
			int x = 1;
			if (x < 2) {
				throw new SQLException("Erro fake para teste.");
			}
			
			int alteracao2 = st.executeUpdate("UPDATE seller SET DepartmentId = 2 WHERE DepartmentId = 1");
			
			conn.commit(); //só vai atualizar o banco quando o programa conseguir chegar aqui, ou seja, passando por todas alterações
			
			System.out.println("execução 1 = " + alteracao1);
			System.out.println("execução 2 = " + alteracao2);
			
		}
		catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Rollback foi feito! Transação não foi concluída.");
			}
			catch (SQLException e1) {
				throw new DbException("Erro enquanto fazia rollback! Causado por: " + e1.getMessage());
			}
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
