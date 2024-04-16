package edu.ib.technologiebyadamski.controller.dto;

import java.util.Date;

public class GetRentalDto {
    private long loanId;

    private GetBookDto book;

    private GetUserDto user;

    private Date rentalDate;

    private Date endRentalDate;

    private Date returnDate;

    public GetRentalDto(long loanId, GetBookDto book, GetUserDto user, Date rentalDate, Date endRentalDate, Date returnDate) {
        this.loanId = loanId;
        this.book = book;
        this.user = user;
        this.rentalDate = rentalDate;
        this.endRentalDate = endRentalDate;
        this.returnDate = returnDate;
    }

    public GetRentalDto() {
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public GetBookDto getBook() {
        return book;
    }

    public void setBook(GetBookDto book) {
        this.book = book;
    }

    public GetUserDto getUser() {
        return user;
    }

    public void setUser(GetUserDto user) {
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
