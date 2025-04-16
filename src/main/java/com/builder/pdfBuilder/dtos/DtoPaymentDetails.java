package com.builder.pdfBuilder.dtos;

import lombok.Getter;

import java.time.LocalDate;
@Getter
public class DtoPaymentDetails {
    private double initialAmount;
    private double finalAmount;
    private String paymentType;
    private String state;
    private LocalDate date;
    @Override
    public String toString() {
        return "Método de pago: " + paymentType + "\n" +
                "initialAmount: " + initialAmount + "\n" +
                "state: " + state + "\n" +
                "finalAmount: " + finalAmount + "\n" +
                "date: " + date;
    }
}
