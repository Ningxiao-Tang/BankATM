
public abstract class AccountType {
	/*
		Abstract class that represents any type of account that can hold money for a customer
	*/
		
	private Currency balance;
	public static Currency accountOpenFee; // set by manager
	public static Currency accountCloseFee; // set by manager
	public static Currency withdrawlFee; // set by manager

	public AccountType(Currency startingBalance) {
		this.balance = startingBalance;
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

	public abstract AccountType openAccount();

	public abstract void closeAccount();
}
