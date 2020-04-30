public class Currency {
    private String currency;
    private char symbol;
    private double balance;
    public Currency(String name, char symbol, double balance) {
        this.currency = name;
        this.symbol = symbol;
        this.balance = balance;
    }
    // CNY ￥；EUR €
    public Currency(){
        this("USD",'$',0);
    }

    public String getCurrency(){
        return currency;
    }
    public char getSymbol() {
        return symbol;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double d){
        balance+=d;
    }


}
