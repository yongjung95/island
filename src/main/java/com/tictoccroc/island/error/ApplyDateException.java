package com.tictoccroc.island.error;

import com.tictoccroc.island.response.error.ErrorCode;

public class ApplyDateException extends CustomException  {

    public ApplyDateException() {
        super();
    }

    public ApplyDateException(String message) {
        super(message);
    }

    public ApplyDateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplyDateException(Throwable cause) {
        super(cause);
    }

    public ApplyDateException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
