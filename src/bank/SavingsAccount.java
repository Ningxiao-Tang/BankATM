/*
	This class represents a customer savings account
*/
package bank;

public class SavingsAccount extends AccountType {

	public static float percentInterest; // set by bank manager
	
	public SavingsAccount(Currency c) {
		super(c);
	}

	public boolean qualifiesForSecurities() {
		return balance.convert(CurrencyType.USD) >= 5000.00;
	}
}
