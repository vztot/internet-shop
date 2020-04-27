package mate.academy.internetshop.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.ShoppingCartService;
import mate.academy.internetshop.service.UserService;

@WebServlet("/shoppingCarts/add")
public class AddToShoppingCart extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private UserService userService = (UserService) injector.getInstance(UserService.class);
    private ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String productId = req.getParameter("product_id");

        ShoppingCart shoppingCart = null;

        try {
            shoppingCart = shoppingCartService.getByUserId(ShoppingCartController.USER_ID);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        if (shoppingCart == null) {
            User user = new User("ProductUser", "login", "pass");
            userService.create(user);
            user.setUserId(ShoppingCartController.USER_ID);
            shoppingCart =
                    shoppingCartService.create(new ShoppingCart(user, new ArrayList<Product>()));
        }
        shoppingCartService.addProduct(shoppingCart, productService.get(Long.parseLong(productId)));
        resp.sendRedirect(req.getContextPath() + "/products/all");
    }
}
