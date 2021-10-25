package com.surabi.restaurants.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OrderDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer OrderDetailsID;

    @ManyToOne
    @JoinColumn(name="OrderId", nullable=false)
    private Orders orders;

    @ManyToOne
    @JoinColumn(name="MenuId", nullable=false)
    private Menu menu;

    private Integer Quantity;

    private double itemTotalprice;

}
