//package mate.academy.internetshop.service;
//
//import mate.academy.internetshop.dao.ItemDao;
//import mate.academy.internetshop.model.Item;
//import java.util.List;
//import java.util.Optional;
//
//public class ItemServiceImpl implements ItemService {
//
//    @Inject
//    private ItemDao itemDao;
//
//    @Override
//    public Item create(Item item) {
//        return itemDao.create(item);
//    }
//
//    @Override
//    public Optional<Item> get(Long id) {
//        return itemDao.get(id);
//    }
//
//    @Override
//    public List<Item> getAll() {
//        return itemDao.getAll();
//    }
//
//    @Override
//    public Item update(Item item) {
//        return itemDao.update(item);
//    }
//
//    @Override
//    public boolean delete(Long id) {
//        return false;
//    }
//}
