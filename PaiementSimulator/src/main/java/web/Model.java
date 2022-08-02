package web;

import java.util.List;

import entities.Abonnement;
import entities.Admin;
import entities.Client;
import entities.Paiement;

public class Model {
	
	private boolean badCredentials;
	private Client client;
	private Admin admin;
	private List<Abonnement> abonnements;
	private List<Paiement> paiements;
	private List<Client> clients;
	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	private Abonnement abonnement;
	public List<Paiement> getPaiements() {
		return paiements;
	}

	public void setPaiements(List<Paiement> paiements) {
		this.paiements = paiements;
	}

	public Abonnement getAbonnement() {
		return abonnement;
	}

	public void setAbonnement(Abonnement abonnement) {
		this.abonnement = abonnement;
	}

	public List<Abonnement> getAbonnements() {
		return abonnements;
	}

	public void setAbonnements(List<Abonnement> abonnements) {
		this.abonnements = abonnements;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public boolean isBadCredentials() {
		return badCredentials;
	}

	public void setBadCredentials(boolean badCredentials) {
		this.badCredentials = badCredentials;
	}

	public Model() {
		super();
	}
	
}
