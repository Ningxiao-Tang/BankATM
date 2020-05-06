/*
The SecurityAccount class represents a stock invesstment account that will be held by a customer.
*/

package bank;
import java.util.List;
import Database.BankData;

public class SecurityAccount extends AccountType {

	private Currency realizedProfits;
	private Currency unrealizedProfits;
	private BankData db;
	private List<Stock> boughtStocks;
	
	public SecurityAccount(Currency c, BankData db) {
		super(c);
		boughtStocks = db.readStocksFor(this);
	}

	public List<Stock> viewStockOptions() {
		return db.readStocks();
	}

	public void buyStock(Stock stock) {
		BankData.buyStock(stock, this);
		this.boughtStocks.add(stock);
	}

	public void sellStock(Stock stock) {
		// add database statement
		this.boughtStocks.remove(boughtStocks.indexOf(stock));
	}

}
