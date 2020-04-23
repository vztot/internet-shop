package mate.academy.internetshop.model;

import java.util.List;

public class ShoppingCart {
    private Long shoppingCartId;
    private User user;
    private List<Product> products;

    public ShoppingCart(User user, List<Product> products) {
        this.user = user;
        this.products = products;
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" + "shoppingCartId=" + shoppingCartId
                + ", user=" + user + ", products=" + products + '}';
    }
}
