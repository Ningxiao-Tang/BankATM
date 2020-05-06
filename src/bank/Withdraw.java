// Subclass of transaction that represents a withdrawal of money from an ATM
package bank;

public class Withdraw extends Transaction {

	public Withdraw(Currency c, AccountType acc) {
		super(c, acc);
		this.withdraw();
	}

	public void withdraw() {
		// withdraw action, basically checks for currency type and takes out the money
		double valToSub = this.amt.getValue();
		CurrencyType accCurrType = this.account.getBalance().getType();

		if (!(this.amt.getType() == accCurrType)) {
			valToSub = this.amt.convert(accCurrType);
		}
		this.account.getBalance().subtract(valToSub);
	}
}