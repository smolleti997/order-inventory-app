package com.inventoryService.model;

public class OrderCreatedEvent {
    private Long orderId;    // NEW
    private String product;
    private int quantity;

    // Constructors
    public OrderCreatedEvent() {}

    public OrderCreatedEvent(Long orderId, String product, int quantity) {
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
    }

    // Getters & setters
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
