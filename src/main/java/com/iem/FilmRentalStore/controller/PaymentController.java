package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.payment.PaymentDTO;
import com.iem.FilmRentalStore.dto.payment.PaymentPatchDTO;
import com.iem.FilmRentalStore.dto.payment.PaymentRequestDTO;
import com.iem.FilmRentalStore.dto.payment.PaymentResponseDTO;
import com.iem.FilmRentalStore.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public PaymentResponseDTO createPayment(@RequestBody PaymentRequestDTO request) {
        return paymentService.createPayment(request);
    }

    @GetMapping("/{id}")
    public PaymentResponseDTO getPaymentById(@PathVariable Integer id) {
        return paymentService.getPaymentById(id);
    }

    @GetMapping
    public Page<PaymentResponseDTO> getAllPayments(
            @PageableDefault(size = 10) Pageable pageable) {
        return paymentService.getAllPayments(pageable);
    }

    @PatchMapping("/{id}")
    public PaymentResponseDTO patchPayment(
            @PathVariable Integer id,
            @RequestBody PaymentPatchDTO request) {
        return paymentService.patchPayment(id, request);
    }

    @GetMapping("/search/customer")
    public List<PaymentResponseDTO> getByCustomer(@RequestParam String name) {
        return paymentService.getPaymentsByCustomerName(name);
    }

    @GetMapping("/rental/{rentalId}")
    public List<PaymentResponseDTO> getByRental(@PathVariable Integer rentalId) {
        return paymentService.getPaymentsByRentalId(rentalId);
    }

    @GetMapping("/customer/{customerId}")
    public Page<PaymentResponseDTO> getByCustomerId(
            @PathVariable Short customerId,
            @PageableDefault(size = 10) Pageable pageable) {
        return paymentService.getByCustomerId(customerId, pageable);
    }

    @GetMapping("/staff/{staffId}")
    public Page<PaymentResponseDTO> getByStaffId(
            @PathVariable Byte staffId,
            @PageableDefault(size = 10) Pageable pageable) {
        return paymentService.getByStaffId(staffId, pageable);
    }

    @GetMapping("/search/staff")
    public Page<PaymentResponseDTO> searchByStaff(
            @RequestParam String name,
            @PageableDefault(size = 10) Pageable pageable) {
        return paymentService.searchByStaffName(name, pageable);
    }
}