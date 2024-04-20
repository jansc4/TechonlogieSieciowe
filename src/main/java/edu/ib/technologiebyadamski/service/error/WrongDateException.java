package edu.ib.technologiebyadamski.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

public class WrongDateException extends RuntimeException{
    private WrongDateException(String message){
        super(message);
    }
    public static ResponseStatusException create(Date date){
        WrongDateException exception = new WrongDateException(String.format("Wrong date: %s ", date));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}
