package com.sidecar.gateway.security;

import com.sidecar.gateway.security.auth.AppAuthFactory;
import com.sidecar.gateway.security.auth.validators.RequestValidator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthInterceptor extends OncePerRequestFilter {

    @Autowired
    AppAuthFactory authFactory;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authRouterSegment = Utilities.getAuthSegment(request.getRequestURI());
        RequestValidator validator = authFactory.authFactory2(authRouterSegment);
        if(validator.isValidate(request, response)) filterChain.doFilter(request, response);
        return ;
    }
}
