# order-inventory-app
 1 User clicks "Submit Order" using  Angular 
 2 REST POST request sent to Order Service using Spring Boot 
 3 Order saved to ORDERS table in Postgres 
 4 "OrderCreated" event published to topic in Kafka 
 5 Inventory Service receives event and updates stock using Java + Kafka 
 6 UI receives update via WebSocket/SSE using Angular
