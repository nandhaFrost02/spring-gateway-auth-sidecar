package com.sidecar.gateway.security.auth;

import com.sidecar.gateway.security.auth.validators.BasicAuthValidator;
import com.sidecar.gateway.security.auth.validators.ClientTokenValidator;
import com.sidecar.gateway.security.auth.validators.RequestValidator;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AppAuthFactory {

    private final Map<String, RequestValidator> supportedAuthTypes;

    public AppAuthFactory(Map<String, RequestValidator> supportedAuthTypes){
        this.supportedAuthTypes = supportedAuthTypes;
    }

    //Hard coded core java - factory method
    public static RequestValidator authFactory(String authSegmentReference){
        return switch (authSegmentReference){
            case "api" -> new ClientTokenValidator();
            default -> new BasicAuthValidator();
        };
    }

    public RequestValidator authFactory2(String authSegmentReference){
        return this.supportedAuthTypes.getOrDefault(authSegmentReference.toUpperCase(),
                this.supportedAuthTypes.get("BASIC"));
    }

}
