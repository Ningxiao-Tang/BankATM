// abstract class representing a type of currency
public abstract class Currency {
	private String type;
	private double value;
	private char symbol;

	public Currency() {
		// noarg
	}

	public String getType() {
		return type;
	}

	public double getValue() {
		return value;
	}

	public <T extends Currency> T convert(String toType) {
		if (toType.equals("usd")) { return this.convertToUSD()};
		else if (toType.equals("euro")) {return this.convertToEURO()};
		else if (toType.equals("rmb")) {return this.convertToRMB()};
		else {
			throw new IllegalArgumentException("Not a valid currency type.");
		}
	}

	protected abstract USD convertToUSD(); 
	protected abstract EURO convertToEURO();
	protected abstract RMB convertToRMB();

	public void add(double amt) {
		this.value += amt;
	}

	public void subtract(double amt) {
		this.value -= amt;
	}

	public void setValue(double amt) {
		this.value = amt;
	}

	public <T extends Currency> boolean equals(T currency) {
		Currency convertedCur = currency.convert(this.type);
		return (this.getValue() == convertedCur.getValue());
	}
}