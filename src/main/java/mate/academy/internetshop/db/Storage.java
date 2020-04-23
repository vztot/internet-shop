package mate.academy.internetshop.db;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;

public class Storage {
    private static Long userId = 0L;
    private static Long shoppingCartId = 0L;
    private static Long orderId = 0L;
    private static Long productId = 0L;
    private static final List<User> users = new ArrayList<>();
    private static final List<Product> products = new ArrayList<>();
    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static final List<Order> orders = new ArrayList<>();

    public static Product addProduct(Product product) {
        productId++;
        product.setProductId(productId);
        products.add(product);
        return product;
    }

    public static Order addOrder(Order order) {
        orderId++;
        order.setOrderId(orderId);
        orders.add(order);
        return order;
    }

    public static ShoppingCart addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartId++;
        shoppingCart.setShoppingCartId(shoppingCartId);
        shoppingCarts.add(shoppingCart);
        return shoppingCart;
    }

    public static User addUser(User user) {
        userId++;
        user.setUserId(userId);
        users.add(user);
        return user;
    }

    public static List<Product> getProducts() {
        return products;
    }

    public static List<Order> getOrders() {
        return orders;
    }

    public static List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public static List<User> getUsers() {
        return users;
    }
}
