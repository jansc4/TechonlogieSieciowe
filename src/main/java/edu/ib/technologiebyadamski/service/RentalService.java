package edu.ib.technologiebyadamski.service;

import edu.ib.technologiebyadamski.controller.dto.BookReturnDto;
import edu.ib.technologiebyadamski.controller.dto.CreateRentalDto;
import edu.ib.technologiebyadamski.controller.dto.CreateRentalResponseDto;
import edu.ib.technologiebyadamski.controller.dto.GetRentalDto;
import edu.ib.technologiebyadamski.infrastructure.entity.RentalEntity;
import edu.ib.technologiebyadamski.infrastructure.repository.RentalRepository;
import edu.ib.technologiebyadamski.service.error.RentalNotFoundException;
import edu.ib.technologiebyadamski.service.error.WrongDateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
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
        var rental = rentalRepository.findById(id).orElseThrow(() -> RentalNotFoundException.create(id));
        return new GetRentalDto(rental.getLoanId(), bookService.getOne(rental.getBook().getId()), userService.getOne(rental.getUser().getUserId()), rental.getRentalDate(), rental.getEndRentalDate(), rental.getReturnDate());
    }
    @Transactional
    public CreateRentalResponseDto create(CreateRentalDto rental){
        if  (rental.getRentalDate().before(Date.valueOf(LocalDate.now()))){
            throw WrongDateException.create(rental.getRentalDate());
        }
        if (rental.getRentalDate().after(rental.getEndRentalDate())){
            throw WrongDateException.create(rental.getEndRentalDate());
        }

        var rentalEntity = new RentalEntity();
        rentalEntity.setBook(bookService.getOneBookEntity(rental.getBook()));
        rentalEntity.setUser(userService.getOneUserEntity(rental.getUser()));
        rentalEntity.setRentalDate(rental.getRentalDate());
        rentalEntity.setEndRentalDate(rental.getEndRentalDate());
        rentalEntity.setReturnDate(null);
        var newRental = rentalRepository.save(rentalEntity);
        return new CreateRentalResponseDto(newRental.getLoanId(), bookService.getOne(newRental.getBook().getId()), userService.getOne(newRental.getUser().getUserId()), newRental.getRentalDate(), newRental.getEndRentalDate());
    }
    public void delete(long id){
        if(!rentalRepository.existsById(id)){
            throw RentalNotFoundException.create(id);
        }
        rentalRepository.deleteById(id);
    }
    public GetRentalDto bookReturn(BookReturnDto dto){
        var rental = rentalRepository.findById(dto.getLoanId()).orElseThrow(() -> RentalNotFoundException.create(dto.getLoanId()));
        if (rental.getRentalDate().after(dto.getReturnDate())){
            throw WrongDateException.create(dto.getReturnDate());
        }
        rental.setReturnDate(dto.getReturnDate());
        rentalRepository.save(rental);
        return new GetRentalDto(rental.getLoanId(), bookService.getOne(rental.getBook().getId()), userService.getOne(rental.getUser().getUserId()), rental.getRentalDate(), rental.getEndRentalDate(), rental.getReturnDate());
    }
}
