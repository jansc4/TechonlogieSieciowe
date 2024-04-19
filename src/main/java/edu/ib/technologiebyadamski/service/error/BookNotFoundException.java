package edu.ib.technologiebyadamski.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookNotFoundException extends RuntimeException{
    private BookNotFoundException(String message){
        super(message);
    }
    public static ResponseStatusException create(long id){
        BookNotFoundException exception = new BookNotFoundException(String.format("Book with id: %s was no found", id));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
    public static ResponseStatusException create(){
        BookNotFoundException exception = new BookNotFoundException(String.format("Books not found"));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}
