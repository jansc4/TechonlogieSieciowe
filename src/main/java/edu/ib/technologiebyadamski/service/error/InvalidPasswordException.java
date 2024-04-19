package edu.ib.technologiebyadamski.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidPasswordException  extends RuntimeException{
    private InvalidPasswordException(String message){
        super(message);
    }
    public static ResponseStatusException create(){
        InvalidPasswordException exception = new InvalidPasswordException(String.format("Wrong password"));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}
