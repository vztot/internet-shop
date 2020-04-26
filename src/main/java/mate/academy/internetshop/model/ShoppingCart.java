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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ShoppingCart that = (ShoppingCart) o;

        if (shoppingCartId != null ? !shoppingCartId.equals(that.shoppingCartId)
                : that.shoppingCartId != null) {
            return false;
        }
        if (user != null ? !user.equals(that.user) : that.user != null) {
            return false;
        }
        return products != null ? products.equals(that.products) : that.products == null;
    }

    @Override
    public int hashCode() {
        int result = shoppingCartId != null ? shoppingCartId.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (products != null ? products.hashCode() : 0);
        return result;
    }
}
