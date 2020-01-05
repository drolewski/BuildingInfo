package com.put.buildinginfo.exception;

/**
 * The type Immovable error response.
 */
public class ImmovableErrorResponse {

    private int status;
    private String message;
    private long timeStamp;

    /**
     * Instantiates a new Immovable error response.
     */
    public ImmovableErrorResponse() {
    }

    /**
     * Instantiates a new Immovable error response.
     *
     * @param status    the status
     * @param message   the message
     * @param timeStamp the time stamp
     */
    public ImmovableErrorResponse(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets time stamp.
     *
     * @return the time stamp
     */
    public long getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets time stamp.
     *
     * @param timeStamp the time stamp
     */
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
