package com.example.subscriptionapp;

public class Subscription {

    private int id;
    private String serviceName;
    private double price;
    private String billingCycle;

    // Empty constructor
    public Subscription() {
    }

    // Constructor for Add
    public Subscription(String serviceName, double price, String billingCycle) {

        this.serviceName = serviceName;
        this.price = price;
        this.billingCycle = billingCycle;
    }

    // ✅ Constructor for Update
    public Subscription(int id, String serviceName, double price, String billingCycle) {

        this.id = id;
        this.serviceName = serviceName;
        this.price = price;
        this.billingCycle = billingCycle;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getPrice() {
        return price;
    }

    public String getBillingCycle() {
        return billingCycle;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBillingCycle(String billingCycle) {
        this.billingCycle = billingCycle;
    }
}