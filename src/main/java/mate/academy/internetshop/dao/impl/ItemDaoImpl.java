package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;

@Dao
public class ItemDaoImpl implements ItemDao {

    @Override
    public Item create(Item item) {
        return Storage.addItem(item);
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
        Item itemThatNeedUpdate = get(item.getItemId())
                .orElseThrow(NoSuchElementException::new);
        itemThatNeedUpdate.setName(item.getName());
        itemThatNeedUpdate.setPrice(item.getPrice());
        return itemThatNeedUpdate;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.getItems().remove(get(id)
                .orElseThrow(NoSuchElementException::new));
    }
}
