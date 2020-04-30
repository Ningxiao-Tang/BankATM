// class representing an amount of RMB
public class RMB extends Currency {
	public RMB(float value) {
		this.type = "rmb";
		this.symbol = '￥';
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
