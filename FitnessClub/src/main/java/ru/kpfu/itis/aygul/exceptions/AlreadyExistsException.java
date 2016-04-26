package ru.kpfu.itis.aygul.exceptions;

/**
 * Created by Айгуль on 23.04.2016.
 */
public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String message) {
        super(message);
    }

}
