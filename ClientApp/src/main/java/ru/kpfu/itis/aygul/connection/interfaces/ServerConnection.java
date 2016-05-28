package ru.kpfu.itis.aygul.connection.interfaces;

import ru.kpfu.itis.aygul.model.UserClient;

import java.io.IOException;

/**
 * Created by aygulmardanova on 27.05.16.
 */
public interface ServerConnection {

    UserClient getUser(String login, String password) throws IOException;
}
