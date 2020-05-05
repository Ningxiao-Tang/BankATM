import java.util.ArrayList;

public class Customer extends User {

    private List<CheckingAccounts> checkingAccounts = readSavingAccounts(this);
    private List<SavingsAccount> savingsAccounts = readSavingAccounts(this);
    private List<SecurityAccount> securityAccounts = readSecurityAccounts(this);

    public Customer(String name, String password, String email, String address) {
        super(name, password, email, address);
    	Manager.addCustomer(this);
    }

    public void makeCheckingAccount() {
    	CheckingAccount acc = new CheckingAccount();
    	this.checkingAccounts.add(acc);
        BankATM.db.addCheckingAccount(this, acc);
    }

    public void makeSavingsAccount() {
    	SavingAccounts acc = new SavingAccounts();
    	this.savingsAccounts.add(acc);
        BankATM.db.addCheckingAccount(this, acc);
    }
	
	public void makeInvestmentAccount() {
		SecurityAccount acc = new SecurityAccount();
		this.securityAccounts.add(acc);
        BankATM.db.addCheckingAccount(this, acc);
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
