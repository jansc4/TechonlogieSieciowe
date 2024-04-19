package edu.ib.technologiebyadamski.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookAlreadyExsitsException extends RuntimeException{
    private BookAlreadyExsitsException(String message){
        super(message);
    }
    public static ResponseStatusException create(String title){
        BookAlreadyExsitsException exception = new BookAlreadyExsitsException(String.format("Book with this title: %s already exxsists", title));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}
