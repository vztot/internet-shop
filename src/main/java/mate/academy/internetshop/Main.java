package mate.academy.internetshop;

import java.math.BigDecimal;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.ItemService;

public class Main {
    private static Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void main(String[] args) {
        ItemService itemService = (ItemService) injector.getInstance(ItemService.class);

        initializeDb(itemService);

        itemService.getAll().forEach(System.out::println);

    }

    private static void initializeDb(ItemService itemService) {
        Product product1 = new Product("HONDA 2020 CB650R", new BigDecimal(9199));
        Product product2 = new Product("HONDA 2020 REBEL 500", new BigDecimal(6199));
        Product product3 = new Product("HONDA 2020 CBR600RR", new BigDecimal(11799));

        itemService.create(product1);
        itemService.create(product2);
        itemService.create(product3);

        itemService.deleteByProduct(product1);
        itemService.delete(product2.getProductId());

        product3.setPrice(new BigDecimal(15999));
        itemService.update(product3);
    }
}
