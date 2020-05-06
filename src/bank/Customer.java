package bank;

import java.util.List;
import Database.BankData;

public class Customer extends User {
    private BankData db;
    private List<CheckingAccount> checkingAccounts;
    private List<SavingsAccount> savingsAccounts;
    private List<SecurityAccount> securityAccounts;

    public Customer(String firstName, String lastName, String email, String password, BankData db) {
        super(firstName, lastName, email, password, db);
        checkingAccounts = (this.db).readCheckingAccounts(this);
        savingsAccounts = (this.db).readSavingAccounts(this);
        securityAccounts = (this.db).readSecurityAccounts(this);
    	// todo add customer to bankdata not manager |Manager.addCustomer(this);
    }

    public void makeCheckingAccount(Currency c) {
    	CheckingAccount acc = new CheckingAccount(c);
    	this.checkingAccounts.add(acc);
        db.addCheckingAccount(this, acc);
    }

    public void makeSavingsAccount(Currency c) {
    	SavingsAccount acc = new SavingsAccount(c, db);
    	this.savingsAccounts.add(acc);
        db.addSavingAccount(this, acc);
    }
	
	public void makeSecurityAccount(Currency c) {
		SecurityAccount acc = new SecurityAccount(c, db);
		this.securityAccounts.add(acc);
        db.addSecurityAccount(this, acc);
	}  	

    public List<CheckingAccount> getCheckingAccounts() {
        return this.checkingAccounts;
    }

    public List<SavingsAccount> getSavingAccounts() {
        return this.savingsAccounts;
    }
    
    public List<SecurityAccount> getStockAccounts() {
        return this.securityAccounts;   
    }

}
