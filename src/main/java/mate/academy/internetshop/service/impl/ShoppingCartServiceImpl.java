package mate.academy.internetshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.dao.ShoppingCartDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCart.getProducts().add(product);
        return shoppingCartDao.update(shoppingCart);
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        boolean isRemoved = shoppingCart.getProducts().remove(product);
        if (isRemoved) {
            shoppingCartDao.update(shoppingCart);
        }
        return isRemoved;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        List<Product> newProductListOfUser = new ArrayList<>();
        shoppingCart.setProducts(newProductListOfUser);
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return shoppingCartDao.getAll().stream()
                .filter(shoppingCart -> shoppingCart.getUser().getUserId().equals(userId))
                .findFirst()
                .get();
    }

    @Override
    public List<Product> getAllProducts(ShoppingCart shoppingCart) {
        return shoppingCartDao.get(shoppingCart.getShoppingCartId()).get().getProducts();
    }
}
