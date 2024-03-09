package com.tictoccroc.island.response.error;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    // COMMON
    PARAMETER_IS_EMPTY(400, "C001", "파라미터가 비었습니다."),

    // APPLY_PLAY_CLASS
    NOT_FOUND_APPLY_CLASS(403, "A001", "신청한 수업을 찾을 수 없습니다."),
    PLAY_CLASS_IS_FULL(400, "A002", "최대 정원 수 초과입니다."),
    APPLY_DATE_ERROR(400, "A003", "현재 날짜의 다음날부터 14일까지만 예약할 수 있습니다."),
    DUPLICATE_APPLY_PLAY_CLASS(409, "A004", "동일인이 동일매장, 동일수업에 중복하여 예약할 수 없습니다."),

    // STORE_PLAY_CLASS
    NOT_FOUND_STORE_PLAY_CLASS(403, "S001", "해당 매장의 수업을 찾을 수 없습니다"),

    // PARENT
    NOT_FOUND_PARENT(403, "P001", "해당 부모님을 찾을 수 없습니다.");

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }

}
