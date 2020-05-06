/*
Class representing a customer Checking Account
*/

package bank;

public class CheckingAccount extends AccountType {

	public static double transactionFee; // set by bank manager

	
	public CheckingAccount(Currency c, Database.BankData db) {
		super(c, db);
		accType = 'C';
	}

	public double getBalanceInLocalCurrency() {
		return this.balance.getValue();
	}

	public String toString() {
		return Integer.toString(accID);
	}
	
}