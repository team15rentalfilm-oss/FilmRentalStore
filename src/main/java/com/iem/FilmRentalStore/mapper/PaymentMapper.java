package com.iem.FilmRentalStore.mapper;

import com.iem.FilmRentalStore.dto.payment.*;
import com.iem.FilmRentalStore.entity.*;

import java.math.BigDecimal;


public class PaymentMapper {

    public static Payment toEntity(Rental rental, Staff staff, BigDecimal amount) {
        Payment payment = new Payment();
        payment.setRental(rental);
        payment.setCustomer(rental.getCustomer());
        payment.setStaff(staff);
        payment.setAmount(amount);
        return payment;
    }

    public static PaymentDTO toDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();

        dto.setAmount(payment.getAmount());
        dto.setPaymentDate(payment.getPaymentDate().toString());
        dto.setCustomerName(
                payment.getCustomer().getFirstName() + " " +
                        payment.getCustomer().getLastName()
        );

        return dto;
    }

    public static PaymentResponseDTO toResponseDTO(Payment payment) {
        PaymentResponseDTO dto = new PaymentResponseDTO();

        dto.setPaymentId(payment.getPaymentId());
        dto.setAmount(payment.getAmount());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setRentalId(payment.getRental().getRentalId());
        dto.setStaffId(payment.getStaff().getStaffId());
        dto.setCustomerId(payment.getCustomer().getCustomerId());

        return dto;
    }
}