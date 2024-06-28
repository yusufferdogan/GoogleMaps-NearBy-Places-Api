package com.yusuf.erdogan.mapsapi.dto.utilities.result;

public class SuccessDataResult<T> extends DataResult<T> {

    public SuccessDataResult(T data, String message) {
        super(data, true ,message);
    }

}
