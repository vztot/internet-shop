package mate.academy.internetshop.db;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

public class Storage {
    private static Long userId = 0L;
    private static Long bucketId = 0L;
    private static Long orderId = 0L;
    private static Long productId = 0L;
    private static final List<User> users = new ArrayList<>();
    private static final List<Product> products = new ArrayList<>();
    private static final List<Bucket> buckets = new ArrayList<>();
    private static final List<Order> orders = new ArrayList<>();

    public static Product addItem(Product product) {
        productId++;
        product.setProductId(productId);
        products.add(product);
        return product;
    }

    public static List<Product> getProducts() {
        return products;
    }
}
