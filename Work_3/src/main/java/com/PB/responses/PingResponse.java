package com.PB.responses;

public class PingResponse {
    private String status;

    public PingResponse(String status) {
        this.status = status;
    }

    public PingResponse() {
        this.status = "UP";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
