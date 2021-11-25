package com.surabi.restaurants.entity;

import com.surabi.restaurants.DTO.BillDTO;
import com.surabi.restaurants.DTO.BillDetailsDTO;
import com.surabi.restaurants.DTO.MaxSaleDayDTO;
import com.surabi.restaurants.DTO.SaleDTO;

import javax.persistence.*;
import java.util.Date;

@SqlResultSetMapping(name="BillDTOMapping",
        classes = {
                @ConstructorResult(targetClass = BillDetailsDTO.class,
                        columns = {@ColumnResult(name="BILL_ID", type = Integer.class),
                                @ColumnResult(name="USERNAME", type = String.class),
                                @ColumnResult(name="ITEM", type = String.class),
                                @ColumnResult(name="QTY", type = Integer.class),
                                @ColumnResult(name="PRICE", type = Integer.class),
                                @ColumnResult(name="ITEM_TOTALPRICE", type = Integer.class),
                                @ColumnResult(name="BILL_AMOUNT", type = double.class),
                                @ColumnResult(name="PAID_BY", type = String.class)
                        }
                )})

@SqlResultSetMapping(name="BillViewMapping",
        classes = {
                @ConstructorResult(targetClass = BillDTO.class,
                        columns = {@ColumnResult(name="BILL_ID", type = Integer.class),
                                @ColumnResult(name="USERNAME", type = String.class),
                                @ColumnResult(name="BILL_DATE", type = String.class),
                                @ColumnResult(name="BILL_AMOUNT", type = double.class)
                        }
                )})
@SqlResultSetMapping(name="SaleDTOMapping",
        classes = {
                @ConstructorResult(targetClass = SaleDTO.class,
                        columns = {@ColumnResult(name="sum", type = double.class),
                                @ColumnResult(name="extract", type = String.class),
                                @ColumnResult(name="to_char", type = String.class),
                                @ColumnResult(name="city", type = String.class)
                        }
                )})

@SqlResultSetMapping(name="MaxSaleDTOMapping",
        classes = {
                @ConstructorResult(targetClass = MaxSaleDayDTO.class,
                        columns = {@ColumnResult(name="max", type = double.class),
                                @ColumnResult(name="extract", type = Integer.class),
                                @ColumnResult(name="to_char", type = String.class)
                        }
                )})

@Entity
public class Bill {
    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private int billID;

    @OneToOne
    @JoinColumn(name="OrderId", nullable=false)
    private Orders orders;
    private double billAmount;
    @Temporal(TemporalType.TIMESTAMP)
    public Date billDate;
    public String paidBy;

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Bill(int billID, Orders orders, double billAmount, Date billDate) {
        this.billID = billID;
        this.orders = orders;
        this.billAmount = billAmount;
        this.billDate = billDate;
    }

    public Bill() {
    }
}
