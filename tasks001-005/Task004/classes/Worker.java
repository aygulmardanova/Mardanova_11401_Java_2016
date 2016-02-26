package Task004.classes;

/**
 * Created by Айгуль on 24.02.2016.
 */
public interface Worker {

    Cheque openCheque(String paymentType);
    double totalAmount(int number);
    String getName();
    int addCheque(Cheque c);
    int chequesCount();
    void clearCheques();
}
