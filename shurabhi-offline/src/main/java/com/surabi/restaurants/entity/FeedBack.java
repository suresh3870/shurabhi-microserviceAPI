package com.surabi.restaurants.entity;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class FeedBack {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int feedBackId;

    @Temporal(TemporalType.TIMESTAMP)
    public Date feedBackDate;

    private int ordersID;

    private int feedback1to5;
    private String feedback;

    public int getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(int feedBackId) {
        this.feedBackId = feedBackId;
    }

    public Date getFeedBackDate() {
        return feedBackDate;
    }

    public void setFeedBackDate(Date feedBackDate) {
        this.feedBackDate = feedBackDate;
    }

    public int getOrdersID() {
        return ordersID;
    }

    public void setOrdersID(int ordersID) {
        this.ordersID = ordersID;
    }

    public int getFeedback1to5() {
        return feedback1to5;
    }

    public void setFeedback1to5(int feedback1to5) {
        this.feedback1to5 = feedback1to5;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public FeedBack(int feedBackId, Date feedBackDate, int ordersID, int feedback1to5, String feedback) {
        this.feedBackId = feedBackId;
        this.feedBackDate = feedBackDate;
        this.ordersID = ordersID;
        this.feedback1to5 = feedback1to5;
        this.feedback = feedback;
    }

    public FeedBack() {
    }
}
