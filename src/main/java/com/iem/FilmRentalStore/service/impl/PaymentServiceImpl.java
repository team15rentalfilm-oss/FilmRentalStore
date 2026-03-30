package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.payment.PaymentDTO;
import com.iem.FilmRentalStore.dto.payment.PaymentRequestDTO;
import com.iem.FilmRentalStore.entity.*;
import com.iem.FilmRentalStore.mapper.PaymentMapper;
import com.iem.FilmRentalStore.repository.*;
import com.iem.FilmRentalStore.service.PaymentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RentalRepository rentalRepository;
    private final StaffRepository staffRepository;

    @Override
    public PaymentDTO createPayment(PaymentRequestDTO request) {

        // 🔥 Step 1: Fetch rental
        Rental rental = rentalRepository.findById(request.getRentalId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Rental not found with id: " + request.getRentalId()));

        // 🔥 Step 2: Fetch staff
        Staff staff = staffRepository.findById(request.getStaffId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Staff not found with id: " + request.getStaffId()));

        // 🔥 Step 3: Validate rental
        if (rental.getReturnDate() == null) {
            throw new IllegalStateException("Rental not returned yet");
        }

        // 🔥 Step 4: Create payment
        Payment payment = PaymentMapper.toEntity(rental, staff, request.getAmount());
        payment.setPaymentDate(LocalDateTime.now());

        Payment saved = paymentRepository.save(payment);

        return PaymentMapper.toDTO(saved);
    }

    @Override
    public PaymentDTO getPaymentById(Integer id) {
        return null;
    }

    @Override
    public PaymentDTO getPaymentById(Short id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found with id: " + id));

        return PaymentMapper.toDTO(payment);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(PaymentMapper::toDTO)
                .toList();
    }
}