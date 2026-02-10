package ordering.app.model;

public class CartLine {
    private final Product product;
    private int quantityBought;

    public CartLine(Product product, int quantityBought){
        this.product = product;
        this.quantityBought = quantityBought;
    }

    public Product getProduct(){
        return product;
    }

    public int getQuantityBought(){
        return quantityBought;
    }

    public double getTotal(){
        return product.getPrice() * quantityBought;
    }
    @Override
    public String toString(){
        return String.format("%-6s x%-3d = $%.2f",
                product.getId(), quantityBought, getTotal());
    }

    public void updateQuantityBought(int addQuantity){
        this.quantityBought += addQuantity;
    }


}
