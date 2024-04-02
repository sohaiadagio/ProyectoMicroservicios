package edu.tienda.core.controllers;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import edu.tienda.core.exceptions.BadRequestException;

@ControllerAdvice /*Como pueden verificar, la clase está decorada con la anotación @ControllerAdvice, lo
que indica que esta clase estará destinada a manejar las respuestas de los servicios REST para las
excepciones requeridas. */

public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

   /*El método “handleBadRequest” manejara la respuesta de los servicios REST cada vez
que se lance la excepcion “BadRequestException”. Esto se logra anotando el método con
@ExceptionHandler(BadRequestException). */
 @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequest(
        RuntimeException ex, WebRequest request)
    {
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("Error", HttpStatus.BAD_REQUEST.toString());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    } 
    
}
