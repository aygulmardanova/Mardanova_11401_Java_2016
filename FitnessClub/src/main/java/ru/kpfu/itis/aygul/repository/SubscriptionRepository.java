package ru.kpfu.itis.aygul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.aygul.model.Subscription;

import java.util.List;

/**
 * Created by aygulmardanova on 28.04.16.
 */
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    List<Subscription> findAll();

    Subscription findOneByValidity(int validity);

    Subscription findOneByPrice(int price);

    Subscription findOneByValidityAndPrice(int validity, int price);

    Subscription findOneById(int id);

    @Query("select min(s.price) from Subscription s")
    int findMinPrice();
}
