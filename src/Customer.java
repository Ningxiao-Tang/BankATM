import java.util.ArrayList;

public class Customer extends User {

    private String name;
    private String password;
    private String address;
    private String email;
    private int id;
    private ArrayList<CheckingAccounts> checkingAccounts= new ArrayList<CheckingAccount>();
    private ArrayList<SavingAccounts> savingAccounts= new ArrayList<SavingAccounts>();
    private ArrayList<StockAccounts> stockAccount= new ArrayList<StockAccount>();

    public Customer(String name, String password, String email, String address) {
    	this.name = name;
    	this.password = password;
    	this.email = email;
    	this.address = address;
    	this.id = Manager.getCustomers().size() + 1;

    	Manager.addCustomer(this);
    }

    /* Getters of customer attributes */
    public String getName() {
    	return name;
    }

    public String getAddress() {
    	return address;
    }

    public String getEmail() {
    	return email;
    }

    public int getId() {
    	return id;
    }
    /* end of getters */

    public void makeCheckingAccount() {
    	CheckingAccount acc = new CheckingAccount();
    	this.checkingAccounts.add(acc);
    }

    public void makeSavingsAccount() {
    	SavingAccounts acc = new SavingAccounts();
    	this.savingAccounts.add(acc);
    }
	
	public void makeInvestmentAccount() {
		StockAccount acc = new StockAccount();
		this.stockAccount.add(acc);

	}  	

}
