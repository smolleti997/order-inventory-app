package com.orderService.entity;

public record StockUpdate(
        Long orderId,
        String product,
        int stock
) {}
