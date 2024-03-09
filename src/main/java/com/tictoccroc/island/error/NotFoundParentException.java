package com.tictoccroc.island.error;

import com.tictoccroc.island.response.error.ErrorCode;

public class NotFoundParentException extends CustomException {

    public NotFoundParentException() {
        super();
    }

    public NotFoundParentException(String message) {
        super(message);
    }

    public NotFoundParentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundParentException(Throwable cause) {
        super(cause);
    }

    public NotFoundParentException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}