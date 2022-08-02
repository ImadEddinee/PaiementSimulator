package entities;

public class Client extends User{

	private String role;
	private String accountNumber;
	public Client() {
		super();
	}
	public Client(Long id, String firstName, String lastName, String username, String password, String email,
			String phoneNumber,String role, String accountNumber) {
		super(id, firstName, lastName, username, password, email, phoneNumber);
		this.role = role;
		this.accountNumber = accountNumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}
