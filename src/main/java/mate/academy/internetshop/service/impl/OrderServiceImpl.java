package mate.academy.internetshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.ShoppingCartDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    private OrderDao orderDao;

    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public Order completeOrder(List<Product> products, User user) {
        ShoppingCart shoppingCartOfUser = shoppingCartDao.getAll().stream().
                filter(shoppingCart -> shoppingCart.getUser().equals(user)).findFirst().get();
        shoppingCartOfUser.setProducts(new ArrayList<>());
        shoppingCartDao.update(shoppingCartOfUser);
        return orderDao.create(new Order(products, user));
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.getAll().stream()
                .filter(order -> order.getUser().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).get();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }
}
