package ordering.app.model;

import ordering.app.ItemType;

abstract public class Product {

    private final String id;
    private final double price;
    private final ItemType type;
    private int stockQuantity;

    public Product(String id, double price, ItemType type, int stockQuantity){
        this.id = id;
        this.price = price;
        this.type = type;
        this.stockQuantity = stockQuantity;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public ItemType getType() {
        return type;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void updateStockQuantity(int quantity){
        this.stockQuantity -= quantity;
    }

    @Override
    public String toString() {
        return String.format("%s %s — $%.2f (Qty: %d)",
                id, type, price, stockQuantity);
    }


}
