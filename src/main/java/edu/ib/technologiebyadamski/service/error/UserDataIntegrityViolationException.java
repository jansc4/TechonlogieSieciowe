package edu.ib.technologiebyadamski.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserDataIntegrityViolationException extends RuntimeException{
    public UserDataIntegrityViolationException(String message){
        super(message);
    }

    public static ResponseStatusException create(long id){
        UserDataIntegrityViolationException exception = new UserDataIntegrityViolationException(String.format("User with this id: %s can not be deleted because it's used in database", id));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}
