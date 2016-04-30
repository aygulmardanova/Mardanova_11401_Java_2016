package ru.kpfu.itis.aygul.exceptions;

/**
 * Created by Айгуль on 23.04.2016.
 */
public class UserNotFound extends RuntimeException {

    public UserNotFound(String message) {
        super(message);
    }
}
