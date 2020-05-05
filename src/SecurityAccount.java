/*
The SecurityAccount class represents a stock invesstment account that will be held by a customer.
*/

public class SecurityAccount {

	private Currency realizedProfits;
	private Currency unrealizedProfits;
	private List<Stock> boughtStocks = BankATM.db.readStocksFor(this);

	public List<Stock> viewStockOptions() {
		return BankATM.db.readStocks();
	}

	public void buyStock(Stock stock) {
		BankATM.db.buyStock(stock, this);
		this.boughtStocks.add(stock);
	}

	public void sellStock(Stock stock) {
		// add database statement
		this.boughtStocks.remove(boughtStocks.indexOf(stock));
	}

}
