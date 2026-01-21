package com.inventoryService;

import com.inventoryService.model.InventoryItem;
import com.inventoryService.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {
    public static void main(String[] args) {

        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner loadData(InventoryRepository repo) {
        return args -> {
            if (repo.count() == 0) {  // only insert if table is empty
                repo.save(new InventoryItem("Phone", 10));
                repo.save(new InventoryItem("Laptop", 5));
                repo.save(new InventoryItem("Tablet", 20));
                repo.save(new InventoryItem("Keyboard", 15));
                repo.save(new InventoryItem("Mouse", 15));
                repo.save(new InventoryItem("CPU", 20));
                repo.save(new InventoryItem("Headphones", 10));
                repo.save(new InventoryItem("Earplugs", 15));
                repo.save(new InventoryItem("RAM", 20));
                repo.save(new InventoryItem("GPU", 10));
                repo.save(new InventoryItem("Mac", 5));
                repo.save(new InventoryItem("Monitor", 5));
            }
        };
    }
}
