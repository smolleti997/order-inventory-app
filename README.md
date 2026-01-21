
# 🛒 Order & Inventory Management System

A **microservices-based real-time order and inventory system** built using **Spring Boot, Apache Kafka, Server-Sent Events (SSE), Angular, and Docker**.

The system demonstrates **event-driven architecture**, **asynchronous communication**, and **real-time UI updates**.

---

## 🚀 Architecture Overview

```
Angular UI
   |
   | REST (Create Order)
   v
Order Service
   |
   | Kafka (OrderCreatedEvent)
   v
Inventory Service
   |
   | Kafka (StockUpdatedEvent)
   v
Inventory Service
   |
   | SSE (Server-Sent Events)
   v
Angular UI (Live Updates)
```

---

## 🧩 Services

### 🟦 Order Service

* Creates new orders via REST API
* Publishes `OrderCreatedEvent` to Kafka
* Does **not** directly call Inventory Service

### 🟩 Inventory Service

* Listens to `OrderCreatedEvent` from Kafka
* Updates product stock
* Publishes `StockUpdatedEvent`
* Streams stock updates to frontend using **SSE**

### 🟥 Frontend (Angular)

* Submit orders via REST
* Listens to real-time stock updates using **SSE**
* Automatically updates UI when inventory changes

---

## 🛠️ Tech Stack

* **Backend:** Java, Spring Boot
* **Messaging:** Apache Kafka
* **Frontend:** Angular
* **Real-time Updates:** Server-Sent Events (SSE)
* **Database:** PostgreSQL
* **Containerization:** Docker & Docker Compose

---

## 📦 Kafka Topics

| Topic Name      | Produced By       | Consumed By             |
| --------------- | ----------------- | ----------------------- |
| `order-created` | Order Service     | Inventory Service       |
| `stock-updated` | Inventory Service | Inventory Service → SSE |

---

## ⚙️ Kafka Configuration Summary

### Producer

* `StringSerializer` (key)
* `JsonSerializer` (value)
* Broker: `kafka:9092`

### Consumer

* `StringDeserializer` (key)
* `JsonDeserializer` (value)
* Consumer Group: `inventory-group`

---

## ▶️ Running the Project

### Prerequisites

* Docker
* Docker Compose
* Node.js (for Angular local dev)

---

### Start Backend Services

```bash
docker-compose up
```

This starts:

* Kafka
* Zookeeper
* PostgreSQL
* Order Service
* Inventory Service

---

### Start Angular Frontend

```bash
cd frontend
npm install
ng serve
```

Frontend runs at:

```
http://localhost:4200
```

---

## 🔄 Order Flow Example

1. User submits an order from Angular UI
2. Order Service creates order and publishes Kafka event
3. Inventory Service consumes event and updates stock
4. Inventory Service publishes stock update
5. Angular UI receives update instantly via SSE

---

## 💡 Why SSE instead of WebSockets?

* One-way communication (server → client)
* Simpler than WebSockets
* Automatic reconnection
* Perfect for streaming stock updates

---

## 📁 Project Structure

```
order-inventory-app/
 ├── order-service/
 ├── inventory-service/
 ├── frontend/
 ├── docker-compose.yml
 └── README.md
```

---

## 📌 Key Concepts Demonstrated

* Event-driven microservices
* Kafka producer & consumer configuration
* Loose coupling between services
* Real-time UI updates without polling
* Dockerized infrastructure

---

## 📈 Future Improvements

* Add retry & dead-letter topics
* Implement order failure events
* Add authentication (OAuth / JWT)
* Improve UI with order status tracking


