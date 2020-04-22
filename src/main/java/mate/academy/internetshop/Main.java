package mate.academy.internetshop;

import java.math.BigDecimal;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;

public class Main {
    private static Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void main(String[] args) {
        ItemService itemService = (ItemService) injector.getInstance(ItemService.class);

        initializeDb(itemService);

        itemService.getAll().forEach(System.out::println);

    }

    private static void initializeDb(ItemService itemService) {
        Item item1 = new Item("HONDA 2020 CB650R", new BigDecimal(9199));
        Item item2 = new Item("HONDA 2020 REBEL 500", new BigDecimal(6199));
        Item item3 = new Item("HONDA 2020 CBR600RR", new BigDecimal(11799));

        itemService.create(item1);
        itemService.create(item2);
        itemService.create(item3);
    }
}
