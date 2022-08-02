package service;

import java.util.Date;
import java.util.List;

import entities.Abonnement;
import entities.Paiement;

public interface IService {
	
	boolean checkUniqueUsernameAndEmail(String username, String email);
	List<Abonnement> getAllAbonnements(Long clientId);
	Date addMonthToDate(Date date);
	Long DaysBetweenTwoDates(Date date);
	Date stringToDate(String expdate);
	void addPaiement(double montant, Long idClient, Long idAbonnement);
	List<Paiement> getAllPaiements(Long clientId);
}
