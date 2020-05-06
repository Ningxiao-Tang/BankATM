package bank;

public class SavingsAccount extends AccountType {

	/*
		This class represents a customer savings account
	*/

	public static float percentInterest; // set by bank manager
	
	public SavingsAccount(Currency c, Database.BankData db) {
		super(c, db);
	}

	public boolean qualifiesForSecurities() {
		return balance.convert(CurrencyType.USD) >= 5000.00;
	}
}
