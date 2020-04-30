// class representing an amount of EURO
public class EURO extends Currency {
	public EURO(float value) {
		this.type = "euro";
		this.symbol = '€';
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
