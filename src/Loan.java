public class Loan {
    private int id;
    private double amount;
    private double interest;
    private String collateral;

    public Loan (int id, double amount, double interest) {
        this.id = id;
        this.amount = amount;
        this.interest = interest;
    }

    public int getId(){
        return id;
    }
    public void setId(int i){
        id = i;
    }
    public double getInterest(){
        return interest;
    }
    public void setInterest(double d) {
        interest = d;
    }
    public double getAmount(){
        return amount;
    }
    public void setAmount(double d){
        amount = d;
    }
}