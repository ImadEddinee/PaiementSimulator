package dao;

import java.util.List;
import java.util.Optional;

import entities.Abonnement;
import entities.Admin;
import entities.Client;
import entities.Paiement;
import entities.User;

public interface IDao {

	Client addClient(Client client);
	Client getClient(Long id);
	Admin addAdmin(Admin admin);
	List<Client> getAllClients();
	Abonnement getAbonnementById(Long id);
	List<Abonnement> getAllAbonnements(Long clientId);
	Paiement addPaiement(Paiement paiement);
	void updateAbonnement(Long id);
	List<Paiement> getAllPaiementsByClientId(Long clientId);
	User authentificate(String username,String password);
}
