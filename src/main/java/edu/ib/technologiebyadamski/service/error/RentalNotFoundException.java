package edu.ib.technologiebyadamski.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RentalNotFoundException extends RuntimeException{
    private RentalNotFoundException(String message){
        super(message);
    }
    public static ResponseStatusException create(long id){
        RentalNotFoundException exception = new RentalNotFoundException(String.format("Loan with id: %s was no found", id));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}
