package mate.academy.internetshop.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.ProductService;

@WebServlet("/products/add")
public class AddProductController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/products/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String productName = req.getParameter("productName");
        String price = req.getParameter("price");

        if (productName != null && price != null
                && productName.length() > 0 && price.length() > 0 && price.matches("[0-9]+")) {
            productService.create(new Product(productName, new BigDecimal(Long.parseLong(price))));
            resp.sendRedirect(req.getContextPath() + "/products/add");
        } else {
            req.setAttribute("msg", "Please enter valid product data.");
            req.getRequestDispatcher("/WEB-INF/views/products/add.jsp").forward(req, resp);
        }
    }
}
