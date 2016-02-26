package Task005.Tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static Task005.Server.generate;


/**
 * Created by Ekaterina on 25.02.2016.
 */
public class ServerTest {

    private static ArrayList<Integer> key;

    @BeforeClass
    public static void beforeClassMethod() {
        key = generate();
    }

    @Test
    public void generateMethodShouldListWithFourElements() {
        Assert.assertEquals(4, key.size());
    }

    @Test
    public void generateMethodShouldReturnListWithFourDifferentValues() {
        boolean flag = true;
        if ((key.get(0) == key.get(1)) || (key.get(0) == key.get(2)) || key.get(0) == key.get(3)
                || (key.get(1) == key.get(2)) || (key.get(1) == key.get(3)) || (key.get(2) ==key.get(3))) {
            flag = false;
        }
        Assert.assertTrue(flag);
    }

}
