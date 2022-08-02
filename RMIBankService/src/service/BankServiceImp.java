package service;

import java.awt.dnd.DropTargetEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import dao.DaoImp;
import dao.IDao;
import entities.BankAccount;

public class BankServiceImp extends UnicastRemoteObject implements IBankService {
	
	private IDao dao = new DaoImp();

	public BankServiceImp() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean makePaiement(String cardNumber, String lastDigits, Date expDate, double montant)
			throws RemoteException {
		System.out.println(expDate);
		BankAccount bankAccount = dao.getAccountByCardNumber(cardNumber);
		System.out.println(bankAccount.getExpirationDate());
		if(bankAccount.getLastDigits().equals(lastDigits)) {
			if(bankAccount.getBalance() >= montant) {
				Date currentDate = new Date();
				if(expDate.compareTo(bankAccount.getExpirationDate()) == 0) {
					System.out.println("true");
					if(expDate.compareTo(currentDate) > 0) {
						dao.updateAccountBalance(cardNumber, montant);
						System.out.println("true");
						return true;
					}
				}
			}
		}
		return false;
	}


}
