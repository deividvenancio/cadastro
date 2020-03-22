package com.br.cadastro.interceptor;

import com.br.cadastro.exceptions.ValidationException;
import com.br.cadastro.model.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Object> handleConflict(
            ValidationException ex, WebRequest request) {

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMessage(ex.getMessage());
        jsonResponse.setStatusCode(404);

        return new ResponseEntity<>(jsonResponse, HttpStatus.NOT_FOUND);
    }
}
