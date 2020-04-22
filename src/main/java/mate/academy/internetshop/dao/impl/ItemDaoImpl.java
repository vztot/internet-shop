package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Product;

@Dao
public class ItemDaoImpl implements ItemDao {

    @Override
    public Product create(Product product) {
        return Storage.addItem(product);
    }

    @Override
    public Optional<Product> get(Long id) {
        return Storage.getProducts().stream()
                .filter(item -> item.getProductId().equals(id))
                .findFirst();
    }

    @Override
    public List<Product> getAll() {
        return Storage.getProducts();
    }

    @Override
    public Product update(Product product) {
        Product productThatNeedUpdate = get(product
                .getProductId())
                .orElseThrow(NoSuchElementException::new);

        productThatNeedUpdate.setName(product.getName());
        productThatNeedUpdate.setPrice(product.getPrice());
        return productThatNeedUpdate;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.getProducts()
                .remove(get(id)
                .orElseThrow(NoSuchElementException::new));
    }
}
