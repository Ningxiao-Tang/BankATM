/* Class that represents the transfer kind of transaction */

public class Transfer extends Transaction {

	private Currency amount;
	private AccountType from;
	private AccountType to;

	public Transfer(Currency c,  AccountType fromAccount, AccountType toAccount) {
		 this.amount = c;
		 this.from = fromAccount;
		 this.to = toAccount;
		 this.transfer();
	}	

	public void transfer() {
		// transfer from one account to another, checking for currency types

		double valToTransfer = this.amount.getValue();
		CurrencyType toAccountType = this.to.getBalance().getType();
		CurrencyType fromAccountType = this.from.getBalance().getType();

		this.from.getBalance().subtract(this.amount.convert(fromAccountType));
		this.to.getBalance().add(this.amount.convert(toAccountType));
	}
	
}