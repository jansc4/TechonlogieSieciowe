package edu.ib.technologiebyadamski.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends RuntimeException{
    private UserNotFoundException(String message){
        super(message);
    }
    public static ResponseStatusException create(long id){
        UserNotFoundException exception = new UserNotFoundException(String.format("User with id: %s was no found", id));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
    public static ResponseStatusException create(){
        UserNotFoundException exception = new UserNotFoundException(String.format("User not found"));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}
