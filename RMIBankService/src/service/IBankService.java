package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IBankService extends Remote{
	
	boolean makePaiement(String cardNumber,String lastDigits,Date expDate,double montant) throws RemoteException;
	
}
