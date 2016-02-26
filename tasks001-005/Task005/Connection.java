package Task005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Ekaterina on 04.12.2015.
 */
public class Connection implements Runnable {
    Socket socket1;
    Status status;
    int Number;

    public Thread getThread() {
        return thread;
    }

    public Server getServer() {
        return server;
    }

    public ArrayList<Integer> getKey() {
        return key;
    }

    public Status getStatus() {
        return status;
    }

    public Socket getSocket1() {
        return socket1;
    }

    Thread thread;
    Server server;
    ArrayList<Integer> key;

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public Connection(Server server, Socket socket1, Status status, ArrayList<Integer> key) {
        this.server = server;
        this.socket1 = socket1;
        this.status = status;
        this.key = key;

        thread = new Thread(this);
        thread.start();
        System.out.println("conn started");
    }

    public int getBulls(ArrayList<Integer> values, ArrayList<Integer> key) {
        int bulls = 0;
        int a0 = key.get(0);
        int a1 = key.get(1);
        int a2 = key.get(2);
        int a3 = key.get(3);
        if (values.get(0) == a3) bulls++;
        if (values.get(1) == a2) bulls++;
        if (values.get(2) == a1) bulls++;
        if (values.get(3) == a0) bulls++;
        return bulls;
    }
    public int getCows(ArrayList<Integer> values, ArrayList<Integer> key) {
        int cows = 0;
        int a0 = key.get(0);
        int a1 = key.get(1);
        int a2 = key.get(2);
        int a3 = key.get(3);
        if (values.contains(a3) && values.get(0) != a3) cows++;
        if (values.contains(a2) && values.get(1) != a2) cows++;
        if (values.contains(a1) && values.get(2) != a1) cows++;
        if (values.contains(a0) && values.get(3) != a0) cows++;
        return cows;
    }

    public ArrayList<Integer> parseIntoList(int value) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            arrayList.add(value % 10);
            value = value / 10;
        }
        return arrayList;
    }

    @Override
    public void run() {

            try {

                PrintWriter bw = new PrintWriter(socket1.getOutputStream(), true);
                BufferedReader br = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
                int bulls;
                int cows;

                while (true) {
                    String line = br.readLine();
                    int c = Integer.parseInt(line);

                    ArrayList<Integer> arrayList = parseIntoList(c);

                    bulls = getBulls(arrayList, key);
                    cows = getCows(arrayList, key);

                    if (bulls == 4) {
                        bw.println("You win!");
                        status.setMessage(this.getNumber());
                        break;
                    } else {
                        bw.println(bulls + " : bulls   " + cows + " : cows");
                        status.setMessage(0);
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

    }

}
