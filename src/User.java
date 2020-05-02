public class User {
	/*
		Class that represents the types of users that can be made within the bank

	*/
	protected String name;
	protected String email;
	protected String username;
	protected String password;

	public User(String name, String email, String username, String password) {
		this.name = name;
    	this.password = password;
    	this.email = email;
    	this.address = address;
	}

	public boolean login(String username, String password) {
		return username.equals(this.username) && password.equals(this.password)
	}

	public void logout() {}

}
