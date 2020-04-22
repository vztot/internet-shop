package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.Item;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final List<Item> items = new ArrayList<>();

    public static Item add(Item item) {
        item.setItemId(Item.generateId());
        items.add(item);
        return item;
    }

    public static List<Item> getItems() {
        return items;
    }
}
