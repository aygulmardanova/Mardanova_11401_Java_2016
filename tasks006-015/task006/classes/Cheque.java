package task006.classes;

/**
 * Created by Айгуль on 17.02.2016.
 */
public interface Cheque {

    public void addGood(Object good);
    void viewGoods();   //выводит только названия товаров
    void viewPrices();  //выводит название товара + цену
    double getTotalAmount();    //выводит общую сумму
    String getPaymentType();  //card/cash
    void printCheque();
    int getNumber();
}
