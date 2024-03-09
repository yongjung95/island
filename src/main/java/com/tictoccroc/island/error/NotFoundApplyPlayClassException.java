package com.tictoccroc.island.error;

import com.tictoccroc.island.response.error.ErrorCode;

public class NotFoundApplyPlayClassException extends CustomException {

    public NotFoundApplyPlayClassException() {
        super();
    }

    public NotFoundApplyPlayClassException(String message) {
        super(message);
    }

    public NotFoundApplyPlayClassException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundApplyPlayClassException(Throwable cause) {
        super(cause);
    }

    public NotFoundApplyPlayClassException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
