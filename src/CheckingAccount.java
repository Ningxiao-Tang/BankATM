/*
Class representing a customer Checking Account
*/

public class CheckingAccount extends AccountType {

	public static double transactionFee; // set by bank manager

	
	public CheckingAccount(Currency c) {
		super(c);
	}

	public int getBalanceInLocalCurrency() {
		this.balance.getValue();
	}
	
}