package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {

    PaymentDTO createPayment(PaymentDTO paymentDTO);
    PaymentDTO getPaymentById(Short id);
    List<PaymentDTO> getAllPayments();
    PaymentDTO updatePayment(Short id, PaymentDTO paymentDTO);
    void deletePayment(Short id);
    List<PaymentDTO> getPaymentsByCustomerId(Short customerId);
    List<PaymentDTO> getPaymentsByStaffId(Integer staffId);
    List<PaymentDTO> getPaymentsByRentalId(Integer rentalId);
}
