package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

    @Inject
    private ItemDao itemDao;

    @Override
    public Product create(Product product) {
        return itemDao.create(product);
    }

    @Override
    public Optional<Product> get(Long id) {
        return itemDao.get(id);
    }

    @Override
    public List<Product> getAll() {
        return itemDao.getAll();
    }

    @Override
    public Product update(Product product) {
        return itemDao.update(product);
    }

    @Override
    public boolean delete(Long id) {
        return itemDao.delete(id);
    }

    @Override
    public boolean deleteByProduct(Product product) {
        return itemDao.delete(product.getProductId());
    }
}
