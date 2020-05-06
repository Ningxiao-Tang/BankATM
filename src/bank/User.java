/*
	Class that represents the types of users that can be made within the bank
*/
package bank;

public class User {

	protected String firstName;
	protected String lastName;
	protected String email;
	protected String password;
	protected Database.BankData db;

	public User(String firstName, String lastName, String email, String password, Database.BankData db) {
		this.firstName = firstName;
		this.lastName = lastName;
    	this.password = password;
    	this.email = email;
    	this.db = db;
	}

	/* Getters of customer attributes */
	public String getFirstName() {	return firstName; }

	public String getLastName() {
    	return lastName;
    }

    public String getEmail() {
    	return email;
    }

    public String getPassword() { return password; }


	public boolean login(String email, String password) {
		boolean ret = (this.email).equals(email) && password.equals(this.password);
		return ret;
	}

	public void logout() {
		System.out.println("not yet implemented");
	}

}
