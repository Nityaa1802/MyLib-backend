package com.example.libraryapp.dao;

import com.example.libraryapp.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Payment findByUserEmail(@RequestParam String userEmail);
}
