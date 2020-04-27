package mate.academy.internetshop.service;

import java.util.List;

public interface GenericService<T, K> {
    T create(T t);

    T get(K id);

    List<T> getAll();

    T update(T t);

    boolean delete(K id);
}
