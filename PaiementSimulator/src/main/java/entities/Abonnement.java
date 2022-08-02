package entities;

import java.util.Date;

public class Abonnement {
	
	private Long id;
	private String name;
	private Double montant;
	private Date paid_at;
	private Date nextPaiement;
	private Long daysDiff;
	private boolean is_paid;
	public Long getDaysDiff() {
		return daysDiff;
	}
	public void setDaysDiff(Long daysDiff) {
		this.daysDiff = daysDiff;
	}
	
	public Date getNextPaiement() {
		return nextPaiement;
	}
	public void setNextPaiement(Date nextPaiement) {
		this.nextPaiement = nextPaiement;
	}
	public boolean isIs_paid() {
		return is_paid;
	}
	public void setIs_paid(boolean is_paid) {
		this.is_paid = is_paid;
	}
	private Long clientId;
	public Abonnement() {
		super();
	}
	public Abonnement(Long id, String name, Double montant, Date paid_at, Long clientId) {
		super();
		this.id = id;
		this.name = name;
		this.montant = montant;
		this.paid_at = paid_at;
		this.clientId = clientId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	public Date getPaid_at() {
		return paid_at;
	}
	public void setPaid_at(Date paid_at) {
		this.paid_at = paid_at;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	
}
