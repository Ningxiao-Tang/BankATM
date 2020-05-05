public class Stock {
    private String code;
    private double price;
    private int shares;

    public Stock(String code, double price, int shares){
        this.code = code;
        this.price = price;
        this.shares = shares;
    }
    public String getCode(){
        return code;
    }
    public double getPrice(){
        return price;
    }
    public int getShares(){
        return shares;
    }
    public void setPrice(double d) {
        price = d;
    }
    public void setShares (int n) {
        shares = n;
    }
    public void setCode(String s){
        code = s;
    }

}
