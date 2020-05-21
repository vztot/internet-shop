package mate.academy.internetshop.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private Long productId;
    private String name;
    private BigDecimal price;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public Product(Long productId, String name, BigDecimal price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{productId=" + productId + ", name='"
                + name + '\'' + ", price=" + price + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product product = (Product) o;

        if (!Objects.equals(productId, product.productId)) {
            return false;
        }
        if (!Objects.equals(name, product.name)) {
            return false;
        }
        return Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        int result = productId != null ? productId.hashCode() : 0;
        result = 13 * result + (name != null ? name.hashCode() : 0);
        result = 89 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
