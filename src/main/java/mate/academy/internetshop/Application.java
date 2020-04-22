package mate.academy.internetshop;

import java.math.BigDecimal;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.ProductService;

public class Application {
    private static Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);

        initializeDb(productService);

        productService.getAll().forEach(System.out::println);
    }

    private static void initializeDb(ProductService productService) {
        Product product1 = new Product("HONDA 2020 CB650R", new BigDecimal(9199));
        Product product2 = new Product("HONDA 2020 REBEL 500", new BigDecimal(6199));
        Product product3 = new Product("HONDA 2020 CBR600RR", new BigDecimal(11799));

        productService.create(product1);
        productService.create(product2);
        productService.create(product3);

        productService.deleteByProduct(product1);
        productService.delete(product2.getProductId());

        Product productUpdateChecker = new Product("HONDA 2020 CBR600RR", new BigDecimal(19999));
        productUpdateChecker.setProductId(3L);

        productService.update(productUpdateChecker);
    }
}
