package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.payment.PaymentDTO;
import com.iem.FilmRentalStore.dto.payment.PaymentRequestDTO;

import java.util.List;

public interface PaymentService {

    PaymentDTO createPayment(PaymentRequestDTO request);

    PaymentDTO getPaymentById(Integer id);

    PaymentDTO getPaymentById(Short id);

    List<PaymentDTO> getAllPayments();
}