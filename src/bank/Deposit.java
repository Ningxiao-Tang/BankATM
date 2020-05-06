package bank;

public class Deposit extends Transaction {

	public Deposit(Currency c, AccountType acc) {
		super(c, acc);
		this.deposit();
	}	
	
	public void deposit() {
		double valToAdd = this.amt.getValue();
		CurrencyType accCurrType = this.account.getBalance().getType();

		if (!(this.amt.getType() == accCurrType)) {
			valToAdd = this.amt.convert(accCurrType);
		}
		this.account.getBalance().add(valToAdd);
	}
	
}