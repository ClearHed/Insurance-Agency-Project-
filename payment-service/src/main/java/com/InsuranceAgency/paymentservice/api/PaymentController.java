package com.InsuranceAgency.paymentservice.api;


import com.InsuranceAgency.paymentservice.api.dto.PaymentDto;
import com.InsuranceAgency.paymentservice.repo.model.Payment;
import com.InsuranceAgency.paymentservice.services.PaymentService;
import org.json.simple.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;
    private final String userService = "http://user-service:8081";

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllRequests() {
        List<Payment> payments = paymentService.getAll();

        return ResponseEntity.ok(payments);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getRequestByID(@PathVariable long id) {
        try {
            Payment payment = paymentService.getById(id);

            return ResponseEntity.ok(payment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/create")
    public ResponseEntity<JSONObject> createPayment(@RequestBody PaymentDto payment){
        RestTemplate restTemplate = new RestTemplate();
        int PaymentID = payment.getPaymentID();
        int customerID = payment.getCustomerID();
        int amount = payment.getAmount();

        if (customerID != 0){
            ResponseEntity<JSONObject> response = restTemplate.exchange(
                    userService + "/users/" + customerID, HttpMethod.GET, null, JSONObject.class
            );
        }

        int id = paymentService.create(PaymentID, customerID, amount);

        return ResponseEntity.created(URI.create("/payments/" + id)).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable int id){
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
