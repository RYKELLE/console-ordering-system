package ordering.app.model;

import java.util.ArrayList;

public class Cart {

    private String customerName;
    private ArrayList<CartLine> lines;

    public Cart(String customerName){
        this.customerName = customerName;
        this.lines = new ArrayList<>();
    }

    public ArrayList<CartLine> getLines() {
        return lines;
    }

    public void addLine(CartLine line){
        lines.add(line);
    }

    public double getCartTotal(){
        double priceTotal = 0;
        for(CartLine product: lines){
            priceTotal += product.getTotal();
        }

        return priceTotal;
    }

    @Override
    public String toString() {
        String out = "";

        // header
        out += String.format("%-6s %-6s %-10s\n", "ID", "QTY", "TOTAL");

        // rows
        for (CartLine line : lines) {
            out += String.format("%-6s %-6d %-10.2f\n",
                    line.getProduct().getId(),
                    line.getQuantityBought(),
                    line.getTotal());
        }

        // footer total
        out += String.format("%-6s %-6s %-10.2f",
                "TOTAL", "", getCartTotal());

        return out;
    }

    public boolean isEmpty(){
        return lines.isEmpty();
    }

    public void checkOutCart(){
        for(CartLine product: lines){
            Product p = product.getProduct();
            p.updateStockQuantity(product.getQuantityBought());
            }
    }

    public void printReceipt() {
        System.out.println("\n=========== RECEIPT ===========");
        System.out.println("Customer: " + customerName);
        System.out.println("--------------------------------");

        System.out.printf("%-6s %-8s %-10s\n", "ID", "QTY", "TOTAL");

        for (CartLine line : lines) {
            System.out.printf("%-6s %-8d %-10.2f\n",
                    line.getProduct().getId(),
                    line.getQuantityBought(),
                    line.getTotal());
        }

        System.out.println("--------------------------------");
        System.out.printf("GRAND TOTAL: %.2f\n", getCartTotal());
        System.out.println("================================");
        System.out.println("Thank you for your order!");
    }

    public CartLine checkInCart(Product product){
        for(CartLine p: lines){
            if(p.getProduct().getId().equals(product.getId())){
                return p;
            }
        }

        return null;
    }



}
