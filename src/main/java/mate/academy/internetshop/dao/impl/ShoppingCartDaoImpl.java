package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.ShoppingCartDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.model.ShoppingCart;

public class ShoppingCartDaoImpl implements ShoppingCartDao {

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return Storage.addShoppingCart(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        return Storage.getShoppingCarts().stream()
                .filter(shoppingCart -> shoppingCart.getShoppingCartId().equals(id))
                .findFirst();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return Storage.getShoppingCarts();
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        ShoppingCart shoppingCartThatNeedUpdate = get(shoppingCart
                .getShoppingCartId())
                .get();

        shoppingCartThatNeedUpdate.setProducts(shoppingCart.getProducts());
        shoppingCartThatNeedUpdate.setUserId(shoppingCart.getUserId());
        return shoppingCartThatNeedUpdate;
    }

    @Override
    public boolean delete(Long shoppingCartId) {
        return Storage.getShoppingCarts()
                .removeIf(shoppingCart -> shoppingCart.getShoppingCartId().equals(shoppingCartId));
    }
}
