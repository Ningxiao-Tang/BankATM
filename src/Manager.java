import java.util.*;

public class Manager extends User {
	/*
		Class that represents the actions and attributes of the bank manager
	*/

	private static String username="username";
	private static String password="password";
	private static Arraylist<Customer> customers = new Arraylist<Customer>();

	public static boolean login(String username, String password) {
		// returns true or false depending on whether the user 
		return username.equals(this.username) && password.equals(this.password)
	}

	public static Arraylist<Customer> getAllCustomers() {
		return this.customers;
	}

	public static Customer getCustomer(int cid) {
		for (Customer c: this.customers) {
			if (c.id == cid) {
				return c;
			}
			return null;
		}
	}

	public static void addNewCustomer(Customer c) {
		this.customers.add(c);
	}

	public static String getDailyReport(){
		return "Nothing too important going on at the moment";
	}

}
