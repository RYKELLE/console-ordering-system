package ordering.app.model;

import ordering.app.ItemType;

public class Cookie extends Product {

    private final int cookieGrams;
    private final String cookieFlavor;

    public Cookie(String id, double price, int stockQuantity, int grams, String flavor){
        super(id, price, ItemType.COOKIE, stockQuantity);
        this.cookieGrams = grams;
        this.cookieFlavor = flavor;
    }

    @Override
    public String toString() {
        return String.format("%-5s %-8s, %-15s — $%7.2f (Qty: %2d, %dg)",
                this.getId(), this.getType(), cookieFlavor, this.getPrice(), this.getStockQuantity(), cookieGrams);
    }



}
