package com.put.buildinginfo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type Immovable rest exception handler.
 */
@ControllerAdvice
public class ImmovableRestExceptionHandler {

    /**
     * Handler exception response entity.
     *
     * @param exc the exc
     * @return the response entity
     */
    @ExceptionHandler
    public ResponseEntity<ImmovableErrorResponse> handlerException(ImmovableNotFound exc){
        ImmovableErrorResponse errorResponse = new ImmovableErrorResponse();

        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle exception response entity.
     *
     * @param exc the exc
     * @return the response entity
     */
    @ExceptionHandler
    public ResponseEntity<ImmovableErrorResponse> handleException(Exception exc){
        ImmovableErrorResponse errorResponse = new ImmovableErrorResponse();

        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
