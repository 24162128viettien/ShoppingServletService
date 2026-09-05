package vn.iotstar.controller.web;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.model.Product;
import vn.iotstar.service.ProductService;
import vn.iotstar.service.impl.ProductServiceImpl;
 
@WebServlet(urlPatterns = { "/product-detail" })
public class ProductDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService = new ProductServiceImpl();
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            resp.sendRedirect(req.getContextPath() + "/product");
            return;
        }
        Product product = productService.get(Integer.parseInt(id));
        req.setAttribute("product", product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/product-detail.jsp");
        dispatcher.forward(req, resp);
    }
}
 
