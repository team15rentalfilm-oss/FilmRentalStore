package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.payment.*;
import com.iem.FilmRentalStore.entity.*;
import com.iem.FilmRentalStore.mapper.PaymentMapper;
import com.iem.FilmRentalStore.repository.*;
import com.iem.FilmRentalStore.service.PaymentService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RentalRepository rentalRepository;
    private final StaffRepository staffRepository;

    @Override
    @Transactional
    public PaymentResponseDTO createPayment(PaymentRequestDTO request) {

        Rental rental = rentalRepository.findById(request.getRentalId())
                .orElseThrow(() -> new EntityNotFoundException("Rental not found"));

        if (rental.getReturnDate() == null) {
            throw new IllegalStateException("Rental not returned yet");
        }

        Staff staff = staffRepository.findById(request.getStaffId())
                .orElseThrow(() -> new EntityNotFoundException("Staff not found"));

        Payment payment = PaymentMapper.toEntity(rental, staff, request.getAmount());
        payment.setPaymentDate(LocalDateTime.now());

        return PaymentMapper.toResponseDTO(paymentRepository.save(payment));
    }

    @Override
    public PaymentResponseDTO getPaymentById(Integer id) {
        return paymentRepository.findById(id)
                .map(PaymentMapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));
    }

    private Pageable sanitizePageable(Pageable pageable) {

        int page = pageable.getPageNumber();
        int size = 10;

        List<String> allowed = List.of(
                "paymentId",
                "amount",
                "paymentDate"
        );

        Sort safeSort = Sort.unsorted();

        for (Sort.Order order : pageable.getSort()) {
            if (allowed.contains(order.getProperty())) {
                safeSort = safeSort.and(Sort.by(order));
            }
        }

        if (safeSort.isUnsorted()) {
            safeSort = Sort.by(Sort.Direction.DESC, "paymentDate");
        }

        return PageRequest.of(Math.max(page, 0), size, safeSort);
    }

    @Override
    public Page<PaymentResponseDTO> getAllPayments(Pageable pageable) {

        pageable = sanitizePageable(pageable);

        return paymentRepository.findAll(pageable)
                .map(PaymentMapper::toResponseDTO);
    }

    @Override
    public List<PaymentResponseDTO> getPaymentsByCustomerName(String name) {
        return paymentRepository
                .findByCustomer_FirstNameContainingIgnoreCaseOrCustomer_LastNameContainingIgnoreCase(name, name)
                .stream()
                .map(PaymentMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<PaymentResponseDTO> getPaymentsByRentalId(Integer rentalId) {
        return paymentRepository.findByRental_RentalId(rentalId)
                .stream()
                .map(PaymentMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public PaymentResponseDTO patchPayment(Integer id, PaymentPatchDTO request) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));

        if (request.getAmount() != null) {
            payment.setAmount(request.getAmount());
        }

        if (request.getStaffId() != null) {
            Staff staff = staffRepository.findById(request.getStaffId())
                    .orElseThrow(() -> new EntityNotFoundException("Staff not found"));
            payment.setStaff(staff);
        }

        return PaymentMapper.toResponseDTO(paymentRepository.save(payment));
    }

    @Override
    public Page<PaymentResponseDTO> getByCustomerId(Short customerId, Pageable pageable) {

        pageable = sanitizePageable(pageable);

        return paymentRepository.findByCustomer_CustomerId(customerId, pageable)
                .map(PaymentMapper::toResponseDTO);
    }

    @Override
    public Page<PaymentResponseDTO> getByStaffId(Byte staffId, Pageable pageable) {

        pageable = sanitizePageable(pageable);

        return paymentRepository.findByStaff_StaffId(staffId, pageable)
                .map(PaymentMapper::toResponseDTO);
    }

    @Override
    public Page<PaymentResponseDTO> searchByStaffName(String name, Pageable pageable) {

        pageable = sanitizePageable(pageable);

        return paymentRepository
                .findByStaff_FirstNameContainingIgnoreCaseOrStaff_LastNameContainingIgnoreCase(name, name, pageable)
                .map(PaymentMapper::toResponseDTO);
    }
}
