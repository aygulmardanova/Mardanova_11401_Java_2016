package ru.kpfu.itis.aygul.service.interfaces;

import ru.kpfu.itis.aygul.model.Purchase;
import ru.kpfu.itis.aygul.model.User;

import java.sql.Date;
import java.util.List;

/**
 * Created by aygulmardanova on 11.05.16.
 */
public interface PurchaseService {

    List<Purchase> getAll();

    Purchase getById(int id);

    Purchase getByUserAndBuyDate(User user, Date date);

    List<Purchase> getAllPurchasesByUser(User user);

    void addPurchase(String login, int subscr_id);

    Purchase getLastPurchaseForUser(User user);
}
