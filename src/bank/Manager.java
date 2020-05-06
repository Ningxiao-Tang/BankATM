package bank;

import java.util.*;
import Database.BankData;

public class Manager extends User {
	/*
		Class that represents the actions and attributes of the bank manager
	*/

	private static List<Customer> customers =  BankData.readCustomers();
	private static List<Stock> stocks = BankData.readStocks();


	public static Arraylist<Customer> getAllCustomers() {
		return this.customers;
	}


	public static Customer getCustomerInfo(String username) {
		for (Customer c: this.customers) {
			if (c.username == username) {
				return c;
			}
			return null;
		}
	}

	public static void addNewCustomer(Customer c) {
		BankData.addCustomer(c);
		this.customers.add(c);
	}

	public static void changeStockValue(Stock s, double newPrice) {
		s.setPrice(newPrice);
		BankData.updateStock(s);

	}

	public static List<Transaction> getDailyReport(){
		//  returns a daily report on transactions for that day.
		return BankData.readTransactions();
	}
}
