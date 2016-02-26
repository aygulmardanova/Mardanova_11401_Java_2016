package Task005.Tests;

import Task005.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.Socket;

import static org.mockito.Mockito.mock;

/**
 * Created by Айгуль on 26.02.2016.
 */
public class StatusTest {

    private static Socket socket1;
    private static Socket socket2;
    private Status status;

    @BeforeClass
    public static void beforeClassMethod() {
        socket1 = mock(Socket.class);
        socket2 = mock(Socket.class);
    }
    @Before
    public void beforeMethod() {
        status = new Status(socket1, socket2);
    }

    @Test
    public void constructorShouldSaveFirstParameterAsClient1() {
        Assert.assertEquals(socket1, status.getClient1());
    }

    @Test
    public void constructorShouldSaveSecondParameterAsClient2() {
        Assert.assertEquals(socket2, status.getClient2());
    }

    @Test
    public void setMessageShouldChangeValueOfMessageField() {
        status.setMessage(2);
        Assert.assertEquals(2, status.getMessage());
    }

    @Test
    public void isFlagMethodShouldReturnFalseIfFlagWasNotChanged() {
        Assert.assertFalse(status.isFlag());
    }

    @Test
    public void setFlagMethodShouldChangeTheValueOfFlagField() {
        status.setFlag(true);
        Assert.assertTrue(status.isFlag());
    }

    @Test
    public void threadWasStartedIfNewStatusWasCreated() {
        Thread th = status.getThread();
        Assert.assertTrue(th.isAlive());
    }

}
