package vn.iotstar.controller.admin;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;
import vn.iotstar.model.Category;
import vn.iotstar.service.CategoryService;
import vn.iotstar.service.impl.CategoryServiceImpl;
import vn.iotstar.util.Constant;
import vn.iotstar.util.ValidationUtil;
@WebServlet(urlPatterns = { "/admin/category/edit" })
public class CategoryEditController extends HttpServlet {
    CategoryService cateService = new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Category category = cateService.get(Integer.parseInt(id));
        req.setAttribute("category", category);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/edit-category.jsp");
        dispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category category = new Category();
        try {
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");
            DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
            JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
            List<FileItem> items = upload.parseRequest(req);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    if (item.getFieldName().equals("id")) {
                        category.setId(Integer.parseInt(item.getString(StandardCharsets.UTF_8)));
                    } else if (item.getFieldName().equals("name")) {
                        category.setName(item.getString(StandardCharsets.UTF_8));
                    } else if (item.getFieldName().equals("status")) {
                        category.setStatus(Integer.parseInt(item.getString(StandardCharsets.UTF_8)));
                    }
                } else {
                    if (item.getFieldName().equals("icon")) {
                        if (item.getSize() > 0) {
                            String originalFileName = item.getName();
                            int index = originalFileName.lastIndexOf(".");
                            String ext = originalFileName.substring(index + 1);
                            String fileName = System.currentTimeMillis() + "." + ext;
                            File file = new File(Constant.DIR + "/category/" + fileName);
                            item.write(file.toPath());
                            category.setIcon("category/" + fileName);
                        } else {
                            category.setIcon(null);
                        }
                    }
                }
            }
 
            // Mới thêm: validate bằng Bean Validation trước khi lưu
            Map<String, String> errors = ValidationUtil.validate(category);
            if (!errors.isEmpty()) {
                req.setAttribute("errors", errors);
                req.setAttribute("category", category);
                req.getRequestDispatcher("/views/admin/edit-category.jsp").forward(req, resp);
                return;
            }
 
            cateService.edit(category);
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
 
