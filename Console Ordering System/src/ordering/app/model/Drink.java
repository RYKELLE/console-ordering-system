package ordering.app.model;

import ordering.app.ItemType;

public class Drink extends Product {

    private final String drinkFlavor;
    private final int drinkMl;

    public Drink(String id, double price, int stockQuantity, String flavor, int ml){
        super(id, price, ItemType.DRINK, stockQuantity);
        this.drinkFlavor = flavor;
        this.drinkMl = ml;
    }

    @Override
    public String toString() {
        return String.format("%-5s %-8s, %-15s — $%7.2f (Qty: %2d, %dml)",
                this.getId(), this.getType(), drinkFlavor, this.getPrice(), this.getStockQuantity(), drinkMl);
    }

}
