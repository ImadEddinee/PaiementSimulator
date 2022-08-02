package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.BankAccount;

public class DaoImp implements IDao{
	
	private Connection conn;

	@Override
	public BankAccount getAccountByCardNumber(String cardNumber) {
		BankAccount bankAccount = null;
		conn = SingletonConnexion.getConnection();
		String sql = "SELECT * FROM bank_account WHERE cardNumber = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cardNumber);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				bankAccount = new BankAccount();
				bankAccount.setId(rs.getLong("id"));
				bankAccount.setFirstName(rs.getString("firstName"));
				bankAccount.setLastName(rs.getString("lastName"));
				bankAccount.setCardNumber(rs.getString("cardNumber"));
				bankAccount.setExpirationDate(rs.getDate("expirationDate"));
				bankAccount.setLastDigits(rs.getString("lastDigits"));
				bankAccount.setBalance(rs.getDouble("balance"));
				bankAccount.setCreatedAt(rs.getDate("createdAt"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bankAccount;
	}

	@Override
	public void updateAccountBalance(String cardNumber, double balance) {
		conn = SingletonConnexion.getConnection();
		String sql = "update bank_account set balance = ? where cardNumber = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, balance);
			ps.setString(2, cardNumber);
			ps.executeUpdate();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
