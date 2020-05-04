// class which connects to our MySQL database


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class BankData {

	// Source for connection code:
	// https://www.javaworld.com/article/3388036/what-is-jdbc-introduction-to-java-database-connectivity.html
	// https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-connect-drivermanager.html

	Connection conn = null;

	public BankData {
		// connect to db on creation
		connect();
	}

	public void connect() {
		// todo fix
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/bank_atm";
            conn=DriverManager.getConnection(url,"admin","admin");
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select * from customers");
            while(rs.next())
            	// todo change "name" to "2"?
                System.out.println(rs.getString("name"));
        }catch(Exception e){ System.out.println(e);}
	}

	public void close() {
		try {
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void readCustomers() {
		// todo complete
	}
}
