package bank;

import java.util.*;
import Database.BankData;

public class Manager extends User {
	/*
		Class that represents the actions and attributes of the bank manager
	*/

	private List<Customer> customers;
	private List<Stock> stocks;

	public Manager(String firstName, String lastName, String email, String password, BankData db) {
		super(firstName, lastName, email, password, db);
		customers = db.readCustomers();
		stocks = db.readStocks();
	}


	public List<Customer> getAllCustomers() {
		return this.customers;
	}


	public Customer getCustomerInfo(String email) {
		Customer ret = null;
		for (Customer c: this.customers) {
			if ((c.getEmail()).equals(email)) {
				ret = c;
			}
		}
		return ret;
	}

	public void addNewCustomer(Customer c) {
		db.addCustomer(c);
		this.customers.add(c);
	}

	public void changeStockValue(Stock s, double newPrice) {
		s.setPrice(newPrice);
		db.updateStock(s);

	}

	public List<Transaction> getDailyReport(){
		//  returns a daily report on transactions for that day.
		return db.readTransactions();
	}
}
