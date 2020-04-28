package mate.academy.internetshop.controllers;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.ShoppingCartService;
import mate.academy.internetshop.service.UserService;

@WebServlet("/orders/new")
public class AddOrderController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private OrderService orderService = (OrderService) INJECTOR.getInstance(OrderService.class);
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String userId = req.getParameter("user_id");

        ShoppingCart shoppingCart = shoppingCartService.getByUserId(ShoppingCartController.USER_ID);
        User user = userService.get(Long.parseLong(userId));
        orderService.completeOrder(shoppingCart.getProducts(), user);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
