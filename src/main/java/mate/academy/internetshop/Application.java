package mate.academy.internetshop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.ShoppingCartService;
import mate.academy.internetshop.service.UserService;

public class Application {
    private static Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void main(String[] args) {
        UserService userService = (UserService) injector.getInstance(UserService.class);
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        ShoppingCartService shoppingCartService
                = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);

        letItTest(userService, productService, shoppingCartService, orderService);
    }

    private static void letItTest(UserService userService, ProductService productService,
                                  ShoppingCartService shoppingCartService,
                                  OrderService orderService) {
        User user1 = new User("Alex", "get17", "password");
        User user2 = new User("Max", "max102901", "asjdhfoiahds2198379821");
        User user3 = new User("Alice", "alisa2000", "(O)*&()(*&%!@(*#@!&#_(@!*&)");

        Product product1 = new Product("HONDA 2020 CB650R", new BigDecimal(9199));
        Product product2 = new Product("HONDA 2020 REBEL 500", new BigDecimal(6199));
        Product product3 = new Product("HONDA 2020 CBR600RR", new BigDecimal(11799));

        ShoppingCart shoppingCart1 =
                new ShoppingCart(user1,
                        new ArrayList<>(Arrays.asList(new Product[]{product1})));
        ShoppingCart shoppingCart2 =
                new ShoppingCart(user2,
                        new ArrayList<>(Arrays.asList(new Product[]{product1, product2})));
        ShoppingCart shoppingCart3 =
                new ShoppingCart(user3,
                        new ArrayList<>(Arrays.asList(
                                new Product[]{product1, product2, product3})));

        Storage.addShoppingCart(shoppingCart1);
        Storage.addShoppingCart(shoppingCart2);
        Storage.addShoppingCart(shoppingCart3);

        System.out.println("\n#####################################");
        System.out.println("USER SERVCE TEST");
        System.out.println("#####################################");
        System.out.println("\n[Create & Read]");
        userService.create(user1);
        userService.create(user2);
        userService.create(user3);
        System.out.println(userService.getAll());

        System.out.println("\n[Update]");
        System.out.println("Before update: " + userService.get(2L));
        User newUser2 = new User("MaxTeperNeMax", "maxNeMax", "1111");
        newUser2.setUserId(2L);
        userService.update(newUser2);
        System.out.println("After update: " + userService.get(2L));

        System.out.println("\n[Delete]");
        System.out.println("Before delete" + userService.getAll());
        userService.delete(newUser2.getUserId());
        System.out.println("After delete" + userService.getAll());

        System.out.println("\n#####################################");
        System.out.println("PRODUCT SERVICE TEST");
        System.out.println("#####################################");
        System.out.println("\n[Create & Read]");
        productService.create(product1);
        productService.create(product2);
        productService.create(product3);
        System.out.println(productService.getAll());

        System.out.println("\n[Update]");
        System.out.println("Before update: " + productService.get(2L));
        Product newProduct2 = new Product("HONDA 2021 CBR 600CC", new BigDecimal(20000));
        newProduct2.setProductId(2L);
        productService.update(newProduct2);
        System.out.println("After update: " + productService.get(2L));

        System.out.println("\n[Delete]");
        System.out.println("Before delete" + productService.getAll());
        productService.delete(product2.getProductId());
        System.out.println("After delete" + productService.getAll());

        System.out.println("\n#####################################");
        System.out.println("SHOPPING CART SERVICE TEST");
        System.out.println("#####################################");
        System.out.println("\n[addProduct()]");
        System.out.println("Before add:" + shoppingCartService.getAllProducts(shoppingCart1));
        shoppingCartService.addProduct(shoppingCart1, product2);
        System.out.println("After add:" + shoppingCartService.getAllProducts(shoppingCart1));

        System.out.println("\n[deleteProduct()]");
        System.out.println("Before deleteProduct():"
                + shoppingCartService.getAllProducts(shoppingCart2));
        shoppingCartService.deleteProduct(shoppingCart2, product1);
        System.out.println("After deleteProduct():"
                + shoppingCartService.getAllProducts(shoppingCart2));

        System.out.println("\n[clear()]");
        System.out.println("Before clear():" + shoppingCartService.getAllProducts(shoppingCart3));
        shoppingCartService.clear(shoppingCart3);
        System.out.println("After clear():" + shoppingCartService.getAllProducts(shoppingCart3));

        System.out.println("\n[getByUserId()]");
        System.out.println(shoppingCartService.getByUserId(user3.getUserId()));

        System.out.println("\n#####################################");
        System.out.println("ORDER SERVICE TEST");
        System.out.println("#####################################");

        List<Product> listProduct1 =
                new ArrayList<>(Arrays.asList(new Product[]{product1}));
        System.out.println("\n[completeOrder()]");
        System.out.println(orderService.completeOrder(listProduct1, user1));

        System.out.println("\n[delete()]");
        System.out.println(orderService.delete(1L));
        System.out.println("Order storage: " + Storage.getOrders());

        System.out.println("\n[get()]");
        orderService.completeOrder(listProduct1, user1);
        System.out.println(orderService.get(2L));

        System.out.println("\n[getAll()]");
        System.out.println(orderService.getAll());

        System.out.println("\n[getUserOrders()]");
        System.out.println(orderService.getUserOrders(user1));
    }
}
