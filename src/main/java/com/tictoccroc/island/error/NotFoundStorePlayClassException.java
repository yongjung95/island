package com.tictoccroc.island.error;

import com.tictoccroc.island.response.error.ErrorCode;

public class NotFoundStorePlayClassException extends CustomException {

    public NotFoundStorePlayClassException() {
        super();
    }

    public NotFoundStorePlayClassException(String message) {
        super(message);
    }

    public NotFoundStorePlayClassException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundStorePlayClassException(Throwable cause) {
        super(cause);
    }

    public NotFoundStorePlayClassException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
