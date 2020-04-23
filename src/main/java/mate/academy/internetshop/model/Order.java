package mate.academy.internetshop.model;

import java.util.List;

public class Order {
    private Long orderId;
    private User user;
    private List<Product> products;

    public Order(List<Product> products, User user) {
        this.user = user;
        this.products = products;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
        return "Order{" + "orderId=" + orderId + ", user="
                + user + ", products=" + products + '}';
    }
}
