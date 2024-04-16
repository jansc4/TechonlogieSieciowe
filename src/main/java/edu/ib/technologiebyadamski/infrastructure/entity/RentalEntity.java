package edu.ib.technologiebyadamski.infrastructure.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "rental", schema = "library")
public class RentalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long loanId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    @Column(name = "rental_date")
    private Date rentalDate;
    @Column(name = "end_rental_date")
    private Date endRentalDate;
    @Column(name = "return_date")
    private Date returnDate;

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
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
