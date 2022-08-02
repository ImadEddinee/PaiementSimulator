package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import entities.Abonnement;
import entities.Admin;
import entities.Client;
import entities.Paiement;
import entities.User;


public class DaoImp implements IDao{
	
	private Connection conn;

	@Override
	public Client addClient(Client client) {
		conn = SingletonConnexion.getConnection();
		String sql = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?);";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, 2L);
			ps.setString(2, client.getFirstName());
			ps.setString(3, client.getLastName());
			ps.setString(4, client.getUsername());
			ps.setString(5, client.getPassword());
			ps.setString(6, client.getEmail());
			ps.setString(7, client.getPhoneNumber());
			ps.setString(8, client.getRole());
			ps.setString(9, client.getAccountNumber());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return client;
	}

	@Override
	public Client getClient(Long id) {
		Client client = new Client();
		conn = SingletonConnexion.getConnection();
		String sql = "SELECT * FROM USER WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				client.setId(rs.getLong("id"));
				client.setFirstName(rs.getString("firstName"));
				client.setLastName(rs.getString("lastName"));
				client.setUsername(rs.getString("username"));
				client.setPassword(rs.getString("password"));
				client.setEmail(rs.getString("email"));
				client.setPhoneNumber(rs.getString("phoneNumber"));
				client.setRole(rs.getString("role"));
				client.setAccountNumber(rs.getString("accountNumber"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return client;
	}

	@Override
	public Admin addAdmin(Admin admin) {
		conn = SingletonConnexion.getConnection();
		String sql = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?);";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, admin.getId());
			ps.setString(2, admin.getFirstName());
			ps.setString(3, admin.getLastName());
			ps.setString(4, admin.getUsername());
			ps.setString(5, admin.getPassword());
			ps.setString(6, admin.getEmail());
			ps.setString(7, admin.getPhoneNumber());
			ps.setString(8, admin.getRole());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admin;

	}

	@Override
	public List<Client> getAllClients() {
		List<Client> clients = new ArrayList<>();
		conn = SingletonConnexion.getConnection();
		String sql = "SELECT * FROM USER WHERE role = 'CLIENT'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Client client = new Client();
				client.setId(rs.getLong("id"));
				client.setFirstName(rs.getString("firstName"));
				client.setLastName(rs.getString("lastName"));
				client.setUsername(rs.getString("username"));
				client.setPassword(rs.getString("password"));
				client.setEmail(rs.getString("email"));
				client.setPhoneNumber(rs.getString("phoneNumber"));
				client.setRole(rs.getString("role"));
				client.setAccountNumber("accountNumber");
				clients.add(client);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clients;
	}

	@Override
	public Paiement addPaiement(Paiement paiement) {
		conn = SingletonConnexion.getConnection();
		String sql = "INSERT INTO paiement(montant,client_id,abonnement_id) VALUES(?,?,?);";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, paiement.getMontant());
			ps.setLong(2, paiement.getClientId());
			ps.setLong(3, paiement.getAbonnementId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paiement;
	}

	@Override
	public List<Paiement> getAllPaiementsByClientId(Long clientId) {
		List<Paiement> paiements = new ArrayList<>();
		conn = SingletonConnexion.getConnection();
		String sql = "SELECT * FROM paiement where client_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, clientId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Paiement paiement = new Paiement();
				paiement.setId(rs.getLong("id"));
				paiement.setDate(rs.getDate("date"));
				paiement.setMontant(rs.getDouble("montant"));
				paiement.setClientId(rs.getLong("client_id"));
				paiement.setAbonnementId(rs.getLong("abonnement_id"));
				paiements.add(paiement);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paiements;
	}

	@Override
	public User authentificate(String username, String password) {
		Client client = null;
		Admin admin = null;
		conn = SingletonConnexion.getConnection();
		String sql = "SELECT * FROM user where username = ? and password = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String role = rs.getString("role");
				if(role.equals("ADMIN")) {
					admin = new Admin();
					admin.setId(rs.getLong("id"));
					admin.setFirstName(rs.getString("firstName"));
					admin.setLastName(rs.getString("lastName"));
					admin.setUsername(rs.getString("username"));
					admin.setPassword(rs.getString("password"));
					admin.setEmail(rs.getString("email"));
					admin.setPhoneNumber(rs.getString("phoneNumber"));
					admin.setRole(rs.getString("role"));
	
				}else {
					client = new Client();
					client.setId(rs.getLong("id"));
					client.setFirstName(rs.getString("firstName"));
					client.setLastName(rs.getString("lastName"));
					client.setUsername(rs.getString("username"));
					client.setPassword(rs.getString("password"));
					client.setEmail(rs.getString("email"));
					client.setPhoneNumber(rs.getString("phoneNumber"));
					client.setRole(rs.getString("role"));
					client.setAccountNumber(rs.getString("accountNumber"));
		
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(client != null) {
			return client;
		}else {
			return admin;
		}
	}
	
	public boolean checkUniqueUsername(String username) {
		conn = SingletonConnexion.getConnection();
		String sql = "SELECT * FROM user where username = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean checkUniqueEmail(String email) {
		conn = SingletonConnexion.getConnection();
		String sql = "SELECT * FROM user where email = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public List<Abonnement> getAllAbonnements(Long clientId) {
		List<Abonnement> abonnements = new ArrayList<>();
		conn = SingletonConnexion.getConnection();
		String sql = "SELECT * FROM abonnement where client_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, clientId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Abonnement abonnement = new Abonnement();
				abonnement.setId(rs.getLong("id"));
				abonnement.setName(rs.getString("name"));
				abonnement.setMontant(rs.getDouble("montant"));
				abonnement.setPaid_at(rs.getDate("paid_at"));
				abonnements.add(abonnement);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return abonnements;
	}

	@Override
	public Abonnement getAbonnementById(Long id) {
		Abonnement abonnement = new Abonnement();
		conn = SingletonConnexion.getConnection();
		String sql = "SELECT * FROM abonnement WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				abonnement.setId(rs.getLong("id"));
				abonnement.setName(rs.getString("name"));
				abonnement.setMontant(rs.getDouble("montant"));
				abonnement.setPaid_at(rs.getDate("paid_at"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return abonnement;
	}

	@Override
	public void updateAbonnement(Long id) {
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		conn = SingletonConnexion.getConnection();
		String sql = "update abonnement set paid_at = ? where id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, date);
			ps.setLong(2, id);
			ps.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
