package Task004.classes;

/**
 * Created by Айгуль on 17.02.2016.
 */
public interface Shop {

    Cheque startBuying(String paymentType);  //создаст чек по типу покупки
    String getName();
    String getAddress();
    void setWorker(Worker w);
    Worker getWorker();
    double askTotalAmount(int number);
    double sayTotalAmount(int number);
}
