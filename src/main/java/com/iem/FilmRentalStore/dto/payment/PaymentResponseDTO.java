package com.iem.FilmRentalStore.dto.payment;

import java.time.LocalDateTime;

import com.iem.FilmRentalStore.dto.customer.CustomerDTO;
import com.iem.FilmRentalStore.dto.rental.RentalDTO;
import com.iem.FilmRentalStore.dto.staff.StaffDTO;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class PaymentResponseDTO {

    private Integer paymentId;
    private Double amount;
    private LocalDateTime paymentDate;

    private CustomerDTO customer;
    private StaffDTO staff;
    private RentalDTO rental;
}