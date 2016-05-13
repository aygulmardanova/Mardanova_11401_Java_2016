package ru.kpfu.itis.aygul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.aygul.model.Purchase;
import ru.kpfu.itis.aygul.model.Subscription;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.repository.PurchaseRepository;
import ru.kpfu.itis.aygul.repository.SubscriptionRepository;
import ru.kpfu.itis.aygul.repository.UserRepository;
import ru.kpfu.itis.aygul.service.interfaces.PurchaseService;

import java.sql.Date;
import java.util.List;

/**
 * Created by aygulmardanova on 11.05.16.
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Override
    public List<Purchase> getAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase getById(int id) {
        return purchaseRepository.findOneById(id);
    }

    @Override
    public Purchase getByUserAndBuyDate(User user, Date date) {
        return purchaseRepository.findOneByUserAndBuyDate(user, date);
    }

    @Override
    public List<Purchase> getAllPurchasesByUser(User user) {
        return purchaseRepository.findAllByUser(user);
    }

    @Override
    public void addPurchase(String login, int subscr_id) {
        User user = userRepository.findByLogin(login);
        Subscription subscription = subscriptionRepository.findOneById(subscr_id);
        Date date = new Date((new java.util.Date()).getTime());
        int prolong;
        List<Purchase> purchases = purchaseRepository.findAllByUser(user);
        if (purchases != null && purchases.size() != 0) {
            prolong = 1;
        } else {
            prolong = 0;
        }

        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setBuyDate(date);
        purchase.setProlong(prolong);
        purchase.setSubscription(subscription);
        purchaseRepository.save(purchase);
    }

    @Override
    public Purchase getLastPurchaseForUser(User user) {
        List<Purchase> purchases = purchaseRepository.findAllByUserOrderByBuyDateAsc(user);
        if (purchases != null && purchases.size() != 0) {
            return purchases.get(purchases.size() - 1);
        }
        return null;
    }


    public PurchaseRepository getPurchaseRepository() {
        return purchaseRepository;
    }

    public void setPurchaseRepository(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

}
