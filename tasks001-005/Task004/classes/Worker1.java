package Task004.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Айгуль on 24.02.2016.
 */
public class Worker1 implements Worker {

    private List<Cheque> cheques;
    private String name;
    private static Worker1 worker1 = new Worker1("Ivan");

    private Worker1(String name) {
        cheques = new ArrayList<>();
        this.name = name;
    }

    public void clearCheques() {
        this.cheques = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public static Worker1 getWorker() {
        return worker1;
    }

    @Override
    public int addCheque(Cheque c) {
        this.cheques.add(c);
        return cheques.size() - 1;
    }

    @Override
    public String toString() {
        return "Worker1{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int chequesCount() {
        return cheques.size();
    }

    public String askCardNumber() {
        System.out.println("Please, enter your card number.");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    @Override
    public Cheque openCheque(String paymentType) {
        switch (paymentType) {
            case "cash": {
                CashCheque cheque = new CashCheque(cheques.size() + 1);
                cheques.add(cheque);
                return cheque;
            }
            case "card": {
                String cardNumber = askCardNumber();
                CardCheque cheque = new CardCheque(cardNumber, cheques.size() + 1);
                cheques.add(cheque);
                return cheque;
            }
            default: return null;
        }
    }

    @Override
    public double totalAmount(int number) {
        return cheques.get(number - 1).getTotalAmount();
    }
}
