package com.InsuranceAgency.orderservice.repo.model;


import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(columnDefinition = "ENUM('Life', 'Property')")
    @Enumerated(EnumType.STRING)
    @NotNull
    private OrderType orderType;

    @NotNull
    private String orderInfo;

    @NotNull
    private int customerID;

    public Order() {
    }

    public Order(OrderType orderType, String orderInfo, int customerID) {
        this.orderType = orderType;
        this.orderInfo = orderInfo;
        this.customerID = customerID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
