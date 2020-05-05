/*
The SecurityAccount class represents a stock invesstment account that will be held by a customer.
*/

import Database.BankData;

public class SecurityAccount extends AccountType {

	private Currency realizedProfits;
	private Currency unrealizedProfits;
	private List<Stock> boughtStocks = BankData.readStocksFor(this);
	
	public SecurityAccount(Currency c) {
		super(c);
	}

	public List<Stock> viewStockOptions() {
		return BankData.readStocks();
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
