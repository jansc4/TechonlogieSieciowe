package edu.ib.technologiebyadamski.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistsException extends RuntimeException {
    private UserAlreadyExistsException(String message){
        super(message);
    }
    public static ResponseStatusException create(String username){
        UserAlreadyExistsException exception = new UserAlreadyExistsException(String.format("User with usermane: %s already exsists", username));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}
