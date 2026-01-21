package com.orderService.controller;

import com.orderService.entity.Order;
import com.orderService.entity.OrderCreatedEvent;
import com.orderService.entity.StockUpdate;
import com.orderService.repository.OrderRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository repo;
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;
    private final OrderEventController orderEventController;

    public OrderController(OrderRepository repo, KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate, OrderEventController orderEventController) {
        this.repo = repo;
        this.kafkaTemplate = kafkaTemplate;
        this.orderEventController = orderEventController;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        Order saved = repo.save(order);
        OrderCreatedEvent event =
                new OrderCreatedEvent(saved.getId(), saved.getProduct(), saved.getQuantity());

        kafkaTemplate.send("order-created", event);

        return saved;
    }

    @PostMapping("/order-updates")
    public void receiveStockUpdate(@RequestBody StockUpdate update) {
        orderEventController.sendOrderUpdate(update);
    }
}

