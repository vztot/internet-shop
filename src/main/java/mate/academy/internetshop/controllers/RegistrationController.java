package mate.academy.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

public class RegistrationController extends HttpServlet {

    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("userName");
        String login = req.getParameter("login");
        String pwd = req.getParameter("pwd");
        String pwdRepeat = req.getParameter("pwd-repeat");

        if (pwd.equals(pwdRepeat)) {
            userService.create(new User(name, login, pwd));
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("msg", "The password confirmation does not match.");
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
        }
    }
}
