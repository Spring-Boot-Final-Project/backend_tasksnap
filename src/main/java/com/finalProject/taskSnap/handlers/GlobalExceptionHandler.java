package com.finalProject.taskSnap.handlers;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.management.InstanceAlreadyExistsException;
import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception) {
        ProblemDetail errorDetail = null;
        exception.printStackTrace();

        if (exception instanceof BadCredentialsException) {
            errorDetail = createProblemDetail(HttpStatus.UNAUTHORIZED, exception.getMessage(), "Incorrect username or password");
        } else if (exception instanceof AccountStatusException) {
            errorDetail = createProblemDetail(HttpStatus.FORBIDDEN, exception.getMessage(), "The account is locked");
        } else if (exception instanceof AccessDeniedException || exception instanceof InsufficientAuthenticationException) {
            errorDetail = createProblemDetail(HttpStatus.FORBIDDEN, exception.getMessage(), "You are not authorized to access this resource");
        }  else if (exception instanceof SignatureException) {
            errorDetail = createProblemDetail(HttpStatus.FORBIDDEN, exception.getMessage(), "The JWT signature is invalid");
        } else if (exception instanceof ExpiredJwtException) {
            errorDetail = createProblemDetail(HttpStatus.FORBIDDEN, exception.getMessage(), "The JWT token has expired");
        } else if (exception instanceof InstanceAlreadyExistsException) {
            errorDetail = createProblemDetail(HttpStatus.CONFLICT, exception.getMessage(), "The email is already registered");
        } else {
            errorDetail = createProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), "Internal server error.");
        }

        return errorDetail;
    }

    private ProblemDetail createProblemDetail(HttpStatus status, String message, String description) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, message);
        problemDetail.setType(URI.create("error"));
        problemDetail.setTitle(status.getReasonPhrase());
        problemDetail.setProperty("description", description);
        return problemDetail;
    }
}
