package entities;

public class Admin extends User {

	private String role;

	public Admin() {
		super();
	}
	

	public Admin(Long id, String firstName, String lastName, String username, String password, String email,
			String phoneNumber,String role) {
		super(id, firstName, lastName, username, password, email, phoneNumber);
		this.role = role;
	}


	public Admin(String role) {
		super();
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
