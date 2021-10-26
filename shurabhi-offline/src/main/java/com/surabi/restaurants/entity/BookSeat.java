package com.surabi.restaurants.entity;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class BookSeat {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int seatId;

    @Temporal(TemporalType.TIMESTAMP)
    public Date bookingDate;

    public BookSeat(int seatId, Date bookingDate) {
        this.seatId = seatId;
        this.bookingDate = bookingDate;
    }

    public BookSeat() {
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
}
