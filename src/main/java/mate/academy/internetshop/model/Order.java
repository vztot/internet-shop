package mate.academy.internetshop.model;

import java.util.List;
import java.util.Objects;

public class Order {
    private Long orderId;
    private Long userId;
    private List<Product> products;

    public Order(List<Product> products, Long userId) {
        this.userId = userId;
        this.products = products;
    }

    public Order(Long orderId, List<Product> products, Long userId) {
        this.orderId = orderId;
        this.userId = userId;
        this.products = products;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
        return "Order{"
                + "orderId=" + orderId
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
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId)
                && Objects.equals(userId, order.userId)
                && Objects.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId, products);
    }
}
