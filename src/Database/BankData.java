// class which connects to our MySQL database
package Database;

import bank.*;
;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BankData {
    //public static final GUI.ReportView();

    // Source for connection code:
    // https://www.javaworld.com/article/3388036/what-is-jdbc-introduction-to-java-database-connectivity.html
    // https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-connect-drivermanager.html

	Connection conn = null;
	public static int mostRecentID = 0;

    public BankData() {
        // connect to db on creation
        connect();
    }

    public void connect() {
        try {
            // Get connection to DB
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bank";
            String username = "admin";
            String pass = "admin";
            // Create statement
            conn=DriverManager.getConnection(url, username, pass);
            // execute sql
            String cmd = "SELECT * FROM customers";
            ResultSet rs = getRsFromCmd(cmd);
            while(rs.next())
                System.out.println(rs.getString("last_name") + ", " + rs.getString("first_name"));
        }catch(Exception e){ System.out.println(e);}
    }

	public void close() {
		try {
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// INSERT new entries into database

	// inserts each new customer into the database
	public void addCustomer(Customer customer) {
        int id = getNewID();
        String first_name = customer.getFirstName();
        String last_name = customer.getLastName();
        String email = customer.getEmail();
        String password = customer.getPassword();
        String cmd = "INSERT INTO bank.customers(id, first_name, last_name, email, password) VALUES (" +
                id + ", '" + first_name + "', '" + last_name + "', '" + email + "', '" + password + "');";
        execute(cmd);
    }

    // inserts new accounts
    private <T extends AccountType> void addAccount(Customer customer, T acct, String acctType) {
        int acc_num = getNewID();
        String cust_email = customer.getEmail();
        double balance = acct.getBalance().getValue();
        double acc_open_fee = AccountType.accountOpenFee;
        double acc_closed_fee = AccountType.accountClosedFee;
        double withdrawl_fee = AccountType.withdrawlFee;
        String cmd = "INSERT INTO bank.accounts(acc_num, cust_email, balance, acc_open_fee, acc_closed_fee, withdrawl_fee" +
                ") VALUES (" + acc_num + ", '" + cust_email + "', " + balance + ", " + acc_open_fee + ", " +
                acc_closed_fee + ", " + withdrawl_fee + ", '" + acctType + "');";
        execute(cmd);
    }
    public void addCheckingAccount(Customer customer, CheckingAccount checkingAccount){
        addAccount(customer, checkingAccount, "C");
    }
    public void addSavingAccount(Customer customer, SavingsAccount savingsAccount){
        addAccount(customer, savingsAccount, "S");
    }
    public void addSecurityAccount(Customer customer, SecurityAccount securityAccount){
        // todo fix according to stock info
        addAccount(customer, securityAccount, "A");
    }

    // inserts new stock available (controlled by Bank Manager)
    public void addStock(Stock stock){
        String name = stock.getCode();
        double price = stock.getPrice();
        int shares = stock.getShares();
        String cmd = "INSERT INTO bank.stocks(name, price, shares) VALUES ('" + name + ", " + price + ", " + shares + ");";
        execute(cmd);
    }

    // buy stock from the list of available stocks (Customer)
    public void buyStock(Stock stock, SecurityAccount securityAccount) {
        int acc_id = securityAccount.getAccID();
        String name = stock.getCode();
        double price = stock.getPrice();
        int shares = stock.getShares();
        String cmd = "INSERT INTO bank.bought_stocks(acc_id, name, price, shares) VALUES (" + acc_id + ", '" + name +
                "', " + price + ", " + shares + ");";
        execute(cmd);
    }

    // add transaction to total list of transactions (for Bank Manager)
    public void addTransaction(Transaction transaction){

        int acc_id = transaction.getAccount().getAccID();
        char acc_type = transaction.getAccount().getAccType();
        double amt = transaction.getAmt();

        String cmd = "INSERT INTO bank.transactions(acc_id, acc_type, amt) VALUES (" + acc_id + ", '" + acc_type +
                "', " + amt + ");";
        execute(cmd);
    }

    // add loan to total list of loans (for Bank Manager)
    public void addLoan(Loan loan, Customer customer){
        String cust_email = customer.getEmail();
        double amt = loan.getAmount();
        String collateral = loan.getCollateral();
        String cmd = " INSERT INTO bank.loans (cust_email, amt, collateral) VALUES ('" + cust_email + "', " +
                amt + ", '" + collateral + "');";
        execute(cmd);
    }


    // SELECT / reading elements from database

    // get list of customers (for Bank Manager use)
    public List<Customer> readCustomers(){
        List<Customer> list = new ArrayList<>();
        try {
            String cmd = "SELECT * FROM bank.customers";
            ResultSet rs = getRsFromCmd(cmd);
            Customer temp;
            while(rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                temp = new Customer(firstName, lastName, email, password, this);
                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }

    // returns list of all pending loans for a particular customer
    public List<Loan> readLoansFor(Customer customer){
        // todo fix according to table
        List<Loan> list = new ArrayList<>();
        try {
            String cmd = "SELECT * FROM bank.loans WHERE cust_email = '" + customer.getEmail() + "';";
            ResultSet rs = getRsFromCmd(cmd);
            Loan temp;
            while(rs.next()) {
                temp = new Loan(getNewID(), rs.getDouble("amt"), 0.1, rs.getString("collateral"));
                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }
    // returns list of all pending loans for all customers (Bank Manager view)
    public List<Loan> readLoans(){
        // todo fix according to table
        List<Loan> list = new ArrayList<>();
        try {
            String cmd = "SELECT * FROM bank.loans";
            ResultSet rs = getRsFromCmd(cmd);
            Loan temp;
            while(rs.next()) {
                temp = new Loan(getNewID(), rs.getDouble("amt"), 0.1, rs.getString("collateral"));
                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }
    // returns list of checking accounts for a particular customer
    public List<CheckingAccount> readCheckingAccounts(Customer customer){
        List<CheckingAccount> list = new ArrayList<>();
        try {
            String cmd = "SELECT * FROM bank.accounts WHERE acc_type = 'C' AND cust_email = '" + customer.getEmail() + "';";
            ResultSet rs = getRsFromCmd(cmd);
            CheckingAccount temp;
            while(rs.next()) {

                temp = new CheckingAccount(new Currency(CurrencyType.USD, rs.getDouble("balance")), this);
                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }
    // returns list of savings accounts for a particular customer
    public List<SavingsAccount> readSavingAccounts(Customer customer){
        List<SavingsAccount> list = new ArrayList<>();
        try {
            String cmd = "SELECT * FROM bank.accounts WHERE acc_type = 'S' AND cust_email = '" + customer.getEmail() + "';";
            ResultSet rs = getRsFromCmd(cmd);
            SavingsAccount temp;
            while(rs.next()) {

                temp = new SavingsAccount(new Currency(CurrencyType.USD, rs.getDouble("balance")), this);
                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }
    // returns list of security accounts for a particular customer
    public List<SecurityAccount> readSecurityAccounts(Customer customer){
        List<SecurityAccount> list = new ArrayList<>();
        try {
            String cmd = "SELECT * FROM bank.accounts WHERE acc_type = 'A' AND cust_email = '" + customer.getEmail() + "';";
            ResultSet rs = getRsFromCmd(cmd);
            SecurityAccount temp;
            while(rs.next()) {

                temp = new SecurityAccount(new Currency(CurrencyType.USD, rs.getDouble("balance")), this);

                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }
    // returns list of all available stocks (for Customer when buying stocks)
    public List<Stock> readStocks(){
        List<Stock> list = new ArrayList<>();
        try {
            String cmd = "SELECT * FROM bank.stocks";
            ResultSet rs = getRsFromCmd(cmd);
            Stock temp;
            while(rs.next()) {

                temp = new Stock(rs.getString("name"), rs.getDouble("price"), rs.getInt("shares"));

                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }

    // returns list of stocks belonging to a specific security account (customer)
    public List<Stock> readStocksFor(SecurityAccount securityAccount){
        List<Stock> list = new ArrayList<>();
        try {
            String cmd = "SELECT * FROM bank.bought_stocks WHERE acc_id = "+ securityAccount.getAccID() +";";
            ResultSet rs = getRsFromCmd(cmd);
            Stock temp;

            while(rs.next()) {
                temp = new Stock(rs.getString("name"), rs.getDouble("price"), rs.getInt("shares"));
                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }

    // returns list of transactions (Bank Manager Daily Digest)
    public List<Transaction> readTransactions(){
        // TODO change account constructors such that they read from actual accounts
        List<Transaction> list = new ArrayList<>();

        try {
            String cmd = "SELECT * FROM bank.transactions;";
            ResultSet rs = getRsFromCmd(cmd);
            Transaction temp;
            while(rs.next()) {
                Currency amt = new Currency(CurrencyType.USD, rs.getDouble("amt"));
                String acc_type = rs.getString("acc_type");
                AccountType acct;
                if (acc_type.equals("C")) {
                    acct = new CheckingAccount(amt, this);
                }
                else if (acc_type.equals("S")) {
                    acct = new SavingsAccount(amt, this);
                }
                else {
                    acct = new SecurityAccount(amt, this);
                }
                temp = new Transaction(amt, acct);
                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }


    public String getCredentials(String email) throws SQLException {
        String pass = "";
        String cmd = "SELECT * FROM bank.customers WHERE email='" + email + "';";
        ResultSet rs = getRsFromCmd(cmd);
        while(rs.next()) {
            pass = rs.getString("password");
        }
        System.out.println(rs);
        return pass;
    }

    // UPDATE -- change the values in a particular table of the db

    // when a customer withdraws or deposits from checking or savings (security is handled by updateStockX methods)
    public void updateCheckingAccount(CheckingAccount checkingAccount){
        String cmd = "UPDATE bank.accounts SET balance = " + checkingAccount.getBalance() + " WHERE acc_num = " +
                checkingAccount.getAccID() + ";";
        execute(cmd);
    }

    public void updateSavingAccount(SavingsAccount savingsAccount){
        String cmd = "UPDATE bank.accounts SET balance = " + savingsAccount.getBalance() + " WHERE acc_num = " +
                savingsAccount.getAccID() + ";";
        execute(cmd);
    }

    public void updateStock(Stock stock){
        String cmd = "UPDATE bank.stocks SET price = " + stock.getPrice() + ", shares = "+ stock.getShares()+
                " WHERE (name = '"+stock.getCode()+"');";
        execute(cmd);
    }

    public void updateBoughtStock(Stock stock, SecurityAccount account){
        String cmd = "UPDATE bank.bought_stocks SET price = "+ stock.getPrice() +", shares = " + stock.getShares()
                + " WHERE (name = '"+stock.getCode()+"' AND account_id = " + account.getAccID()+");";
        execute(cmd);
    }


    // helpers for reading/writing to database

    private ResultSet getRsFromCmd(String cmd) {
        ResultSet rs = null;
        try {
            Statement stmt=conn.createStatement();
            rs = stmt.executeQuery(cmd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    private void execute(String sql){
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        }
        catch(Exception e){ System.out.println(e);}
    }

    public static int getNewID() {
	    Random rand = new Random();
        int x = rand.nextInt(999) + 1000;
        return x;
    }

	// for testing
	public static void main(String[] args) {
        BankData db = new BankData();
        String cmd = "INSERT INTO bank.customers(id, first_name, last_name, email, password) VALUES (1, 'elyse', 'test'" +
                ", 'test', 'test');";
        db.execute(cmd);
	}



}
