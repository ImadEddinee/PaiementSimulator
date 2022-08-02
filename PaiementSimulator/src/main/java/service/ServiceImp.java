package service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import dao.DaoImp;
import dao.IDao;
import entities.Abonnement;
import entities.Paiement;


public class ServiceImp implements IService{
	
	private IDao dao = new DaoImp();

	@Override
	public boolean checkUniqueUsernameAndEmail(String username, String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Abonnement> getAllAbonnements(Long clientId) {
		List<Abonnement> abonnements = dao.getAllAbonnements(clientId);
		for (Abonnement abonnement : abonnements) {
			abonnement.setNextPaiement(addMonthToDate(abonnement.getPaid_at()));
			Date currentDate = new Date();
			if(currentDate.compareTo(abonnement.getNextPaiement()) > 0) {
				abonnement.setIs_paid(false);
				abonnement.setDaysDiff(DaysBetweenTwoDates(abonnement.getPaid_at()));
			}else {
				abonnement.setIs_paid(true);
				abonnement.setDaysDiff(DaysBetweenTwoDates(abonnement.getNextPaiement()));
			}
		}
		return abonnements;
	}

	@Override
	public Date addMonthToDate(Date date) {
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date); 
		calendar.add(Calendar.MONTH, 1);
		date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String stringDate = sdf.format(date);
		try {
			date = sdf.parse(stringDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	@Override
	public Long DaysBetweenTwoDates(Date date) {
		Date currentDate = new Date();
		long timeDiff = Math.abs(date.getTime() - currentDate.getTime());
		long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
		return daysDiff;
	}

	@Override
	public Date stringToDate(String expdate) {
		expdate = "01/" + expdate;
		DateFormat dateFormat= new SimpleDateFormat("dd/MM/yy");
		Date date= new Date();
		try {
			date = (Date)dateFormat.parse(expdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	@Override
	public void addPaiement(double montant, Long idClient, Long idAbonnement) {
		Paiement paiement = new Paiement();
		paiement.setDate(new Date());
		paiement.setMontant(montant);
		paiement.setClientId(idClient);;
		paiement.setAbonnementId(idAbonnement);
		dao.addPaiement(paiement);
		dao.updateAbonnement(idAbonnement);
	}

	@Override
	public List<Paiement> getAllPaiements(Long clientId) {
		List<Paiement> paiements = dao.getAllPaiementsByClientId(clientId);
		for (Paiement paiement : paiements) {
			Abonnement abonnement = dao.getAbonnementById(paiement.getAbonnementId());
			paiement.setAbonnementName(abonnement.getName());
		}
		return paiements;
	}
	


}
