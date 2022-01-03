package com.InsuranceAgency.orderservice.api.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {
    private String orderType;
    private String orderInfo;
    private int customerID;

}
