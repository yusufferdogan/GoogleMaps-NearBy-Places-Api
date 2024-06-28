package com.yusuf.erdogan.mapsapi.exception;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class PlacesApiRuntimeException extends RuntimeException {

    private final Logger logger = LoggerFactory.getLogger(PlacesApiRuntimeException.class);
    private String[] params;
    private boolean printStackTraceLoggable = false;

    public PlacesApiRuntimeException(String message) {
        super(message);
    }

    public PlacesApiRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlacesApiRuntimeException(String message, boolean printStackTraceLoggable, String... params) {
        super(message);
        this.printStackTraceLoggable = printStackTraceLoggable;
        this.params = params;
    }
}
