package com.sidecar.gateway.security.auth.validators;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public interface RequestValidator {

    boolean isValidate(HttpServletRequest request, HttpServletResponse response)  throws IOException;

    default void setResponse(HttpServletResponse response, String message, HttpStatus status) throws IOException {
        response.getWriter().write(message);
        response.setStatus(status.value());
    }
}
