package Task005.Tests;

import Task005.Clientgui;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static Task005.Clientgui.isUnique;

/**
 * Created by Ekaterina on 25.02.2016.
 */
public class ClientTest {

    private static Clientgui cl;

    @BeforeClass
    public static void beforeMethod() throws IOException {
        cl = new Clientgui("192.168.1.4");
        //cl = mock(Clientgui.class);
        //when(cl.getHost()).thenReturn("host");
    }

    @Test
    public void isUniqueShouldReturnTrueIfUniqueString(){
       Assert.assertTrue(isUnique("abcd"));
    }

    @Test
    public void isUniqueShouldReturnFalseIfNotUniqueString(){
        Assert.assertFalse(isUnique("abca"));
    }

    @Test
    public void constructorShouldSaveParameterInHostField() {
        Assert.assertEquals("192.168.1.4", cl.getHost());
    }


}
