package com.put.buildinginfo.exception;

/**
 * The type Immovable not found.
 */
public class ImmovableNotFound extends RuntimeException {

    /**
     * Instantiates a new Immovable not found.
     *
     * @param message the message
     */
    public ImmovableNotFound(String message) {
        super(message);
    }

    /**
     * Instantiates a new Immovable not found.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ImmovableNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Immovable not found.
     *
     * @param cause the cause
     */
    public ImmovableNotFound(Throwable cause) {
        super(cause);
    }
}
