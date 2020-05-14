package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.ConnectionUtil;

@Dao
public class UserDaoJdbcImpl implements UserDao {

    @Override
    public User create(User user) {
        String query = "INSERT INTO users (name, login, password) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            user.setUserId(resultSet.getLong(1));
            selectAndInsertRelativeRole(user);
        } catch (SQLException e) {
            throw new DataProcessingException(e);
        }
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        String query = "SELECT * FROM users WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new DataProcessingException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<User> list = new ArrayList<>();
            while (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            throw new DataProcessingException(e);
        }
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users SET name = ?, login = ?, password = ? "
                + "WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getUserId());
            statement.executeUpdate();
            deleteUserFromUsersRoles(user.getUserId());
            insertToUsersRoles(user);
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM users WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            deleteUserOrdersProducts(id);
            deleteUserOrders(id);
            deleteUserShoppingCartProducts(id);
            deleteUserShoppingCart(id);
            deleteUserFromUsersRoles(id);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DataProcessingException(e);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {
        String query = "SELECT * FROM users WHERE login = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new DataProcessingException(e);
        }
        return Optional.empty();
    }

    private void selectAndInsertRelativeRole(User user) throws SQLException {
        String insertQuery = "INSERT INTO users_roles (user_id, role_id) VALUES (?, ?)";
        String selectQuery = "SELECT role_id FROM roles WHERE role_name = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Role role : user.getRoles()) {
                PreparedStatement selectStatement =
                        connection.prepareStatement(selectQuery);
                selectStatement.setString(1, role.getRoleName().name());
                ResultSet resultSet = selectStatement.executeQuery();
                resultSet.next();
                PreparedStatement insertStatement =
                        connection.prepareStatement(insertQuery);
                insertStatement.setLong(1, user.getUserId());
                insertStatement.setLong(2, resultSet.getLong("role_id"));
                insertStatement.executeUpdate();
            }
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("user_id");
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        User user = new User(id, name, login, password);
        user.setRoles(getUserRoles(id));
        return user;
    }

    private Set<Role> getUserRoles(Long userId) throws SQLException {
        String query = "SELECT role_name FROM users_roles JOIN roles"
                + " USING (role_id) WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            Set<Role> set = new HashSet<>();
            while (resultSet.next()) {
                set.add(Role.of(resultSet.getString("role_name")));
            }
            return set;
        }
    }

    private void deleteUserFromUsersRoles(Long userId) throws SQLException {
        String query = "DELETE FROM users_roles WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            statement.executeUpdate();
        }
    }

    private void deleteUserOrdersProducts(Long userId) throws SQLException {
        String query = "DELETE FROM orders_products WHERE order_id IN"
                + " (SELECT order_id FROM orders WHERE user_id = ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            statement.executeUpdate();
        }
    }

    private void deleteUserOrders(Long userId) throws SQLException {
        String query = "DELETE FROM orders WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            statement.executeUpdate();
        }
    }

    private void deleteUserShoppingCartProducts(Long userId) throws SQLException {
        String query = "DELETE FROM shopping_carts_products WHERE cart_id IN"
                + " (SELECT cart_id FROM shopping_carts WHERE user_id = ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            statement.executeUpdate();
        }
    }

    private void deleteUserShoppingCart(Long userId) throws SQLException {
        String query = "DELETE FROM shopping_carts WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            statement.executeUpdate();
        }
    }

    private void insertToUsersRoles(User user) throws SQLException {
        String selectQuery = "SELECT role_id FROM roles WHERE role_name = ?";
        String insertQuery = "INSERT INTO users_roles (user_id, role_id) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Role role : user.getRoles()) {
                PreparedStatement selectStatement =
                        connection.prepareStatement(selectQuery);
                selectStatement.setString(1, role.getRoleName().name());
                ResultSet resultSet = selectStatement.executeQuery();
                resultSet.next();
                PreparedStatement insertStatement =
                        connection.prepareStatement(insertQuery);
                insertStatement.setLong(1, user.getUserId());
                insertStatement.setLong(2, resultSet.getLong("role_id"));
                insertStatement.executeUpdate();
            }
        }
    }
}
