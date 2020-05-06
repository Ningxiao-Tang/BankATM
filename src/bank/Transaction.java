package bank;

public class Transaction {
	protected Currency amt;
	protected AccountType account;

	public Transaction(Currency amount, AccountType acc) {
		this.amt = amount;
		this.account = acc;
	}
}