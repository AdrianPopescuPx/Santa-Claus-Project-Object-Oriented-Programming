package SantaDatabase;

public class SantaGiftsList {

    private final String productName;
    private final Double price;
    private final String category;

    public SantaGiftsList(final String productName, final Double price,
                          final String category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "SantaGiftsList{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
