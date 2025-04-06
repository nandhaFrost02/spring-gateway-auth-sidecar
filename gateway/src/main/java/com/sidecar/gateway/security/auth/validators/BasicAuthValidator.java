package com.sidecar.gateway.security.auth.validators;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;

@Component("BASIC")
public class BasicAuthValidator implements RequestValidator {

    private final String basicAuthKey = "user:password";
    @Override
    public boolean isValidate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getHeader("Authorization");
        if(token==null || (token!=null && !new String(Base64.getDecoder().decode(token.split(" ")[1])).equals(basicAuthKey))){
            RequestValidator.super.setResponse(response, "Basic auth missing or wrong", HttpStatus.BAD_REQUEST);
            return false;
        }
        return true;
    }
}
