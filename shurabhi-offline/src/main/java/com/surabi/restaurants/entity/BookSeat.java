package com.surabi.restaurants.entity;
import io.swagger.annotations.ApiModelProperty;


import javax.persistence.*;
import java.util.Date;


@Entity

public class BookSeat {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    //@Schema(value = "Some optional property", defaultValue = "foo")
    @ApiModelProperty(example = "1")
    private int seatId;

    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(example = "19-Dec-2021")
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
