package com.inventoryService.model;


import jakarta.persistence.*;

@Entity
@Table(name="inventory_item")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String product;
    private int stock;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public InventoryItem() {}

    public InventoryItem(String product, int stock) {
        this.product = product;
        this.stock = stock;
    }

}
