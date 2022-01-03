package com.InsuranceAgency.orderservice.service;


import com.InsuranceAgency.orderservice.repo.OrderRepo;
import com.InsuranceAgency.orderservice.repo.model.Order;
import com.InsuranceAgency.orderservice.repo.model.OrderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {
    public final OrderRepo orderRepo;

    public List<Order> getAll(){
        List<Order> orders = orderRepo.findAll();
        return orders;
    }

    public Order getOrderByID(int id) throws IllegalArgumentException{
        Optional<Order> order = orderRepo.findById(id);

        if (order.isEmpty()) {
            throw new IllegalArgumentException();
        } else{
            return order.get();
        }
    }

    public int createOrder(OrderType orderType, String orderInfo, int customerID) throws IllegalArgumentException{
        Order order = new Order(orderType, orderInfo, customerID);
        Order savedOrder = orderRepo.save(order);

        return savedOrder.getId();
    }

    public void update(int orderID, String orderInfo, int customerID) throws IllegalArgumentException{
        Order order = getOrderByID(orderID);
        if (orderInfo != null) order.setOrderInfo(orderInfo);
        if (customerID != 0) order.setCustomerID(customerID);

        orderRepo.save(order);
    }

    public void delete(int id){
        orderRepo.deleteById(id);
    }
}
