// class representing an amount of USD
public class USD extends Currency {
	public USD(float value) {
		this.type = "usd";
		this.symbol = '$';
		this.value = value;
	}
	protected USD convertToUSD() {
		// todo complete
	}
	protected EURO convertToEURO() {
		// todo complete
	}
	protected RMB convertToRMB() {
		// tdoo complete
	}
}
