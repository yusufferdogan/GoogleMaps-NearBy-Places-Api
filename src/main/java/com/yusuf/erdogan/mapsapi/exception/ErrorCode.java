package com.yusuf.erdogan.mapsapi.exception;

public enum ErrorCode {
    GENERIC_ERROR(1000, "An unexpected error occurred"),
    NO_RECORDS_FOUND(1001, "No records found"),
    SERVICE_UNAVAILABLE(1002, "Service is unavailable"),
    INVALID_PARAMETERS(1003, "Invalid parameters");

    private final int code;
    private final String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
