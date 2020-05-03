package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.User;

@Dao
public class UserDaoImpl implements UserDao {

    @Override
    public User create(User user) {
        return Storage.addUser(user);
    }

    @Override
    public Optional<User> get(Long id) {
        return Storage.getUsers().stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return Storage.getUsers();
    }

    @Override
    public User update(User user) {
        User productThatNeedUpdate = get(user
                .getUserId())
                .get();

        productThatNeedUpdate.setName(user.getName());
        productThatNeedUpdate.setLogin(user.getLogin());
        productThatNeedUpdate.setPassword(user.getPassword());
        return productThatNeedUpdate;
    }

    @Override
    public boolean delete(Long userId) {
        return Storage.getUsers()
                .removeIf(product -> product.getUserId().equals(userId));
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Storage.getUsers().stream()
                .filter(s -> s.getLogin().equals(login))
                .findFirst();
    }
}
