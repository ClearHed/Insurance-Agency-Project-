package com.InsuranceAgency.paymentservice.repo;


import com.InsuranceAgency.paymentservice.repo.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {
}
