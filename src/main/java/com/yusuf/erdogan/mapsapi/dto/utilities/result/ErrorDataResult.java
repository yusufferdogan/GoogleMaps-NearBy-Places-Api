package com.yusuf.erdogan.mapsapi.dto.utilities.result;

import lombok.Data;

import java.util.Map;

@Data
public class ErrorDataResult<T> extends DataResult<T> {
    private int messageCode;
    private Map<String, String> multiValueMessageMap;

    public ErrorDataResult(String message, int messageCode) {
        super(null,false,message);
        this.messageCode = messageCode;
    }

    public ErrorDataResult(Map<String, String> multiValueMap,int messageCode) {
        super(null, false);
        this.multiValueMessageMap = multiValueMap;
        this.messageCode = messageCode;
    }

}
