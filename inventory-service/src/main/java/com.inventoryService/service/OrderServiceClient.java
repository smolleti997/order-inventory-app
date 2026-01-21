package com.inventoryService.service;

import com.inventoryService.model.StockUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceClient {

    private final RestTemplate restTemplate;

    public OrderServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendStockUpdate(Long orderId, String product, int stock) {
        StockUpdateRequest request =
                new StockUpdateRequest(orderId, product, stock);

        restTemplate.postForObject(
                "http://order-service:8080/orders/order-updates",
                request,
                Void.class
        );
    }
}

