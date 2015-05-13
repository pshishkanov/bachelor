package org.pshishkanov.sherlock.controller.response;

/**
 * Created by pshishkanov on 22/04/15.
 */
public class Response {

    private String status;

    private String type;

    public Response() { }

    public Response(String status, String type) {
        this.status = status;
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
