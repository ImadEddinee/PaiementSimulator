package entities;

import java.util.Date;

public class Paiement {
	
	private Long id;
	private Date date;
	private double montant;
	private Long clientId;
	private String abonnementName;
	public String getAbonnementName() {
		return abonnementName;
	}
	public void setAbonnementName(String abonnementName) {
		this.abonnementName = abonnementName;
	}
	private Long abonnementId;
	public Long getAbonnementId() {
		return abonnementId;
	}
	public void setAbonnementId(Long abonnementId) {
		this.abonnementId = abonnementId;
	}
	public Paiement() {
		super();
	}
	public Paiement(Long id, Date date, double montant, Long clientId) {
		super();
		this.id = id;
		this.date = date;
		this.montant = montant;
		this.clientId = clientId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

}
