
package  com.inventoryService.listener;
import com.inventoryService.model.InventoryItem;
import com.inventoryService.model.OrderCreatedEvent;
import com.inventoryService.repository.InventoryRepository;
import com.inventoryService.service.OrderServiceClient;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryListener {

    private final InventoryRepository repo;// inject service
    private final OrderServiceClient orderServiceClient;

    public InventoryListener(InventoryRepository repo,
                             OrderServiceClient orderServiceClient) {
        this.repo = repo;
        this.orderServiceClient = orderServiceClient;
    }

    @KafkaListener(topics = "order-created", groupId = "inventory-group", containerFactory = "orderCreatedEventKafkaListenerContainerFactory")
    public void handleOrderCreated(OrderCreatedEvent event) {

        InventoryItem item = repo.findByProduct(event.getProduct())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        item.setStock(item.getStock() - event.getQuantity());
        repo.save(item);

        orderServiceClient.sendStockUpdate(
                event.getOrderId(),
                item.getProduct(),
                item.getStock()
        );
    }

}

