public class Withdraw extends Transaction {

	public Withdraw(Currency c, AccountType acc) {
		super(c, acc);
		this.withdraw();
	}

	public void withdraw() {
		double valToSub = this.amt.getValue();
		CurrencyType accCurrType = this.account.getBalance().getType();

		if (!(this.amt.getType() == accCurrType)) {
			valToSub = this.amt.convert(accCurrType);
		}
		this.account.getBalance().subtract(valToSub);
	}
}