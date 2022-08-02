package web;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dao.DaoImp;
import dao.IDao;
import entities.Abonnement;
import entities.Admin;
import entities.Client;
import entities.Paiement;
import entities.User;
import service.IBankService;
import service.IService;
import service.Mail;
import service.RMIClient;
import service.ServiceImp;


public class Servlet extends HttpServlet{
	
	private IDao dao;
	private IService service;
	private IBankService bankService;
	private RMIClient rmiClient;
	
	@Override   
	public void init() throws ServletException {
		dao = new DaoImp();
		service = new ServiceImp();
		try {
			bankService = rmiClient.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		if(path.equals("/setLangue")) {
			String langue = req.getParameter("langue");
			Cookie cookie = new Cookie("lang", langue);
			cookie.setMaxAge(360 * 60 * 60);
			resp.addCookie(cookie);
			resp.sendRedirect("login");
		}else if(path.equals("/logout")) {
			session.invalidate();
			resp.sendRedirect("login");
		}else if(path.equals("/paiement")) {
			Long abonnementId = Long.parseLong(req.getParameter("id"));
			Abonnement abonnement = dao.getAbonnementById(abonnementId);
			Model model = new Model();
			model.setAbonnement(abonnement);
			req.setAttribute("model", model);
			req.getRequestDispatcher("clientVue.jsp").forward(req, resp);
		}else if(path.equals("/paiements")) {
			Client client = (Client)session.getAttribute("user");
			List<Paiement> paiements = service.getAllPaiements(client.getId());
			Model model = new Model();
			model.setPaiements(paiements);
			req.setAttribute("model", model);
			req.getRequestDispatcher("paiements.jsp").forward(req, resp);
		}else if(path.equals("/clientinfo")) {
			Long id = Long.parseLong(req.getParameter("id"));
			List<Paiement> paiements = service.getAllPaiements(id);
			Client client = dao.getClient(id);
			Model model = new Model();
			model.setPaiements(paiements);
			model.setClient(client);
			req.setAttribute("model",model );
			req.getRequestDispatcher("paiementinfo.jsp").forward(req, resp);
		}else if(session.getAttribute("user") != null ) {
			User user = (User) session.getAttribute("user");
			if(user instanceof Client) {
				Client client = (Client) session.getAttribute("user");
				Model model = new Model();
				List<Abonnement> abonnements = service.getAllAbonnements(client.getId());
				model.setAbonnements(abonnements);
				req.setAttribute("model", model);
				req.getRequestDispatcher("home.jsp").forward(req, resp);
			}else {
				Model model = new Model();
				List<Client> clients = dao.getAllClients();
				model.setClients(clients);
				req.setAttribute("model", model);
				req.getRequestDispatcher("adminVue.jsp").forward(req, resp);
			}
		}else {
			req.getRequestDispatcher("authentification.jsp").forward(req, resp);
		}
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = null;
		String path = req.getServletPath();
		if(path.equals("/authentification")) {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			User user = dao.authentificate(username, password);
			if(user == null) {
				Model model = new Model();
				model.setBadCredentials(true);
				req.setAttribute("model", model);
				req.getRequestDispatcher("authentification.jsp").forward(req, resp);
			}else {
				session = req.getSession();
				session.setAttribute("user", user);
				if(user instanceof Client) {
					Client client = (Client) user;
					Model model = new Model();
					List<Abonnement> abonnements = service.getAllAbonnements(client.getId());
					model.setAbonnements(abonnements);
					req.setAttribute("model", model);
					req.getRequestDispatcher("home.jsp").forward(req, resp);
				}else {
					Model model = new Model();
					List<Client> clients = dao.getAllClients();
					model.setClients(clients);
					req.setAttribute("model", model);
					req.getRequestDispatcher("adminVue.jsp").forward(req, resp);
				}
			}
		}else if(path.equals("/paiement")) { 
			session = req.getSession();
			String cardNumber = req.getParameter("numeroCarte");
			String expirationDate = req.getParameter("dateExpiration");
			String lastDigits = req.getParameter("lastDigits");
			String abonnementId = req.getParameter("abonnementId");
			Date expDate = service.stringToDate(expirationDate);
			Abonnement abonnement = dao.getAbonnementById(Long.parseLong(abonnementId));
			if(bankService.makePaiement(cardNumber, lastDigits, expDate, abonnement.getMontant())) {
				Client client = (Client) session.getAttribute("user");
				service.addPaiement(abonnement.getMontant(), client.getId(),Long.parseLong(abonnementId));
				Model model = new Model();
				model.setClient(client);
				req.setAttribute("model", model);
		    	List<Paiement> paiements = service.getAllPaiements(client.getId());
		    	Mail mail = new Mail(paiements.get(paiements.size() - 1));
				req.getRequestDispatcher("successVue.jsp").forward(req, resp);
			}else {
				Model model = new Model();
				model.setBadCredentials(true);
				req.setAttribute("model", model);
				req.getRequestDispatcher("clientVue.jsp").forward(req, resp);
			}
		}
	}
}
