package mate.academy.internetshop.dao.impl;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.Storage;
import mate.academy.internetshop.model.Item;
import java.util.List;
import java.util.Optional;

public class ItemDaoImpl implements ItemDao {

    @Override
    public Item create(Item item) {
        return Storage.add(item);
    }

    @Override
    public Optional<Item> get(Long id) {
        return Storage.getItems().stream()
                .filter(item -> item.getItemId().equals(id))
                .findFirst();
    }

    @Override
    public List<Item> getAll() {
        return Storage.getItems();
    }

    @Override
    public Item update(Item item) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
