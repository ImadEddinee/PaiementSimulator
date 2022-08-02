package service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dao.DaoImp;
import dao.IDao;
import entities.Client;
import entities.Paiement;



public class Mail {
	
	private Paiement paiement;
	private IDao dao;
	
	public Mail(Paiement paiement) {
		this.paiement = paiement;
		dao = new DaoImp();
		Client client = dao.getClient(paiement.getClientId());
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("hajaliimadeddine@gmail.com", "ptqwxaadslgsxfnc");
            }
        });
        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("hajaliimadeddine@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(client.getEmail()));
            message.setSubject("Confirmation de votre Paiement");
            Long idPaiement = paiement.getId();
            Date datePaiement = paiement.getDate();
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
            double montant = paiement.getMontant();
            message.setText("Le code de votre Paiement est : " + idPaiement + "\n La date de Paiement est : " + sdf.format(datePaiement) + "\n Le montant paye est : " + montant);
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
	}

}


