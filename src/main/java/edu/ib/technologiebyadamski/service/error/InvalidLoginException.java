package edu.ib.technologiebyadamski.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidLoginException extends  RuntimeException{
    private InvalidLoginException(String message){
        super(message);
    }
    public static ResponseStatusException create(){
        InvalidLoginException exception = new InvalidLoginException(String.format("Wrong login"));
        return new ResponseStatusException(HttpStatus.UNAUTHORIZED, exception.getMessage(), exception);
    }
}
