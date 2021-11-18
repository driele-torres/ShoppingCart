package br.com.geofusion.ShoppingCart.advice;

import org.springframework.http.HttpStatus;
import br.com.geofusion.ShoppingCart.exception.ClientNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ClientNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(ClientNotFoundException ex) {
        return ex.getMessage();
    }
}