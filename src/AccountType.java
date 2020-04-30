// class representing an account which can hold money for a customer
public abstract class AccountType {
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
