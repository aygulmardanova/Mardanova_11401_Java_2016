package task007.classes;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Cheque openCheque() {
        CashCheque cheque = new CashCheque(cheques.size() + 1);
        cheques.add(cheque);
        return cheque;
    }

    @Override
    public Cheque openCheque(String cardNumber) {
        CardCheque cheque = new CardCheque(cardNumber, cheques.size() + 1);
        cheques.add(cheque);
        return cheque;
    }

    @Override
    public double totalAmount(int number) {
        return cheques.get(number - 1).getTotalAmount();
    }
}
