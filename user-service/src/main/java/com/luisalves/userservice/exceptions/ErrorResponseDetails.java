package com.luisalves.userservice.exceptions;

public record ErrorResponseDetails(int code, String message, String uri) { }
