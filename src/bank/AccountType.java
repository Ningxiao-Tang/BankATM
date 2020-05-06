/*
	Abstract class that represents any type of account that can hold money for a customer
*/
package bank;

import java.util.Random;

public abstract class AccountType {

	protected Currency balance;
	protected boolean isActive;
	protected Database.BankData db;
	protected int accID;
	protected char accType;
	public static double accountOpenFee; // set by manager
	public static double accountClosedFee; // set by manager
	public static double withdrawlFee; // set by manager

	public AccountType(Currency startingBalance, Database.BankData db) {
		this.balance = startingBalance;
		this.isActive = true;
		this.db = db;
		Random rand = new Random();
		this.accID = rand.nextInt(100) + 1000;
	}

	public Currency getBalance() {
		return balance;
	}

	public int getAccID() {
		return accID;
	}

	public char getAccType() {
		return accType;
	}

	public void deposit(Currency value) {
		new Deposit(value, this);
	}
	
	public void withdraw(Currency value) {
		new Withdraw(value, this);
	}

	public void transfer(Currency value, AccountType toAccount) {
		new Transfer(value, this, toAccount);
	}

	public void closeAccount() {
		this.isActive = false;
	}
}
