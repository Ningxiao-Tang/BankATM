/* Class that represents the transfer kind of transaction */

package bank;

public class Transfer extends Transaction {

	private AccountType to;

	public Transfer(Currency c,  AccountType fromAccount, AccountType toAccount) {
		super(c, fromAccount);
		this.to = toAccount;
		this.transfer();
	}	

	public void transfer() {
		// transfer from one account to another, checking for currency types

		double valToTransfer = this.amt.getValue();
		CurrencyType toAccountType = this.to.getBalance().getType();
		CurrencyType fromAccountType = this.from.getBalance().getType();

		this.from.getBalance().subtract(this.amount.convert(fromAccountType));
		this.to.getBalance().add(this.amount.convert(toAccountType));
	}
	
}