package edu.ib.technologiebyadamski.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookDataIntegrityViolationException extends RuntimeException{
    public BookDataIntegrityViolationException(String message){
        super(message);
    }

    public static ResponseStatusException create(long id){
        BookDataIntegrityViolationException exception = new BookDataIntegrityViolationException(String.format("Book with this id: %s can not be deleted because it's used in database", id));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}
