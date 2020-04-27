package mate.academy.internetshop.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.ShoppingCartService;

public class ShoppingCartController extends HttpServlet {
    public static final Long USER_ID = 1L;
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Product> productList = new ArrayList<>();

        try {
            productList = shoppingCartService.getByUserId(USER_ID).getProducts();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        req.setAttribute("products", productList);
        req.getRequestDispatcher("/WEB-INF/views/shoppingCarts/user1.jsp").forward(req, resp);
    }
}