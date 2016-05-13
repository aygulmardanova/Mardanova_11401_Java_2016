package service;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.kpfu.itis.aygul.model.Subscription;
import ru.kpfu.itis.aygul.repository.SubscriptionRepository;
import ru.kpfu.itis.aygul.service.SubscriptionServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by aygulmardanova on 12.05.16.
 */
public class SubscriptionServiceTest {

    private static Subscription subscription;

    private static SubscriptionServiceImpl subscriptionService;

    private static List<Subscription> subscriptions;

    private static final double eps = 1e-8;
    private static final int incor_id = 100;
    private static final int incor_price = 1000;
    private static final int incor_validity = 1;

    @BeforeClass
    public static void beforeMethod() {
        subscription = new Subscription();
        subscription.setPrice(4500);
        subscription.setValidity(6);

        subscriptions = new ArrayList<>();

        subscriptionService = new SubscriptionServiceImpl();
        subscriptions.add(subscription);

        SubscriptionRepository subscriptionRepository = mock(SubscriptionRepository.class);
        when(subscriptionRepository.findAll()).thenReturn(subscriptions);
        when(subscriptionRepository.findAllOrderByValidityAsc()).thenReturn(subscriptions);

        when(subscriptionRepository.findOneById(anyInt())).thenReturn(null);
        when(subscriptionRepository.findOneById(subscription.getId())).thenReturn(subscription);

        when(subscriptionRepository.findMinPrice()).thenReturn(subscription.getPrice());

        when(subscriptionRepository.findOneByPrice(anyInt())).thenReturn(null);
        when(subscriptionRepository.findOneByPrice(subscription.getPrice())).thenReturn(subscription);

        when(subscriptionRepository.findOneByValidity(anyInt())).thenReturn(null);
        when(subscriptionRepository.findOneByValidity(subscription.getValidity())).thenReturn(subscription);

        when(subscriptionRepository.findOneByValidityAndPrice(anyInt(), anyInt())).thenReturn(null);
        when(subscriptionRepository.findOneByValidityAndPrice(subscription.getValidity(), subscription.getPrice())).thenReturn(subscription);

        subscriptionService.setSubscriptionRepository(subscriptionRepository);
    }

    @Test
    public void getAllMethodShouldReturnCorrectListFromRepository() {
        Assert.assertEquals(subscriptions, subscriptionService.getAll());
    }

    @Test
    public void getOneByIdShouldReturnCorrectSubscription() {
        Assert.assertEquals(subscription, subscriptionService.getSubscriptionById(subscription.getId()));
    }

    @Test
    public void getOneByIdShouldReturnNullIfNonExistingId() {
        Assert.assertNull(subscriptionService.getSubscriptionById(incor_id));
    }

    @Test
    public void getOneByPriceShouldReturnCorrectSubscription() {
        Assert.assertEquals(subscription, subscriptionService.getSubscriptionByPrice(subscription.getPrice()));
    }

    @Test
    public void getOeByPriceShouldReturnNullIfNotExistsPrice() {
        Assert.assertNull(subscriptionService.getSubscriptionByPrice(incor_price));
    }

    @Test
    public void getOneByValidityShouldReturnCorrectSubscription() {
        Assert.assertEquals(subscription, subscriptionService.getSubscriptionByValidity(subscription.getValidity()));
    }

    @Test
    public void getOneByValidityShouldReturnNullIfNotExists() {
        Assert.assertNull(subscriptionService.getSubscriptionByValidity(incor_validity));
    }

    @Test
    public void findOneByValidityAndPriceShouldReturnCorrectSubscriptionIfExists() {
        Assert.assertEquals(subscription,
                subscriptionService.getSubscriptionByValidityAndPrice(subscription.getValidity(), subscription.getPrice()));
    }

    @Test
    public void findOneByValidityAndPriceShouldReturnNullIfNotExists() {
        Assert.assertNull(subscriptionService.getSubscriptionByValidityAndPrice(incor_validity, incor_price));
    }

    @Test
    public void getMinPriceShouldCallCorrectMethodInRepository() {
        Assert.assertEquals(subscription.getPrice(), subscriptionService.findMinPrice(), eps);
    }

    @Test
    public void ifValidityAlreadyExistsShouldReturnTrueIfSubscrExists() {
        Assert.assertTrue(subscriptionService.ifValidityAlreadyExists(subscription.getValidity()));
    }

    @Test
    public void ifValidityAlreadyExistsShouldReturnFalseIfSubscrNotExists() {
        Assert.assertFalse(subscriptionService.ifValidityAlreadyExists(incor_validity));
    }
}
