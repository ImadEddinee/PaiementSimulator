package service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMIClient {
	
	
	public static IBankService connect() {
		IBankService bankService = null;
		try {
			bankService = (IBankService) Naming.lookup("rmi://localhost:1098/BankService");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bankService;
	}

}
