package ru.kpfu.itis.aygul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.aygul.model.Purchase;
import ru.kpfu.itis.aygul.model.User;

import java.sql.Date;
import java.util.List;

/**
 * Created by aygulmardanova on 11.05.16.
 */
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    List<Purchase> findAll();

    Purchase findOneById(int id);

    List<Purchase> findAllByUser(User user);

    Purchase findOneByUserAndBuyDate(User user, Date buyDate);

    List<Purchase> findAllByUserOrderByBuyDateAsc(User user);

//    @Query("select p from Purchase p ")
//    Purchase findOneByUserWhereMaxBuyDate(User user);
}
