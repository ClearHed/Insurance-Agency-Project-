package com.InsuranceAgency.orderservice.repo;


import com.InsuranceAgency.orderservice.repo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Integer> {

}
