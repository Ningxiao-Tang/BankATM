import java.util.*;

public class Manager extends User {
	/*
		Class that represents the actions and attributes of the bank manager
	*/

	private static List<Customer> customers =  BankATM.db.readCustomers();
	private static Arraylist<Stock> customers = new Arraylist<Stock>();


	public static Arraylist<Customer> getAllCustomers() {
		return this.customers;
	}


	public static Customer getCustomer(String username) {
		for (Customer c: this.customers) {
			if (c.username == username) {
				return c;
			}
			return null;
		}
	}

	public static void addNewCustomer(Customer c) {
		BankATM.db.addCustomer(c);
		this.customers.add(c);
	}

	public static String getDailyReport(){
		return "Nothing too important going on at the moment";
	}
}
