package com.tictoccroc.island.error;

import com.tictoccroc.island.response.error.ErrorCode;

public class DuplicateApplyPlayClassException extends CustomException {

    public DuplicateApplyPlayClassException() {
        super();
    }

    public DuplicateApplyPlayClassException(String message) {
        super(message);
    }

    public DuplicateApplyPlayClassException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateApplyPlayClassException(Throwable cause) {
        super(cause);
    }

    public DuplicateApplyPlayClassException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}