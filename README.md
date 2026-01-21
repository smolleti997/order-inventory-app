# order-inventory-app
 1 User clicks "Submit Order" Angular 2 REST POST request sent to Order Service Spring Boot 3 Order saved to ORDERS table Postgres DB 4 "OrderCreated" event published to topic Kafka 5 Inventory Service receives event and updates stock Java + Kafka 6 UI receives update via WebSocket/SSE Angular
