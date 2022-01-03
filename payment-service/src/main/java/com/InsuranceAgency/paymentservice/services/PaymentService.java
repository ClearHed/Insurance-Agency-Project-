package com.InsuranceAgency.paymentservice.services;

import com.InsuranceAgency.paymentservice.repo.PaymentRepo;
import com.InsuranceAgency.paymentservice.repo.model.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class PaymentService {

    private final PaymentRepo paymentRepo;

    public List<Payment> getAll() {
        return paymentRepo.findAll();
    }

    public Payment getById (long id) throws IllegalArgumentException {
        final Optional<Payment> payment  = paymentRepo.findById(id);
        if (payment.isEmpty()) throw new IllegalArgumentException("Payment not found");
        else return payment.get();
    }

    public int create(int PaymentID, int customerID, int amount) {
        final Payment payment = new Payment(PaymentID, customerID, amount);
        final Payment savedPayment = paymentRepo.save(payment);
        return savedPayment.getId();
    }

    public void update(long id, int PaymentID, int customerID, int amount) throws IllegalArgumentException {
        Payment payment = getById(PaymentID);
        if (amount != 0) payment.setAmount(amount);

        paymentRepo.save(payment);

    }

    public void delete(long id) {
        paymentRepo.deleteById(id);
    }

}
