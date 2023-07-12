package com.mercury.apigateway;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DataResponse<T> extends Response{
    private T data;

    public DataResponse(boolean success, String message, T data) {
        super(success, message);
        this.data = data;
    }

    public DataResponse(String message, T data) {
        super(message);
        this.data = data;
    }

    public DataResponse(boolean success, T data) {
        super(success);
        this.data = data;
    }

    public DataResponse(T data) {
        super(true, "");
        this.data = data;
    }
    public DataResponse(boolean success, String message) {
        super(success, message);
        this.data = null;
    }



}
