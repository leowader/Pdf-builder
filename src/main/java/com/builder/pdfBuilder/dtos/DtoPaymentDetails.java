package com.builder.pdfBuilder.dtos;

import com.builder.pdfBuilder.domain.Prototype.IPrototype;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DtoPaymentDetails implements IPrototype<DtoPaymentDetails> {
    private double initialAmount;
    private double finalAmount;
    private String paymentType;
    private String state;
    private LocalDate date;

    public DtoPaymentDetails(DtoPaymentDetails dtoPaymentDetails) {
        this.initialAmount=dtoPaymentDetails.getInitialAmount();
        this.finalAmount=dtoPaymentDetails.getFinalAmount();
        this.paymentType=dtoPaymentDetails.getPaymentType();
        this.state=dtoPaymentDetails.getState();
        this.date=dtoPaymentDetails.getDate();
    }

    @Override
    public String toString() {
        return "Método de pago: " + paymentType + "\n" +
                "initialAmount: " + initialAmount + "\n" +
                "state: " + state + "\n" +
                "finalAmount: " + finalAmount + "\n" +
                "date: " + date;
    }

    @Override
    public DtoPaymentDetails clonacionSimple() {
        return new DtoPaymentDetails(this);
    }

    @Override
    public DtoPaymentDetails clonacionProfunda() {
        return this.clonacionSimple();
    }
}
