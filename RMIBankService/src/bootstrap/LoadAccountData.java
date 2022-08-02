package bootstrap;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import dao.SingletonConnexion;

public class LoadAccountData {
	
	private static Connection conn;
	
	public static void load() {
		createTable();
		insertData();
	}
	
	public static void createTable() {
		conn = SingletonConnexion.getConnection();
		try {
			Statement stm = conn.createStatement();
			String sql = "DROP TABLE IF EXISTS bank_account;"+
					"CREATE TABLE bank_account ( "+
					" id int auto_increment primary key,"+
					" firstName varchar(20) not null , "+
					" lastName varchar(25) not null , "+
					" cardNumber varchar(150) not null unique, "+
					" expirationDate Date not null ,"+
					" lastDigits varchar(3) not null,"+
					" balance double not null , "+
					" createdAt timestamp default current_timestamp );";
			stm.executeUpdate(sql);
			System.out.println("Table Created");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insertData() {
		conn = SingletonConnexion.getConnection();
		String sql = "INSERT INTO bank_account "+
				"(firstName, lastName, cardNumber, expirationDate, lastDigits , balance) VALUES"+
				"('imad','eddine','9675397324074286','2029-09-01','587',6976954.50 ) ";
		try {
			Statement stm = conn.createStatement();
			stm.executeUpdate(sql);
			System.out.println("Load Data into table ...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
}
