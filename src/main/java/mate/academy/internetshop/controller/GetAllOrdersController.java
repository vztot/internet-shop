package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

@WebServlet("/orders/all")
public class GetAllOrdersController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private OrderService orderService = (OrderService) INJECTOR.getInstance(OrderService.class);
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        List<Order> orderList = orderService.getUserOrders(userService.get(userId));
        req.setAttribute("orders", orderList);
        req.getRequestDispatcher("/WEB-INF/views/orders/all.jsp").forward(req, resp);
    }
}
