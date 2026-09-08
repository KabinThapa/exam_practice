public class new {
    private double balance;
    public new(double balance){
        this.balance = balance;
        }
    public synchronized void deposit(double amount){
            double current = balance;

            try{
                Thread.sleep(500);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
            balance = current+amount;
        }
}