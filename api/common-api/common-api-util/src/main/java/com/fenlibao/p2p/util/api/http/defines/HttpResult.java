package com.fenlibao.p2p.util.api.http.defines;

/**
 * Created by lenovo on 2015/9/4.
 */
public class  HttpResult {

    private int statusCode;
    private byte[] bytes;
    private String message;

    public HttpResult() {
    }

    public HttpResult(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        String byteStr = "";
        try {
            byteStr = new String(bytes, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "HttpResult{" +
                "statusCode=" + statusCode +
                ", bytes=" + byteStr +
                ", message='" + message + '\'' +
                '}';
    }
}
