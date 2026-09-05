package vn.iotstar.controller.web;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.model.Product;
import vn.iotstar.service.ProductService;
import vn.iotstar.service.impl.ProductServiceImpl;
 
@WebServlet(urlPatterns = { "/product" })
public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService = new ProductServiceImpl();
    private static final int PAGE_SIZE = 6; 
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 0;
        String pageParam = req.getParameter("page");
        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 0;
            }
        }
 
        List<Product> productList = productService.getAll(page, PAGE_SIZE);
        int totalRecords = productService.count();
        int totalPages = (int) Math.ceil((double) totalRecords / PAGE_SIZE);
 
        req.setAttribute("productList", productList);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);
 
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/product.jsp");
        dispatcher.forward(req, resp);
    }
}
 
