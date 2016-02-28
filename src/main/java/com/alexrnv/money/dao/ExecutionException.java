package com.alexrnv.money.dao;

/**
 * @author Alex
 */
public class ExecutionException extends Exception {
    public ExecutionException(String message) {
        super(message);
    }

    public ExecutionException(Throwable cause) {
        super(cause);
    }
}
