package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.ShoppingCartService;

@WebServlet("/shoppingCart/products")
public class GetShoppingCartController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, NoSuchElementException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        List<Product> productList = new ArrayList<>();
        productList = shoppingCartService.getByUserId(userId).getProducts();
        req.setAttribute("products", productList);
        req.getRequestDispatcher("/WEB-INF/views/shoppingCart.jsp").forward(req, resp);
    }
}
