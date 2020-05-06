// Subclass of transaction that represents a deposit of money from an ATM
package bank;

public class Deposit extends Transaction {

	public Deposit(Currency c, AccountType acc) {
		super(c, acc);
		this.deposit();
	}	
	
	public void deposit() {
		// deposit action, basically checks for currency type and takes in the money
		double valToAdd = this.amt.getValue();
		CurrencyType accCurrType = this.account.getBalance().getType();

		if (!(this.amt.getType() == accCurrType)) {
			valToAdd = this.amt.convert(accCurrType);
		}
		this.account.getBalance().add(valToAdd);
	}
	
}