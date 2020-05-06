/*
	Class representing of currency, it can hold 3 different currency types (which come from the enum file CurrencyTypes)
*/

package bank;
public class Currency {

	private CurrencyType type;
	private double value;
	private char symbol;

	public Currency(CurrencyType type, double value) {
		this.type = type;
		this.value = value;
		switch (this.type) {
			case USD:
				this.symbol = '$';
			case EURO:
				this.symbol = '€';
			case RMB:
				this.symbol = '¥';
		}
	}
	
	// getters for currency attributes
	public CurrencyType getType() {
		return type;
	}

	public double getValue() {
		return value;
	}

	public char getSymbol() {
		return symbol;
	}

	// public <T extends Currency> T convert(String toType) {
	// 	if (toType.equals("usd")) { return this.convertToUSD()};
	// 	else if (toType.equals("euro")) {return this.convertToEURO()};
	// 	else if (toType.equals("rmb")) {return this.convertToRMB()};
	// 	else {
	// 		throw new IllegalArgumentException("Not a valid currency type.");
	// 	}
	// }

	public double convert(CurrencyType toConvert) {
		// converts the current currency to the inputted type and returns its value
		double ret = 0;
		switch (toConvert) {
		    case USD:
		    	if (this.type == CurrencyType.EURO) { ret = this.value * 1.11; }
		    	if (this.type == CurrencyType.RMB) { ret = this.value * 0.14; }
		    case EURO:
		    	if (this.type == CurrencyType.USD) { ret = this.value *  0.9; }
		    	if (this.type == CurrencyType.RMB) { ret = this.value * 0.13; }
		    case RMB:
		    	if (this.type == CurrencyType.USD) { ret = this.value *  7.06; }
		    	if (this.type == CurrencyType.EURO) { ret = this.value * 7.84; }
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + toConvert);
		}
		return ret;
	}

	// setters for currency value 
	public void add(double amt) {
		this.value += amt;
	}

	public void subtract(double amt) {
		this.value -= amt;
	}

	public void setValue(double amt) {
		this.value = amt;
	}

	// public <T extends Currency> boolean equals(T currency) {
	// 	Currency convertedCur = currency.convert(this.type);
	// 	return (this.getValue() == convertedCur.getValue());
	// }
}