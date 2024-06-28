package com.yusuf.erdogan.mapsapi.exception;

import com.yusuf.erdogan.mapsapi.dto.utilities.result.ErrorDataResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({ Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDataResult handleException(Exception e) {
        logger.error(getErrorOpeningTag(ErrorCode.GENERIC_ERROR.getCode()), e);
        return new ErrorDataResult(ErrorCode.GENERIC_ERROR.getMsg(), ErrorCode.GENERIC_ERROR.getCode());
    }

    @ExceptionHandler({ NoSuchElementException.class })
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDataResult handleNoElementException(NoSuchElementException e) {
        logger.error(getErrorOpeningTag(ErrorCode.NO_RECORDS_FOUND.getCode()), e);
        return new ErrorDataResult(e.getMessage(), ErrorCode.NO_RECORDS_FOUND.getCode());
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDataResult handleConstraintViolationExceptions(ConstraintViolationException ex) {
        Map<String, String> errors = getConstraintViolationMultiMessage(ex);
        logger.error(getErrorOpeningTag(ErrorCode.INVALID_PARAMETERS.getCode()), ex);
        return new ErrorDataResult(errors, ErrorCode.INVALID_PARAMETERS.getCode());
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDataResult onMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logger.error(getErrorOpeningTag(ErrorCode.INVALID_PARAMETERS.getCode()), ex);
        return new ErrorDataResult(getMultiValueMessageMap(ex), ErrorCode.INVALID_PARAMETERS.getCode());
    }

    @ExceptionHandler({ PlacesApiRuntimeException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDataResult handlePlacesApiRuntimeException(PlacesApiRuntimeException e) {
        logger.error(getErrorOpeningTag(ErrorCode.INVALID_PARAMETERS.getCode()), e);
        return new ErrorDataResult(e.getMessage(), ErrorCode.INVALID_PARAMETERS.getCode());
    }

    private String getErrorOpeningTag(int errorCode) {
        return "<ERROR_CODE_" + errorCode + ">";
    }

    private Map<String, String> getConstraintViolationMultiMessage(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach((error) -> {
            String fieldName = StreamSupport.stream(error.getPropertyPath().spliterator(), false)
                    .reduce((first, second) -> second).get().toString();
            String message = error.getMessage();
            errors.put(fieldName, message);
        });
        return errors;
    }

    private Map<String, String> getMultiValueMessageMap(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;
    }
}
