package com.luisalves.userservice.exceptions;

import lombok.Getter;

public class GetResourceFailedException extends RuntimeException {

    @Getter
    private int code;

    public GetResourceFailedException(String message) {
        super(message);
    }

    public GetResourceFailedException(int code, String message) {
        super(message);
        this.code = code;
    }

}
