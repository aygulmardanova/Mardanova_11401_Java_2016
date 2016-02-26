package Task004.classes;

/**
 * Created by Айгуль on 21.02.2016.
 */
public class MyShop implements Shop {

    private String address;
    private Worker worker;

    public MyShop() {
        this.address = "England, London";
        this.worker = Worker1.getWorker();
    }

    @Override
    public void setWorker(Worker w) {
        this.worker = w;
    }

    @Override
    public Worker getWorker() {
        return worker;
    }

    @Override
    public double askTotalAmount(int number) {
        return sayTotalAmount(number);
    }

    @Override
    public double sayTotalAmount(int number) {
        double amount = worker.totalAmount(number);
        System.out.println("You have chosen goods for " + amount);
        return amount;
    }

    @Override
    public Cheque startBuying(String paymentType) {
        return worker.openCheque(paymentType);
    }

    @Override
    public String getName() {
        return "Best Shop in London";
    }

    @Override
    public String getAddress() {
        return address;
    }

}
