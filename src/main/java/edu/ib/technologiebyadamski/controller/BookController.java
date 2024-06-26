package edu.ib.technologiebyadamski.controller;

import edu.ib.technologiebyadamski.controller.dto.CreateBookDto;
import edu.ib.technologiebyadamski.controller.dto.CreateBookResponseDto;
import edu.ib.technologiebyadamski.controller.dto.GetBookDto;

import edu.ib.technologiebyadamski.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'READER')")
    public List<GetBookDto> getAllBooks(){
        return bookService.getAll();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'READER')")
    public GetBookDto getOne(@PathVariable long id){
        return bookService.getOne(id);
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateBookResponseDto> create(@RequestBody CreateBookDto book){
        var newBook = bookService.create(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable long id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
