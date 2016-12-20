package com.hoaven.Exception;

/**
 * Created by hehuanchun on 2016/12/20 0020.
 */
public class CommonException extends RuntimeException {
    public CommonException(String message) {
        super(message);
    }

    public CommonException(Throwable cause) {
        super(cause);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }
}
