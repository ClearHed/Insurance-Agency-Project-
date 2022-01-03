package com.InsuranceAgency.paymentservice.api.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentDto {
    private int PaymentID;
    private int customerID;
    private int amount;
}
