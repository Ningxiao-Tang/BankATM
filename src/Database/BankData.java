// class which connects to our MySQL database
package Database;

import java.sql.*;

// TODO take out commented code
public class BankData {

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
                id + ", '" + first_name + ", " + last_name + ", " + email + ", " + password + ");";
    }

    // inserts new accounts
    public void addCheckingAccount(Customer customer, CheckingAccount checkingAccount){
        int acc_num = getNewID();
        String cust_email = customer.getEmail();
        double balance = checkingAccount.getBalance().getValue();
        double acc_open_fee = checkingAccount.accountOpenFee;
        double acc_closed_fee = checkingAccount.accountClosedFee;
        double withdrawl_fee = checkingAccount.withdrawlFee;
        String cmd = "INSERT INTO bank.accounts(acc_num, cust_email, balance, acc_open_fee, acc_closed_fee, withdrawl_fee" +
                ") VALUES (" + acc_num + ", " + cust_email + ", " + balance + ", " + acc_open_fee + ", " +
                acc_closed_fee + ", " + withdrawl_fee + ", 'C');";
        execute(cmd);
    }
    public void addSavingAccount(Customer customer, SavingsAccount savingsAccount){
        // todo complete according to table
        String cmd = "INSERT INTO bank.accounts (balance, routing_num, acc_num, active, open_fee, close_fee, interest, type, person_name, routing_acc) VALUES (\'";
        execute(cmd);
    }
    public void addSecurityAccount(Customer customer, SecurityAccount securityAccount){
        // todo complete according to table
        String cmd = "INSERT INTO bank.accounts (balance, routing_num, acc_num, active, open_fee, close_fee, type, person_name, routing_acc) VALUES (\'" ;
        execute(cmd);
    }

    // inserts new stock available (controlled by Bank Manager)
    public void addStock(Stock stock){
        // todo complete according to table
        String cmd = "INSERT INTO bank.stocks (name, price, total_shares, avai_shares) VALUES (\'";
        execute(cmd);
    }

    // buy stock from the list of available stocks (Customer)
    public void buyStock(Stock stock, SecurityAccount securityAccount) {
        // todo complete according to table
        String cmd = "INSERT INTO bank.bought_stocks (share_amount, worth, account_id, stock_name) VALUES (\'";
        execute(cmd);
    }

    // add transaction to total list of transactions (for Bank Manager)
    public void addTransaction(Transaction transaction){
        // todo complete according to table
        String cmd = "INSERT INTO bank.transactions (sender_acc_num, sender_routing_num, rec_acc_num, rec_routing_num, currency, amount, type)";
        execute(cmd);
    }
    // add loan to total list of loans (for Bank Manager)
    public void addLoan(Loan loan, CustomerAccount customerAccount){
        // todo complete according to table
        String cmd = " INSERT INTO bank.loans (initial_amount, debt, owner, interest) VALUES (\'";
        execute(cmd);
    }


    // SELECT / reading elements from database

    // get list of customers (for Bank Manager use)
    public List<Customer> readCustomers(){
        // todo fix according to table
        List<Customer> list = new ArrayList<>();
        try {
            String cmd = "SELECT * FROM bank.customers";
            ResultSet rs = getRsFromCmd(cmd);
            Customer temp;
            while(rs.next()) {
                String name = rs.getString("name");
                String[] names = name.split(" ");
                temp = new Customer(names[0], names[1]);
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
            String cmd = "SELECT * FROM bank.loans WHERE owner = \'"+firstName + " " + lastName+"\'";
            ResultSet rs = getRsFromCmd(cmd);
            Loan temp;
            while(rs.next()) {
                temp = new Loan(new Currency("USD"), rs.getFloat("interest"), rs.getFloat("initial_amount"));
                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }
    // returns list of all pending loans for all customer (Bank Manager view)
    public List<Loan> readLoans(){
        // todo fix according to table
        List<Loan> list = new ArrayList<>();
        try {
            String cmd = "SELECT * FROM bank.loans";
            ResultSet rs = getRsFromCmd(cmd);
            Loan temp;
            while(rs.next()) {
                temp = new Loan(new Currency("USD"), rs.getFloat("interest"), rs.getFloat("initial_amount"));
                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }
    // returns list of checking accounts for a particular customer
    public List<CheckingAccount> readCheckingAccounts(Customer customer){
        // todo fix according to table
        List<CheckingAccount> list = new ArrayList<>();
        try {
            String cmd = "SLEECT * FROM account WHERE type = \'C\' AND person_name = \'"+ firstName + " " + lastName+"\'";
            ResultSet rs = getRsFromCmd(cmd);
            CheckingAccount temp;
            while(rs.next()) {

                temp = new CheckingAccount(rs.getFloat("balance"), rs.getInt("routing_num"), rs.getInt("acc_num"), rs.getBoolean("active"), new Currency("USD")
                        , rs.getFloat("close_fee"), rs.getFloat("open_fee"), rs.getFloat("transaction_fee"), rs.getFloat("withdrawal_fee"));
                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }
    // returns list of savings accounts for a particular customer
    public List<SavingsAccount> readSavingAccounts(Customer customer){
        // todo fix according to table & object constructor
        List<SavingsAccount> list = new ArrayList<>();
        try {
            String cmd = "SELECT * FROM account WHERE type = \'S\' AND person_name = \'"+ firstName + " " + lastName+"\'";
            ResultSet rs = getRsFromCmd(cmd);
            SavingsAccount temp;
            while(rs.next()) {

                temp = new SavingsAccount(rs.getFloat("balance"), rs.getInt("routing_num"), rs.getInt("acc_num"), rs.getBoolean("active"), new Currency("USD")
                        , rs.getFloat("close_fee"), rs.getFloat("open_fee"), rs.getFloat("interest"));
                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }
    // returns list of security accounts for a particular customer
    public List<SecurityAccount> readSecurityAccounts(Customer customer){
        // todo fix according to table and object constructor
        List<SecurityAccount> list = new ArrayList<>();
        try {
            String cmd = "SELECT * FROM account WHERE type = \'I\' AND person_name = \'"+ firstName + " " + lastName+"\';";
            ResultSet rs = getRsFromCmd(cmd);
            SecurityAccount temp;
            while(rs.next()) {

                temp = new SecurityAccount(rs.getFloat("balance"), rs.getInt("routing_num"), rs.getInt("acc_num"), rs.getBoolean("active"), new Currency("USD")
                        , rs.getFloat("close_fee"), rs.getFloat("open_fee"));

                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }
    // returns list of all available stocks (for Customer when buying stocks)
    public List<Stock> readStocks(){
        // TODO fix according to table and object constructor
        List<Stock> list = new ArrayList<>();
        try {
            String cmd = "SELECT * FROM stock";
            ResultSet rs = getRsFromCmd(cmd);
            Stock temp;
            while(rs.next()) {

                temp = new Stock(rs.getFloat("price"), rs.getInt("total_shares"), rs.getInt("avai_shares"), rs.getString("name"));

                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }

    // returns list of stocks belonging to a specific security account (customer)
    public List<Stock> readStocksFor(SecurityAccount securityAccount){
        // TODO fix according to table and object constructor
        List<Stock> list = new ArrayList<>();
        try {
            String cmd = "SELECT * FROM bank.bought_stocks WHERE account_id = "+securityAccount.getRoutingNumber() + securityAccount.getAccountNumber()+";";
            ResultSet rs = getRsFromCmd(cmd);
            BoughtStock temp;

            while(rs.next()) {
                String stock_name = rs.getString("stock_name");
                Stock stock = readStockByName(stock_name);
                temp = new Stock(stock, rs.getInt("share_amount"));

                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }

    // returns list of transactions (Bank Manager Daily Digest)
    public List<Transaction> readTransactions(){
        // TODO fix according to table and object constructor
        List<Transaction> list = new ArrayList<>();
        try {
            String cmd = "SELECT * FROM transaction WHERE type = \'D\' AND rec_acc_num = \'"+ acc_num +"\' AND  rec_routing_num = \'" + routing_num +"\'";
            ResultSet rs = getRsFromCmd(cmd);
            Transaction temp;
            while(rs.next()) {
                temp = new Transaction(rs.getFloat("amount"), new Currency(rs.getString("currency")),rs.getInt("rec_acc_num"), rs.getInt("rec_routing_num"));
                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }
    public List<Transaction> readTransactionsFor(Customer customer){
        // TODO fix according to table and object constructor
        List<Transaction> list = new ArrayList<>();
        try {
            String cmd = "SELECT * FROM transaction WHERE type = \'D\' AND rec_acc_num = \'"+ acc_num +"\' AND  rec_routing_num = \'" + routing_num +"\'";
            ResultSet rs = getRsFromCmd(cmd);
            Transaction temp;
            while(rs.next()) {
                temp = new Transaction(rs.getFloat("amount"), new Currency(rs.getString("currency")),rs.getInt("rec_acc_num"), rs.getInt("rec_routing_num"));
                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }


    // UPDATE -- change the values in a particular table of the db

    // when a customer withdraws or deposits from checking or savaings (security is handled by updateStockX methods)
    public void updateCheckingAccount(CheckingAccount checkingAccount){
        // TODO fix according to table and object constructor
        String cmd = "UPDATE bank.account SET balance = \'" + checkingAccount.getBalanceInLocalCurrency() +  "\', active = "+ checkingAccount.isActive() + " , open_fee = \'" + checkingAccount.getOpeningCharge() +"\', close_fee = \'" + checkingAccount.getClosingCharge() +"\', transaction_fee = \'" + checkingAccount.getTransferFee() + "\', " +
                "withdrawal_fee = \'"+ checkingAccount.getWithdrawalFee() +"\' WHERE (acc_num = \' "+ checkingAccount.getAccountNumber()+" \' AND routing_num = \'"+ checkingAccount.getAccountNumber()+ " \');\n";
        execute(cmd);
    }

    public void updateSavingAccount(SavingsAccount savingsAccount){
        // TODO fix according to table and object constructor
        String cmd = "UPDATE bank.accounts SET balance = \'" + savingsAccount.getBalanceInLocalCurrency() +  "\', active = "+ savingsAccount.isActive() + " , open_fee = \'" + savingsAccount.getOpeningCharge() +"\', close_fee = \'" + savingsAccount.getClosingCharge() +"\'," +
                "interest = \'"+ savingsAccount.getInterest() +"\' WHERE (acc_num = \' "+ savingsAccount.getAccountNumber()+" \' AND routing_num = \'"+ savingsAccount.getAccountNumber()+ " \');\n";
        execute(cmd);
    }

    public void updateStock(Stock stock){
        // TODO fix according to table and object constructor
        String cmd = "UPDATE bank.stocks SET price = \'" +stock.getCurrentPrice()+ "\', total_shares = \'"+ stock.getTotalShares()+"\', avai_shares = \'"+stock.getCurrentlyAvailableShares()+"\', WHERE (name = \'"+stock.getName()+"\');";
        execute(cmd);
    }

    public void updateBoughtStock(Stock stock, SecurityAccount account){
        // TODO fix according to table and object constructor
        String cmd = "UPDATE bank.bought_stocks SET share_amount = \'"+ boughtStock.getAmountOfStocks() +"\', worth = \'" + boughtStock.getTotalAmountSpentOnBuying()+ "\' WHERE (stock_name = \'"+boughtStock.getStock().getName()+"\' AND account_id = \'" + account.getAccountNumber()+"\');";
        execute(cmd);
    }

    // DELETE things from db tables
    public void deletePerson(){
        // TODO fix according to table and object constructor
        String cmd = "DELETE FROM bank_atm.person";
        execute(cmd);
    }
    public void deleteAccount(){
        // TODO fix according to table and object constructor
        String cmd = "DELETE FROM bank_atm.account";
        execute(cmd);
    }
    public void deleteLoan(){
        // TODO fix according to table and object constructor
        String cmd = "DELETE FROM bank_atm.loan";
        execute(cmd);
    }
    public void deleteStock(){
        // TODO fix according to table and object constructor
        String cmd = "DELETE FROM bank_atm.stock";
        execute(cmd);
    }
    public void deleteBoughtStock(){
        // TODO fix according to table and object constructor
        String cmd = "DELETE FROM bank_atm.bought_stock";
        execute(cmd);
    }
    public void deleteTransaction(){
        // TODO fix according to table and object constructor
        String cmd = "DELETE FROM bank_atm.transaction";
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
	    mostRecentID++;
	    return mostRecentID;
    }

	// for testing
	public static void main(String[] args) {
		new BankData();
	}
    */
    // for testing
    public static void main(String[] args) {
        new BankData();
    }
}
