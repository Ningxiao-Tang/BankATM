/*
A user that 
*/

import java.util.List;
import Database.BankData;

public class Customer extends User {

    private List<CheckingAccount> checkingAccounts = BankData.readSavingAccounts(this);
    private List<SavingsAccount> savingsAccounts = BankData.readSavingAccounts(this);
    private List<SecurityAccount> securityAccounts = BankData.readSecurityAccounts(this);

    public Customer(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    	Manager.addCustomer(this);
    }

    public void makeCheckingAccount(Currency c) {
    	CheckingAccount acc = new CheckingAccount(c);
    	this.checkingAccounts.add(acc);
        BankData.addCheckingAccount(this, acc);
    }

    public void makeSavingsAccount(Currency c) {
    	SavingAccounts acc = new SavingAccounts(c);
    	this.savingsAccounts.add(acc);
        BankData.addCheckingAccount(this, acc);
    }
	
	public void makeInvestmentAccount(Currency c) {
		SecurityAccount acc = new SecurityAccount(c);
		this.securityAccounts.add(acc);
        BankData.addCheckingAccount(this, acc);
	}  	

    public List<CheckingAccount> getCheckingAccounts() {
        return this.checkingAccounts;
    }

    public List<SavingsAccount> getSavingAccounts() {
        return this.savingAccounts;   
    }
    
    public List<SecurityAccount> getStockAccounts() {
        return this.securityAccounts;   
    }

}
