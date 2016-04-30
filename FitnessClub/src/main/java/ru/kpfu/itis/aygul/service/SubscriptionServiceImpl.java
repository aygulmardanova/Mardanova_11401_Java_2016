package ru.kpfu.itis.aygul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.aygul.model.Subscription;
import ru.kpfu.itis.aygul.repository.SubscriptionRepository;
import ru.kpfu.itis.aygul.service.interfaces.SubscriptionService;

import java.util.List;
/**
 * Created by aygulmardanova on 28.04.16.
 */
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Override
    public List<Subscription> getAll() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription getSubscriptionById(int id) {
        return subscriptionRepository.findOneById(id);
    }

    @Override
    public Subscription getSubscriptionByValidity(int validity) {
        return subscriptionRepository.findOneByValidity(validity);
    }

    @Override
    public Subscription getSubscriptionByPrice(int price) {
        return subscriptionRepository.findOneByPrice(price);
    }

    @Override
    public void updateSubscriptionPrice(int validity, int new_price) {
        Subscription subscription = subscriptionRepository.findOneByValidity(validity);
        if (subscription == null) {
            subscription = new Subscription();
            subscription.setValidity(validity);
            subscription.setPrice(new_price);
        } else {
            subscription.setPrice(new_price);
        }
        subscriptionRepository.save(subscription);

    }

    @Override
    public Subscription getSubscriptionByValidityAndPrice(int validity, int price) {
        return subscriptionRepository.findOneByValidityAndPrice(validity, price);
    }


    @Override
    public void addSubscription(int validity, int price) {
        Subscription subscription = new Subscription();
        subscription.setValidity(validity);
        subscription.setPrice(price);
        subscriptionRepository.save(subscription);
    }

    @Override
    public int findMinPrice() {
        return subscriptionRepository.findMinPrice();
    }
}
