package com.surabi.restaurants.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillOrderDetailsDTO {
    private int BILL_ID;
    private String USERNAME;
    private double BILL_AMOUNT;
    private List<BillDetailDTO> billDetailDTO;
}