package com.elis.common.exception;

import feign.FeignException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleMethodArgumentNotValid( MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = ex.getBindingResult().getFieldErrors()
                .stream().map(error -> error.getField()+": "+ error.getDefaultMessage())
                .collect(Collectors.toList());
        ExceptionResponse exception = new ExceptionResponse(LocalDateTime.now(), "validation failed", details);
        return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public final ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex, WebRequest request){
        ExceptionResponse exception = new ExceptionResponse(LocalDateTime.now(), ex.getReason(), Arrays.asList(request.getDescription(false)));
        return new ResponseEntity(exception, ex.getStatus());
    }

    @ExceptionHandler(FeignException.class)
    public final ResponseEntity<Object> handleAllException(FeignException ex, WebRequest request){
        ExceptionResponse exception = new ExceptionResponse(LocalDateTime.now(), "Feign Error ",Arrays.asList(request.getDescription(false)));
        return new ResponseEntity(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
