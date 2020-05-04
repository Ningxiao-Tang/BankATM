public class User {
	/*
		Class that represents the types of users that can be made within the bank

	*/
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String username;
	protected String password;

	public User(String firstName, String lastName, String email, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
    	this.password = password;
    	this.email = email;
    	this.address = address;
	}

	public boolean login(String username, String password) {
		return username.equals(this.username) && password.equals(this.password)
	}

	public void logout() {}

}
