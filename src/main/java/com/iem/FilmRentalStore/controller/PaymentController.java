package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.PaymentDTO;
import com.iem.FilmRentalStore.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getPayments(
            @RequestParam(required = false) Short customerId,
            @RequestParam(required = false) Integer staffId,
            @RequestParam(required = false) Integer rentalId
    ) {
        if (customerId != null) {
            return ResponseEntity.ok(paymentService.getPaymentsByCustomerId(customerId));
        }
        if (staffId != null) {
            return ResponseEntity.ok(paymentService.getPaymentsByStaffId(staffId));
        }
        if (rentalId != null) {
            return ResponseEntity.ok(paymentService.getPaymentsByRentalId(rentalId));
        }
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Short id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        return new ResponseEntity<>(paymentService.createPayment(paymentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable Short id, @Valid @RequestBody PaymentDTO paymentDTO) {
        return ResponseEntity.ok(paymentService.updatePayment(id, paymentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Short id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
