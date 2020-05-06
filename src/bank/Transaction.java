// Subclass of transaction that represents a transaction of money from an ATM
package bank;

public class Transaction {
	protected Currency amt;
	protected AccountType account;

	public Transaction(Currency amount, AccountType acc) {
		this.amt = amount;
		this.account = acc;
	}

	public AccountType getAccount() {
		return account;
	}

	public double getAmt() {
		return amt.getValue();
	}
}