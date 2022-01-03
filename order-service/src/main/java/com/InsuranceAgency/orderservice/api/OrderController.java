package com.InsuranceAgency.orderservice.api;


import com.InsuranceAgency.orderservice.api.dto.OrderDto;
import com.InsuranceAgency.orderservice.repo.model.Order;
import com.InsuranceAgency.orderservice.repo.model.OrderType;
import com.InsuranceAgency.orderservice.service.OrderService;
import org.json.simple.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final String userService = "http://user-service:8081";


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAll();

        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderByID(@PathVariable int id) {
        try {
            Order order = orderService.getOrderByID(id);
            return ResponseEntity.ok(order);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<JSONObject> createOrder(@RequestBody OrderDto order) {
        try {
            RestTemplate restTemplate = new RestTemplate();


            OrderType orderType = OrderType.valueOf(order.getOrderType());
            String orderInfo = order.getOrderInfo();
            int customerID = order.getCustomerID();

            if (customerID != 0) {
                ResponseEntity<JSONObject> response = restTemplate.exchange(
                        userService + "/users/" + customerID, HttpMethod.GET, null, JSONObject.class
                );
            }
            int id = orderService.createOrder(orderType, orderInfo, customerID);
            return ResponseEntity.created(URI.create("/orders/" + id)).build();

        } catch (IllegalArgumentException | NullPointerException e) {
            JSONObject response = new JSONObject();
            response.put("error", "incorrect request data");
            return ResponseEntity.badRequest().body(response);
        } catch (HttpClientErrorException.NotFound e) {
            JSONObject response = new JSONObject();
            response.put("error", "incorrect customer id");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<JSONObject> update(@PathVariable int id, @RequestBody OrderDto order) {
        try {


            int customerID = order.getCustomerID();
            if (customerID != 0) {
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<JSONObject> response = restTemplate.exchange(
                        userService + "/users/" + customerID, HttpMethod.GET, null, JSONObject.class
                );
            }


            String orderInfo = order.getOrderInfo();

            orderService.update(id, orderInfo, customerID);

            return ResponseEntity.noContent().build();
        } catch (HttpClientErrorException.NotFound e) {
            JSONObject response = new JSONObject();
            response.put("error", "incorrect customer id");
            return ResponseEntity.badRequest().body(response);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        orderService.delete(id);

        return ResponseEntity.noContent().build();
    }
}

