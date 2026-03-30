package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.payment.*;
import org.springframework.data.domain.*;

import java.util.List;

public interface PaymentService {

    PaymentResponseDTO createPayment(PaymentRequestDTO request);

    PaymentResponseDTO getPaymentById(Integer id);

    Page<PaymentResponseDTO> getAllPayments(Pageable pageable);

    List<PaymentResponseDTO> getPaymentsByCustomerName(String name);

    List<PaymentResponseDTO> getPaymentsByRentalId(Integer rentalId);

    PaymentResponseDTO patchPayment(Integer id, PaymentPatchDTO request);

    Page<PaymentResponseDTO> getByCustomerId(Short customerId, Pageable pageable);

    Page<PaymentResponseDTO> getByStaffId(Byte staffId, Pageable pageable);

    Page<PaymentResponseDTO> searchByStaffName(String name, Pageable pageable);
}