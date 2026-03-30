package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.payment.PaymentDTO;
import com.iem.FilmRentalStore.dto.payment.PaymentRequestDTO;
import com.iem.FilmRentalStore.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // 🔥 CREATE PAYMENT
    @PostMapping
    public PaymentDTO createPayment(@Valid @RequestBody PaymentRequestDTO request) {
        return paymentService.createPayment(request);
    }

    // 🔥 GET BY ID
    @GetMapping("/{id}")
    public PaymentDTO getPaymentById(@PathVariable Short id) {
        return paymentService.getPaymentById(id);
    }

    // 🔥 GET ALL
    @GetMapping
    public List<PaymentDTO> getAllPayments() {
        return paymentService.getAllPayments();
    }
}