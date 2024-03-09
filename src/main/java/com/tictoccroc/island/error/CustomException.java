package com.tictoccroc.island.error;

import com.tictoccroc.island.response.error.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private ErrorCode errorCode;

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    public CustomException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
