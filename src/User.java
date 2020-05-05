/*
	Class that represents the types of users that can be made within the bank
*/

public class User {

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

	/* Getters of customer attributes */
	public String getFirstName() {
    	return firstName;
    }

	public String getLastName() {
    	return lastName;
    }

    public String getEmail() {
    	return email;
    }

    public String getUsername() {
    	return username;
    }

	public boolean login(String username, String password) {
		return username.equals(this.username) && password.equals(this.password);
	}

	public void logout() {
		System.out.println("not yet implemented");
	}

}
