/*
	Abstract class that represents any type of account that can hold money for a customer
*/
package bank;

public abstract class AccountType {
	
	protected Currency balance;
	protected boolean isActive;
	public static Currency accountOpenFee; // set by manager
	public static Currency accountCloseFee; // set by manager
	public static Currency withdrawlFee; // set by manager

	public AccountType(Currency startingBalance) {
		this.balance = startingBalance;
		this.isActive = true;
	}

	public Currency getBalance() {
		return balance;
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
