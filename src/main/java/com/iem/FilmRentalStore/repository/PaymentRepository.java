package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {


    @EntityGraph(attributePaths = {
            "customer",
            "staff",
            "rental"
    })
    Page<Payment> findByCustomer_CustomerId(Short customerId, Pageable pageable);

    @EntityGraph(attributePaths = {
            "customer",
            "staff",
            "rental"
    })
    Page<Payment> findByStaff_StaffId(Byte staffId, Pageable pageable);

    @EntityGraph(attributePaths = {
            "customer",
            "staff",
            "rental"
    })
    Page<Payment> findByStaff_FirstNameContainingIgnoreCaseOrStaff_LastNameContainingIgnoreCase(
            String firstName, String lastName, Pageable pageable);

    List<Payment> findByRental_RentalId(Integer rentalId);

    List<Payment> findByCustomer_FirstNameContainingIgnoreCaseOrCustomer_LastNameContainingIgnoreCase(
            String firstName, String lastName);

    @EntityGraph(attributePaths = {
            "customer",
            "staff",
            "rental"
    })
    Page<Payment> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {
            "customer",
            "staff",
            "rental"
    })
    Optional<Payment> findById(Integer id);
}
