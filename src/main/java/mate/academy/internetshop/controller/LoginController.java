package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mate.academy.internetshop.exception.AuthenticationException;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.security.AuthenticationService;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private AuthenticationService authService =
            (AuthenticationService) INJECTOR.getInstance(AuthenticationService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String pwd = req.getParameter("pwd");
        try {
            User user = authService.login(login, pwd);
            HttpSession session = req.getSession();
            session.setAttribute("user_id", user.getUserId());
            session.setAttribute("user_name", user.getName());
            session.setAttribute("is_admin", user.getRoles().stream()
                    .map(role -> role.getRoleName())
                    .collect(Collectors.toList())
                    .contains(Role.RoleName.ADMIN)
                    ? 1 : 0);

        } catch (AuthenticationException e) {
            req.setAttribute("errorMsg", e.getMessage());
            req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
