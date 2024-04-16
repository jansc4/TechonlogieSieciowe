package edu.ib.technologiebyadamski.service;

import edu.ib.technologiebyadamski.controller.dto.CreateRentalDto;
import edu.ib.technologiebyadamski.controller.dto.GetRentalDto;
import edu.ib.technologiebyadamski.infrastructure.entity.RentalEntity;
import edu.ib.technologiebyadamski.infrastructure.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public RentalService(RentalRepository rentalRepository, BookService bookService, UserService userService) {
        this.rentalRepository = rentalRepository;
        this.bookService = bookService;
        this.userService = userService;
    }

    public List<GetRentalDto> getAll(){
        var rentals = rentalRepository.findAll();
        return rentals.stream().map((rental) -> new GetRentalDto(rental.getLoanId(), bookService.getOne(rental.getBook().getId()), userService.getOne(rental.getUser().getUserId()), rental.getRentalDate(), rental.getEndRentalDate(), rental.getReturnDate())).collect(Collectors.toList());
    }
    public GetRentalDto getOne(long id){
        var rental = rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental not found"));
        return new GetRentalDto(rental.getLoanId(), bookService.getOne(rental.getBook().getId()), userService.getOne(rental.getUser().getUserId()), rental.getRentalDate(), rental.getEndRentalDate(), rental.getReturnDate());
    }
    public GetRentalDto create(CreateRentalDto rental){
        var rentalEntity = new RentalEntity();
        rentalEntity.setBook(bookService.getOneBookEntity(rental.getBook()));
        rentalEntity.setUser(userService.getOneUserEntity(rental.getUser()));
        rentalEntity.setRentalDate(rental.getRentalDate());
        rentalEntity.setEndRentalDate(rental.getEndRentalDate());
        rentalEntity.setReturnDate(rental.getReturnDate());
        var newRental = rentalRepository.save(rentalEntity);
        return new GetRentalDto(newRental.getLoanId(), bookService.getOne(newRental.getBook().getId()), userService.getOne(newRental.getUser().getUserId()), newRental.getRentalDate(), newRental.getEndRentalDate(), newRental.getReturnDate());
    }
    public void delete(long id){
        if(!rentalRepository.existsById(id)){
            throw new RuntimeException();
        }
        rentalRepository.deleteById(id);
    }
}
