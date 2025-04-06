package com.sidecar.gateway.security.auth.validators;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("API")
public class ClientTokenValidator implements RequestValidator{

    private final String authKey= "token-0001";

    @Override
    public boolean isValidate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getHeader("x-token");
        if(token==null || (token!=null && !token.equals(authKey))){
            RequestValidator.super.setResponse(response, "token missing or wrong", HttpStatus.BAD_REQUEST);
            return false;
        }
        return true;
    }
}
