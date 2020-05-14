package mate.academy.internetshop.dao.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.ConnectionUtil;

@Dao
public class OrderDaoJdbcImpl implements OrderDao {

    @Override
    public Order create(Order order) {
        String query = "INSERT INTO orders (user_id) VALUES (?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            order.setOrderId(resultSet.getLong(1));
            insertOrdersProducts(order);
            return order;
        } catch (SQLException e) {
            throw new DataProcessingException(e);
        }
    }

    @Override
    public Optional<Order> get(Long id) {
        String query = "SELECT * FROM orders WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Order order = createOrderFromResultSetAndOrdersProducts(resultSet);
                return Optional.of(order);
            }
        } catch (SQLException e) {
            throw new DataProcessingException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Order> getAll() {
        String query = "SELECT * FROM orders";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<Order> list = new ArrayList<>();
            while (resultSet.next()) {
                Order order = createOrderFromResultSetAndOrdersProducts(resultSet);
                list.add(order);
            }
            return list;
        } catch (SQLException e) {
            throw new DataProcessingException(e);
        }
    }

    @Override
    public Order update(Order order) {
        String query = "UPDATE orders SET user_id = ? "
                + "WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, order.getUserId());
            statement.setLong(2, order.getOrderId());
            statement.executeUpdate();
            deleteOrderFromOrdersProducts(order.getOrderId());
            insertOrdersProducts(order);
            return order;
        } catch (SQLException e) {
            throw new DataProcessingException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM orders WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            deleteOrderFromOrdersProducts(id);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DataProcessingException(e);
        }
    }

    @Override
    public List<Order> getUserOrders(User user) {
        String query = "SELECT * FROM orders WHERE user_id= ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, user.getUserId());
            ResultSet resultSet = statement.executeQuery();
            List<Order> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(createOrderFromResultSetAndOrdersProducts(resultSet));
            }
            return list;
        } catch (SQLException e) {
            throw new DataProcessingException(e);
        }
    }

    private void insertOrdersProducts(Order order) throws SQLException {
        String query = "INSERT INTO orders_products (order_id, product_id)"
                + " VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Product product : order.getProducts()) {
                PreparedStatement insertStatement =
                        connection.prepareStatement(query);
                insertStatement.setLong(1, order.getOrderId());
                insertStatement.setLong(2, product.getProductId());
                insertStatement.executeUpdate();
            }
        }
    }

    private Order createOrderFromResultSetAndOrdersProducts(ResultSet resultSet)
            throws SQLException {
        Long orderId = resultSet.getLong("order_id");
        Long userId = resultSet.getLong("user_id");
        Order order = new Order(orderId, createProductListFromOrdersProducts(orderId), userId);
        return order;
    }

    private List<Product> createProductListFromOrdersProducts(Long orderId) throws SQLException {
        String query = "SELECT products.* FROM orders_products "
                + "JOIN products USING (product_id) WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            List<Product> list = new ArrayList<>();
            while (resultSet.next()) {
                Long id = resultSet.getLong("product_id");
                String name = resultSet.getString("name");
                BigDecimal price = resultSet.getBigDecimal("price");
                Product product = new Product(id, name, price);
                list.add(product);
            }
            return list;
        }
    }

    private void deleteOrderFromOrdersProducts(Long orderId) throws SQLException {
        String query = "DELETE FROM orders_products WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, orderId);
            statement.executeUpdate();
        }
    }
}
