package ordering.app.model;

import ordering.app.ItemType;

public class Food extends Product {

    private final String foodName;
    private final String foodHotness;

    public Food(String id, double price, int stockQuantity, String foodName, String hotness){
        super(id, price, ItemType.FOOD, stockQuantity);
        this.foodName = foodName;
        this.foodHotness = hotness;
    }

    @Override
    public String toString() {
        return String.format("%-5s %-8s, %-15s — $%7.2f (Qty: %2d, %s)",
                this.getId(), this.getType(), foodName, this.getPrice(), this.getStockQuantity(), foodHotness);
    }
}
