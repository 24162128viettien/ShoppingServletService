package vn.iotstar.controller.admin;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.model.Category;
import vn.iotstar.service.CategoryService;
import vn.iotstar.service.impl.CategoryServiceImpl;
@WebServlet(urlPatterns = { "/admin/category/list" })
public class CategoryListController extends HttpServlet {
    CategoryService cateService = new CategoryServiceImpl();
    private static final int PAGE_SIZE = 5; // Số danh mục hiển thị mỗi trang
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Mới thêm: đọc số trang từ URL (?page=0, ?page=1...), mặc định trang 0
        int page = 0;
        String pageParam = req.getParameter("page");
        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 0;
            }
        }
 
        List<Category> cateList = cateService.getAll(page, PAGE_SIZE);
        int totalRecords = cateService.count();
        int totalPages = (int) Math.ceil((double) totalRecords / PAGE_SIZE);
 
        req.setAttribute("cateList", cateList);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);
 
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/list-category.jsp");
        dispatcher.forward(req, resp);
    }
}
 
