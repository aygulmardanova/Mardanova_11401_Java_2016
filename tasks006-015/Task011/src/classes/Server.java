package classes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ekaterina on 18.11.2015.
 */

public class Server {
    final int PORT = 3456;

    public ArrayList<Connection> getConnections() {
        return connections;
    }

    ArrayList<Connection> connections;
    Status status;

    ServerSocket s = new ServerSocket(PORT);

    public Server() throws IOException {
        this.connections = new ArrayList<>();
    }

    public ArrayList<Integer> generate() {
        ArrayList<Integer> arrayList = new ArrayList();
        Random r = new Random();
        int a0;
        int a1;
        int a2;
        int a3;
        a0 = r.nextInt(9);
        a1 = r.nextInt(9);
        while (a0 == a1) {
            a1 = r.nextInt(9);
        }
        a2 = r.nextInt(9);
        while (a2 == a0 || a2 == a1) {
            a2 = r.nextInt(9);
        }
        a3 = r.nextInt(9);
        while (a3 == a0 || a3 == a1 || a3 == a2) {
            a3 = r.nextInt(9);
        }
        arrayList.add(a0);
        arrayList.add(a1);
        arrayList.add(a2);
        arrayList.add(a3);
        return arrayList;
    }

    public void go(ArrayList<Integer> key) throws IOException {

        Socket client1 = s.accept();
        Socket client2 = s.accept();
        System.out.println("Clients connected");
        status = new Status(client1, client2);
        Connection conn1 = new Connection(this, client1, status, key);
        conn1.setNumber(1);
        connections.add(conn1);
        Connection conn2 = new Connection(this, client2, status, key);
        conn2.setNumber(2);
        connections.add(conn2);

    }

    public static void main(String[] args) throws IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-config.xml");
        Server server = (Server) ac.getBean("server");
        for (int i = 0; i < 3; i++) {
            ArrayList<Integer> key = server.generate();
            server.go(key);
        }
    }

}