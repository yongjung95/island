package com.tictoccroc.island.error;

import com.tictoccroc.island.response.error.ErrorCode;

public class ApplyMaxCntException extends CustomException {

    public ApplyMaxCntException() {
        super();
    }

    public ApplyMaxCntException(String message) {
        super(message);
    }

    public ApplyMaxCntException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplyMaxCntException(Throwable cause) {
        super(cause);
    }

    public ApplyMaxCntException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
