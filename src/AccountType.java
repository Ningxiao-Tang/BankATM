/*
	Abstract class that represents any type of account that can hold money for a customer
*/

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
		balance.add(value);
	}
	
	public void withdraw(Currency value) {
		balance.subtract(value);
	}

	public closeAccount() {
		this.isActive = false;
	}
}
