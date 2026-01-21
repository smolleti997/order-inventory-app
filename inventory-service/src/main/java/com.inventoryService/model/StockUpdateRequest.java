package com.inventoryService.model;

public record StockUpdateRequest(
        Long orderId,
        String product,
        int stock
) {}
