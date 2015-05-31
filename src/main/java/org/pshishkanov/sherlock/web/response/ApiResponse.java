package org.pshishkanov.sherlock.web.response;

/**
 * Created by pshishkanov on 22/04/15.
 */
public class ApiResponse {

    private String message;

    public ApiResponse() { }

    public ApiResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
