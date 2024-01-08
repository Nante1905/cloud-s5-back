package com.cloud.voiture.types.response;

public class Response {
    Object data;
    boolean OK;
    String message = "";
    String err = "";

    public Response(Object data, String message) {
        setOK(true);
        setData(data);
        setMessage(message);
    }

    public Response(String err) {
        setOK(false);
        setErr(err);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isOK() {
        return OK;
    }

    public void setOK(boolean oK) {
        OK = oK;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

}
