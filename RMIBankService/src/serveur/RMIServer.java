package serveur;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import bootstrap.LoadAccountData;
import service.BankServiceImp;

public class RMIServer {

	public static void main(String[] args) {
		
		LoadAccountData.load();
		
		try {
			LocateRegistry.createRegistry(1098);
			BankServiceImp bankServiceImp = new BankServiceImp();
			Naming.rebind("rmi://localhost:1098/BankService", bankServiceImp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
