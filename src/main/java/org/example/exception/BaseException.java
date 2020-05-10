package org.example.exception;

/**
 * @author AdamSun
 * @date 2020/5/10 9:13
 */
public class BaseException extends RuntimeException{

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
