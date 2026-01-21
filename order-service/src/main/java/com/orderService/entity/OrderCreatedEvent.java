package com.orderService.entity;

public record OrderCreatedEvent(
        Long orderId,
        String product,
        int quantity
) {}
