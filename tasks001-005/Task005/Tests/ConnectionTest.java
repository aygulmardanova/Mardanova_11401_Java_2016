package Task005.Tests;

import Task005.Connection;
import Task005.Server;
import Task005.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

/**
 * Created by Айгуль on 26.02.2016.
 */
public class ConnectionTest {

    private static Server server;
    private static Socket socket;
    private static Status status;
    private static ArrayList<Integer> key;
    private static ArrayList<Integer> values;
    private static Connection connection;

    @BeforeClass
    public static void beforeClassMethod() throws IOException {
        server = mock(Server.class);

        socket = mock(Socket.class);
        doCallRealMethod().when(socket).getInputStream();
        doCallRealMethod().when(socket).getOutputStream();
//        when(socket.getOutputStream()).thenReturn(socket.getOutputStream());

        status = mock(Status.class);

        key = mock(ArrayList.class);
        when(key.size()).thenReturn(4);
        when(key.get(0)).thenReturn(1);
        when(key.get(1)).thenReturn(2);
        when(key.get(2)).thenReturn(3);
        when(key.get(3)).thenReturn(4);

        connection = new Connection(server, socket, status, key);

        values = connection.parseIntoList(3452);
    }

    @Test
    public void constructorShouldSaveFirstParameterAsServer() {
        Assert.assertEquals(server, connection.getServer());
    }

    @Test
    public void constructorShouldSaveSecondParameterAsSocket1() {
        Assert.assertEquals(socket, connection.getSocket1());
    }

    @Test
     public void constructorShouldSaveThirdParameterAsStatus() {
        Assert.assertEquals(status, connection.getStatus());
    }

    @Test
     public void constructorShouldSaveForthParameterAsKey() {
        Assert.assertEquals(key, connection.getKey());
    }

    @Test
    public void setNumberShouldFixTheEnteredNumberCorrectly() {
        Connection myC = new Connection(server, socket, status, key);
        myC.setNumber(3);
        Assert.assertEquals(3, myC.getNumber());
    }

    @Test
    public void parseIntoListShouldReturnListWithCorrectValues() {
        Assert.assertTrue(2 == values.get(0));
        Assert.assertTrue(5 == values.get(1));
        Assert.assertTrue(4 == values.get(2));
        Assert.assertTrue(3 == values.get(3));
    }

    @Test
    public void getBullsShouldReturnCorrectCountOfBullsInNumberIfNonZero() {
        ArrayList<Integer> key = new ArrayList<>();
        key.add(1);
        key.add(4);
        key.add(6);
        key.add(2);
        Assert.assertEquals(2, connection.getBulls(values, key));
    }

    @Test
    public void getBullsShouldReturnCorrectCountOfBullsInNumberIfZero() {
        ArrayList<Integer> key = new ArrayList<>();
        key.add(4);
        key.add(3);
        key.add(2);
        key.add(5);
        Assert.assertEquals(0, connection.getBulls(values, key));
    }

    @Test
    public void getCowsShouldReturnCorrectCountOfCowsInNumberIfNonZero() {
        ArrayList<Integer> key = new ArrayList<>();
        key.add(1);
        key.add(4);
        key.add(2);
        key.add(3);
        Assert.assertEquals(2, connection.getCows(values, key));
    }

    @Test
    public void getCowsShouldReturnCorrectCountOfCowsInNumberIfZero() {
        ArrayList<Integer> key = new ArrayList<>();
        key.add(1);
        key.add(4);
        key.add(6);
        key.add(2);
        Assert.assertEquals(0, connection.getCows(values, key));
    }
}
