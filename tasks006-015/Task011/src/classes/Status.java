package classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Ekaterina on 24.12.2015.
 */
public class Status implements Runnable {

    public Socket getClient1() {
        return client1;
    }

    public Socket getClient2() {
        return client2;
    }

    Socket client1;
    Socket client2;

    public Thread getThread() {
        return thread;
    }

    Thread thread;
    int message;
    boolean flag = false;

    public Status(Socket client1, Socket client2) {
        this.client1 = client1;
        this.client2 = client2;
        thread = new Thread(this);
        thread.start();
        System.out.println("Status started");
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {

                PrintWriter bw1 = new PrintWriter(client1.getOutputStream(), true);
                PrintWriter bw2 = new PrintWriter(client2.getOutputStream(), true);
                while (true) {
                    this.wait(100);
                    if (this.getMessage() == 1) {
                        bw2.println("You lose!");
                        break;
                    } else if (this.getMessage() == 2) {
                        bw1.println("You lose!");
                        break;
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
