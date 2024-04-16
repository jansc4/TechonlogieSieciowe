package edu.ib.technologiebyadamski.controller.dto;

import java.util.Date;

public class CreateRentalDto {
    private long book; //przekazujemy tylko id

    private long user;

    private Date rentalDate;

    private Date endRentalDate;

    private Date returnDate;

    public CreateRentalDto(long book, long user, Date rentalDate, Date endRentalDate, Date returnDate) {
        this.book = book;
        this.user = user;
        this.rentalDate = rentalDate;
        this.endRentalDate = endRentalDate;
        this.returnDate = returnDate;
    }

    public CreateRentalDto() {
    }

    public long getBook() {
        return book;
    }

    public void setBook(long book) {
        this.book = book;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getEndRentalDate() {
        return endRentalDate;
    }

    public void setEndRentalDate(Date endRentalDate) {
        this.endRentalDate = endRentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
