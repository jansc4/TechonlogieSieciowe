package edu.ib.technologiebyadamski.controller;


import edu.ib.technologiebyadamski.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    private final RentalService rentalService;
    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    public RentalController(RentalRepository rentalRepository){
        this.rentalRepository = rentalRepository;
    }
    @PostMapping("/add")
    @ResponseStatus(code= HttpStatus.CREATED)
    public @ResponseBody Rental addRental(@RequestBody Rental rental){
        return rentalRepository.save(rental);
    }
    @GetMapping("/getAll")
    public @ResponseBody Iterable<Rental> getAllRental(){
        return rentalRepository.findAll();
    }
}
