package dao;

import java.util.Date;

import entities.BankAccount;

public interface IDao {

	BankAccount getAccountByCardNumber(String cardNumber);
	void updateAccountBalance(String cardNumber,double balance);
}
