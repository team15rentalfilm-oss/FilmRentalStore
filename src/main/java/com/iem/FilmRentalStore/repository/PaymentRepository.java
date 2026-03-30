package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Short> {

    // ID-based (internal use)
    List<Payment> findByCustomer_CustomerId(Short customerId);

    List<Payment> findByStaff_StaffId(Byte staffId);

    List<Payment> findByRental_RentalId(Integer rentalId);

    // Name-based (user search)
    List<Payment> findByCustomer_FirstNameContainingIgnoreCaseOrCustomer_LastNameContainingIgnoreCase(
            String firstName, String lastName);

    List<Payment> findByStaff_FirstNameContainingIgnoreCaseOrStaff_LastNameContainingIgnoreCase(
            String firstName, String lastName);
}