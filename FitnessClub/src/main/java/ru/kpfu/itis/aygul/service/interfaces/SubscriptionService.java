package ru.kpfu.itis.aygul.service.interfaces;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.aygul.model.Subscription;

import java.util.List;

/**
 * Created by aygulmardanova on 28.04.16.
 */

public interface SubscriptionService {

    List<Subscription> getAll();

    Subscription getSubscriptionById(int id);

    Subscription getSubscriptionByValidity(int validity);

    Subscription getSubscriptionByPrice(int price);

    Subscription getSubscriptionByValidityAndPrice(int validity, int price);

    void updateSubscriptionPrice(int validity, int new_price);

    void updateSubscriptionPriceById(int id, int price);

    void addSubscription(int validity, int price);

    int findMinPrice();

    boolean ifValidityAlreadyExists(int validity);

    void deleteSubscription(int id);


}
