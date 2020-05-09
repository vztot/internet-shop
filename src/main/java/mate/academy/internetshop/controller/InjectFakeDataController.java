package mate.academy.internetshop.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.UserService;

@WebServlet("/injectFakeData")
public class InjectFakeDataController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);
    private ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        if (req.getParameter("inject").equals("true")) {
            userService.create(
                    new User("Neil Armstrong", "moon69", "neillovesnasa",
                            Set.of(Role.of("ADMIN"))));
            userService.create(
                    new User("Leroy Jenkins", "leroooooooy", "jeeeenkins!",
                            Set.of(Role.of("USER"))));
            userService.create(
                    new User("Barbara Brilska", "barba12", "654654",
                            Set.of(Role.of("USER"))));
            userService.create(
                    new User("John Bobkins", "jobo17", "3216409jobojododojo",
                            Set.of(Role.of("USER"))));
            userService.create(
                    new User("Alex", "herastic1999", "supermegadominator4k",
                            Set.of(Role.of("USER"))));
            userService.create(
                    new User("Mary", "marrrrrrrrryyyyyyyyaaa", "kittykitty",
                            Set.of(Role.of("USER"))));

            productService.create(new Product("Kosmos-3M", new BigDecimal(150_000_000)));
            productService.create(new Product("Falcon Heavy", new BigDecimal(350_000_000)));
            productService.create(new Product("Buran", new BigDecimal(75_000_000)));
            productService.create(new Product("Soyuz-2", new BigDecimal(80_000_000)));
            productService.create(new Product("Proton-K", new BigDecimal(55_000_000)));
            productService.create(new Product("Energia", new BigDecimal(600_000_000)));
            productService.create(new Product("Ares V", new BigDecimal(250_000_000)));
            productService.create(new Product("Saturn V", new BigDecimal(355_000_000)));
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
