package mate.academy.internetshop.model;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order order = (Order) o;

        if (!Objects.equals(orderId, order.orderId)) {
            return false;
        }
        if (!Objects.equals(user, order.user)) {
            return false;
        }
        return Objects.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 17 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (products != null ? products.hashCode() : 0);
        return result;
    }
}
