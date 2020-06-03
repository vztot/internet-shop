package mate.academy.internetshop.dao.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.ShoppingCartDao;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.util.ConnectionUtil;

@Dao
public class ShoppingCartDaoJdbcImpl implements ShoppingCartDao {

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        String query = "INSERT INTO shopping_carts (user_id) VALUES (?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, shoppingCart.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            shoppingCart.setShoppingCartId(resultSet.getLong(1));
            insertRelativeProductsToShoppingCart(shoppingCart);
            return shoppingCart;
        } catch (SQLException e) {
            throw new DataProcessingException(e);
        }
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        String query = "SELECT * FROM shopping_carts WHERE cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ShoppingCart shoppingCart = getShoppingCartFromResultSet(resultSet);
                shoppingCart.setProducts(getProductsFromShoppingCart(id));
                return Optional.of(shoppingCart);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException(e);
        }
    }

    @Override
    public List<ShoppingCart> getAll() {
        String query = "SELECT * FROM shopping_carts";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<ShoppingCart> list = new ArrayList<>();
            while (resultSet.next()) {
                ShoppingCart shoppingCart = getShoppingCartFromResultSet(resultSet);
                shoppingCart.setProducts(
                        getProductsFromShoppingCart(shoppingCart.getShoppingCartId()));
                list.add(shoppingCart);
            }
            return list;
        } catch (SQLException e) {
            throw new DataProcessingException(e);
        }
    }

    @Override
    public Optional<ShoppingCart> getByUserId(Long userId) {
        String query = "SELECT * FROM shopping_carts WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ShoppingCart shoppingCart = getShoppingCartFromResultSet(resultSet);
                shoppingCart.setProducts(
                        getProductsFromShoppingCart(shoppingCart.getShoppingCartId()));
                return Optional.of(shoppingCart);
            }
        } catch (SQLException e) {
            throw new DataProcessingException(e);
        }
        return Optional.empty();
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        String query = "UPDATE shopping_carts SET user_id = ?"
                + " WHERE cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, shoppingCart.getUserId());
            statement.setLong(2, shoppingCart.getShoppingCartId());
            statement.executeUpdate();
            deleteShoppingCartFromCartsProducts(shoppingCart.getShoppingCartId());
            insertRelativeProductsToShoppingCart(shoppingCart);
            return shoppingCart;
        } catch (SQLException e) {
            throw new DataProcessingException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM shopping_carts WHERE cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            deleteShoppingCartFromCartsProducts(id);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DataProcessingException(e);
        }
    }

    private void insertRelativeProductsToShoppingCart(ShoppingCart shoppingCart)
            throws SQLException {
        String query = "INSERT INTO shopping_carts_products (cart_id, product_id)"
                + " VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Product product : shoppingCart.getProducts()) {
                PreparedStatement insertStatement =
                        connection.prepareStatement(query);
                insertStatement.setLong(1, shoppingCart.getShoppingCartId());
                insertStatement.setLong(2, product.getProductId());
                insertStatement.executeUpdate();
            }
        }
    }

    private ShoppingCart getShoppingCartFromResultSet(ResultSet resultSet) throws SQLException {
        Long cartId = resultSet.getLong("cart_id");
        Long userId = resultSet.getLong("user_id");
        ShoppingCart shoppingCart = new ShoppingCart(cartId, userId);
        return shoppingCart;
    }

    private List<Product> getProductsFromShoppingCart(Long shoppingCartId)
            throws SQLException {
        String query = "SELECT products.* FROM shopping_carts_products "
                + "JOIN products USING (product_id) WHERE cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, shoppingCartId);
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Long id = resultSet.getLong("product_id");
                String name = resultSet.getString("name");
                BigDecimal price = resultSet.getBigDecimal("price");
                Product product = new Product(name, price);
                product.setProductId(id);
                products.add(product);
            }
            return products;
        }
    }

    private void deleteShoppingCartFromCartsProducts(Long shoppingCartId) throws SQLException {
        String query = "DELETE FROM shopping_carts_products WHERE cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, shoppingCartId);
            statement.executeUpdate();
        }
    }
}
