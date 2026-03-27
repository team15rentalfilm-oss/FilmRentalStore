package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.entity.Payment;
import com.iem.FilmRentalStore.exception.ResourceNotFoundException;
import com.iem.FilmRentalStore.repository.CustomerRepository;
import com.iem.FilmRentalStore.repository.PaymentRepository;
import com.iem.FilmRentalStore.repository.RentalRepository;
import com.iem.FilmRentalStore.repository.StaffRepository;
import com.iem.FilmRentalStore.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;
    private final RentalRepository rentalRepository;

    public PaymentServiceImpl(
            PaymentRepository paymentRepository,
            CustomerRepository customerRepository,
            StaffRepository staffRepository,
            RentalRepository rentalRepository
    ) {
        this.paymentRepository = paymentRepository;
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        validateReferences(paymentDTO);

        Payment payment = toEntity(paymentDTO);
        if (payment.getPaymentDate() == null) {
            payment.setPaymentDate(LocalDateTime.now());
        }

        return toDTO(paymentRepository.save(payment));
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentDTO getPaymentById(Short id) {
        return toDTO(findPayment(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public PaymentDTO updatePayment(Short id, PaymentDTO paymentDTO) {
        validateReferences(paymentDTO);

        Payment payment = findPayment(id);
        payment.setCustomerId(paymentDTO.getCustomerId());
        payment.setStaffId(paymentDTO.getStaffId());
        payment.setRentalId(paymentDTO.getRentalId());
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentDate(paymentDTO.getPaymentDate() != null ? paymentDTO.getPaymentDate() : payment.getPaymentDate());

        return toDTO(paymentRepository.save(payment));
    }

    @Override
    public void deletePayment(Short id) {
        paymentRepository.delete(findPayment(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentDTO> getPaymentsByCustomerId(Short customerId) {
        ensureCustomerExists(customerId);
        return paymentRepository.findByCustomerId(customerId).stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentDTO> getPaymentsByStaffId(Integer staffId) {
        ensureStaffExists(staffId);
        return paymentRepository.findByStaffId(staffId).stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentDTO> getPaymentsByRentalId(Integer rentalId) {
        ensureRentalExists(rentalId);
        return paymentRepository.findByRentalId(rentalId).stream()
                .map(this::toDTO)
                .toList();
    }

    private Payment findPayment(Short id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment", "id", id));
    }

    private void validateReferences(PaymentDTO paymentDTO) {
        ensureCustomerExists(paymentDTO.getCustomerId());
        ensureStaffExists(paymentDTO.getStaffId());
        if (paymentDTO.getRentalId() != null) {
            ensureRentalExists(paymentDTO.getRentalId());
        }
    }

    private void ensureCustomerExists(Short customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Customer", "id", customerId);
        }
    }

    private void ensureStaffExists(Integer staffId) {
        if (!staffRepository.existsById(staffId)) {
            throw new ResourceNotFoundException("Staff", "id", staffId);
        }
    }

    private void ensureRentalExists(Integer rentalId) {
        if (!rentalRepository.existsById(rentalId)) {
            throw new ResourceNotFoundException("Rental", "id", rentalId);
        }
    }

    private PaymentDTO toDTO(Payment payment) {
        return new PaymentDTO(
                payment.getPaymentId(),
                payment.getCustomerId(),
                payment.getStaffId(),
                payment.getRentalId(),
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getLastUpdate()
        );
    }

    private Payment toEntity(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setPaymentId(paymentDTO.getPaymentId());
        payment.setCustomerId(paymentDTO.getCustomerId());
        payment.setStaffId(paymentDTO.getStaffId());
        payment.setRentalId(paymentDTO.getRentalId());
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentDate(paymentDTO.getPaymentDate());
        payment.setLastUpdate(paymentDTO.getLastUpdate());
        return payment;
    }
}
