package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingCart {
    private Long shoppingCartId;
    private Long userId;
    private List<Product> products;

    public ShoppingCart(Long userId, List<Product> products) {
        this.userId = userId;
        this.products = products;
    }

    public ShoppingCart(Long shoppingCartId, Long userId) {
        this.shoppingCartId = shoppingCartId;
        this.userId = userId;
        this.products = new ArrayList<Product>();
    }

    public ShoppingCart(Long shoppingCartId, Long userId, List<Product> products) {
        this.shoppingCartId = shoppingCartId;
        this.userId = userId;
        this.products = products;
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ShoppingCart{"
                + "shoppingCartId=" + shoppingCartId
                + ", userId=" + userId
                + ", products=" + products + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ShoppingCart that = (ShoppingCart) o;
        return Objects.equals(shoppingCartId, that.shoppingCartId)
                && Objects.equals(userId, that.userId)
                && Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoppingCartId, userId, products);
    }
}
