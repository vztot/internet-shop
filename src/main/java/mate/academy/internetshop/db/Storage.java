package mate.academy.internetshop.db;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

public class Storage {
    private static Long userId = 0L;
    private static Long bucketId = 0L;
    private static Long orderId = 0L;
    private static Long itemId = 0L;
    private static final List<User> users = new ArrayList<>();
    private static final List<Item> items = new ArrayList<>();
    private static final List<Bucket> buckets = new ArrayList<>();
    private static final List<Order> orders = new ArrayList<>();

    public static Item addItem(Item item) {
        itemId++;
        item.setItemId(itemId);
        items.add(item);
        return item;
    }

    public static List<Item> getItems() {
        return items;
    }
}
