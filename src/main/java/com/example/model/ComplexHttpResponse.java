package com.example.model;

public class ComplexHttpResponse<T> {
    private T response;
    private long code;

    public static <T> ComplexHttpResponse<T> of(T response, long code) {
        return new ComplexHttpResponse<>(response, code);
    }

    private ComplexHttpResponse(T response, long code) {
        this.response = response;
        this.code = code;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }
}
