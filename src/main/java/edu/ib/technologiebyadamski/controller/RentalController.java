package edu.ib.technologiebyadamski.controller;


import edu.ib.technologiebyadamski.controller.dto.BookReturnDto;
import edu.ib.technologiebyadamski.controller.dto.CreateRentalDto;
import edu.ib.technologiebyadamski.controller.dto.CreateRentalResponseDto;
import edu.ib.technologiebyadamski.controller.dto.GetRentalDto;
import edu.ib.technologiebyadamski.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<GetRentalDto> getAllRentals() {
        return rentalService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public GetRentalDto getOne(@PathVariable long id) {
        return rentalService.getOne(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateRentalResponseDto> create(@RequestBody CreateRentalDto rental) {
        var newRental = rentalService.create(rental);
        return new ResponseEntity<>(newRental, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        rentalService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/return")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GetRentalDto> returnBook(@RequestBody BookReturnDto rental) {
        var newRental = rentalService.bookReturn(rental);
        return new ResponseEntity<>(newRental, HttpStatus.OK);
    }

}
