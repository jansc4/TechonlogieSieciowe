package edu.ib.technologiebyadamski.controller.dto;

import java.util.Date;

public class BookReturnDto {
    private long loanId;
    private Date returnDate;

    public BookReturnDto(long loanId, Date returnDate) {
        this.loanId = loanId;
        this.returnDate = returnDate;
    }

    public BookReturnDto() {
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
