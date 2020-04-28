package mate.academy.internetshop.controllers;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.ShoppingCartService;

@WebServlet("/shoppingCarts/add")
public class AddProductToShoppingCartController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String productId = req.getParameter("product_id");
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(ShoppingCartController.USER_ID);
        shoppingCartService.addProduct(shoppingCart, productService.get(Long.parseLong(productId)));
        resp.sendRedirect(req.getContextPath() + "/products/all");
    }
}