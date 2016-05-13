package service;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.kpfu.itis.aygul.model.Purchase;
import ru.kpfu.itis.aygul.model.Subscription;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.repository.PurchaseRepository;
import ru.kpfu.itis.aygul.service.PurchaseServiceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by aygulmardanova on 12.05.16.
 */
public class PurchaseServiceTest {

    private static Date date;
    private static Purchase purchase;
    private static User user;
    private static Subscription subscription;
    private static List<Purchase> purchases;
    private static PurchaseRepository purchaseRepository;
    private static PurchaseServiceImpl purchaseService;

    private static final Date incor_date = new Date((new java.util.Date()).getTime() + 1000);
    private static final int incor_id = 1000;

    @BeforeClass
    public static void beforeMethod() {

        date = new Date((new java.util.Date()).getTime());

        purchases = new ArrayList<>();

        user = new User();
        user.setName("kolya");

        subscription = new Subscription();

        purchase = new Purchase();
        purchase.setUser(user);
        purchase.setProlong(0);
        purchase.setSubscription(subscription);
        purchase.setBuyDate(date);

        purchases.add(purchase);

        purchaseRepository = mock(PurchaseRepository.class);
        when(purchaseRepository.findAll()).thenReturn(purchases);

        when(purchaseRepository.findAllByUser(any(User.class))).thenReturn(null);
        when(purchaseRepository.findAllByUser(user)).thenReturn(purchases);

        when(purchaseRepository.findOneById(anyInt())).thenReturn(null);
        when(purchaseRepository.findOneById(purchase.getId())).thenReturn(purchase);

        when(purchaseRepository.findAllByUserOrderByBuyDateAsc(any(User.class))).thenReturn(null);
        when(purchaseRepository.findAllByUserOrderByBuyDateAsc(user)).thenReturn(purchases);

        when(purchaseRepository.findOneByUserAndBuyDate(any(User.class), any(Date.class))).thenReturn(null);
        when(purchaseRepository.findOneByUserAndBuyDate(user, date)).thenReturn(purchase);

        purchaseService = new PurchaseServiceImpl();
        purchaseService.setPurchaseRepository(purchaseRepository);
    }

    @Test
    public void getAllMethodShouldReturnCorrectList() {
        Assert.assertEquals(purchases, purchaseService.getAll());
    }

    @Test
    public void getAllPurchasesByUserShouldReturnCorrectListForExistingUser() {
        Assert.assertEquals(purchases, purchaseService.getAllPurchasesByUser(user));
    }

    @Test
    public void getAllPurchasesByUserShouldReturnNullIfUserNotExist() {
        Assert.assertNull(purchaseService.getAllPurchasesByUser(new User()));
    }

    @Test
    public void getByIdShouldReturnPurchaseForExistingId() {
        Assert.assertEquals(purchase, purchaseService.getById(purchase.getId()));
    }

    @Test
    public void getByIdShouldReturnNullForNotExistingId() {
        Assert.assertNull(purchaseService.getById(incor_id));
    }

    @Test
    public void getByUserAndBuyDateShouldReturnPurchaseIfEverythingCorrect() {
        Assert.assertEquals(purchase, purchaseService.getByUserAndBuyDate(user, date));
    }

    @Test
    public void getByUserAndBuyDateShouldReturnNullIfIncorrectUser() {
        Assert.assertNull(purchaseService.getByUserAndBuyDate(new User(), date));
    }

    @Test
    public void getByUserAndBuyDateShouldReturnNullIfIncorrectDate() {
        Assert.assertNull(purchaseService.getByUserAndBuyDate(user, incor_date));
    }

    @Test
    public void getLastPurchaseForUserShouldReturnPurchaseForCorrectUser() {
        Assert.assertEquals(purchase, purchaseService.getLastPurchaseForUser(user));
    }

    @Test
    public void getLastPurchaseForUserShouldReturnNullForNotExistUser() {
        Assert.assertNull(purchaseService.getLastPurchaseForUser(new User()));
    }
}
