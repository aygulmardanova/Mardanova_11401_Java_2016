package classes;

/**
 * Created by Айгуль on 24.02.2016.
 */
public interface Worker {

    Cheque openCheque(String cardNumber);
    Cheque openCheque();
    double totalAmount(int number);
    String getName();
    int addCheque(Cheque c);
    int chequesCount();
    void clearCheques();
}
