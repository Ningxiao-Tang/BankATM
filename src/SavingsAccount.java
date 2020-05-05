public class SavingsAccount {

	/*
		This class represents a customer savings account
	*/

	public static float percentInterest; // set by bank manager

	public boolean qualifiesForSecurities() {
		return balance.convert(CurrencyType.USD) >= 5000.00;
	}
}
