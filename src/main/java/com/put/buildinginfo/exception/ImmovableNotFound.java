package com.put.buildinginfo.exception;

public class ImmovableNotFound extends RuntimeException {

    public ImmovableNotFound(String message) {
        super(message);
    }

    public ImmovableNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public ImmovableNotFound(Throwable cause) {
        super(cause);
    }
}
