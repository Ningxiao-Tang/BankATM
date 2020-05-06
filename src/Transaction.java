// Subclass of transaction that represents a transaction of money from an ATM
public class Transaction {
	protected Currency amt;
	protected AccountType account;

	public Transaction(Currency amount, AccountType acc) {
		this.amt = amount;
		this.account = acc;
	}
}