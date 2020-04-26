package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Order;

@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order create(Order order) {
        return Storage.addOrder(order);
    }

    @Override
    public Optional<Order> get(Long id) {
        return Storage.getOrders().stream()
                .filter(order -> order.getOrderId().equals(id))
                .findFirst();
    }

    @Override
    public List<Order> getAll() {
        return Storage.getOrders();
    }

    @Override
    public Order update(Order order) {
        Order orderThatNeedUpdate = get(order
                .getOrderId())
                .get();

        orderThatNeedUpdate.setProducts(order.getProducts());
        orderThatNeedUpdate.setUser(order.getUser());
        return orderThatNeedUpdate;
    }

    @Override
    public boolean delete(Long orderId) {
        return Storage.getOrders()
                .removeIf(order -> order.getOrderId().equals(orderId));
    }
}
