package vn.iotstar.controller.admin;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;
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
import vn.iotstar.model.Product;
import vn.iotstar.service.CategoryService;
import vn.iotstar.service.ProductService;
import vn.iotstar.service.impl.CategoryServiceImpl;
import vn.iotstar.service.impl.ProductServiceImpl;
import vn.iotstar.util.Constant;
 
@WebServlet(urlPatterns = { "/admin/product/add" })
public class ProductAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService = new ProductServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> cateList = categoryService.getAll();
        req.setAttribute("cateList", cateList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/add-product.jsp");
        dispatcher.forward(req, resp);
    }
 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        product.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        try {
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");
 
            DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
            JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
            List<FileItem> items = upload.parseRequest(req);
 
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String field = item.getFieldName();
                    String value = item.getString(StandardCharsets.UTF_8);
                    if (field.equals("name")) {
                        product.setName(value);
                    } else if (field.equals("price")) {
                        product.setPrice(Double.parseDouble(value));
                    } else if (field.equals("quantity")) {
                        product.setQuantity(Integer.parseInt(value));
                    } else if (field.equals("description")) {
                        product.setDescription(value);
                    } else if (field.equals("categoryId")) {
                        Category category = categoryService.get(Integer.parseInt(value));
                        product.setCategory(category);
                    }
                } else {
                    if (item.getFieldName().equals("image") && item.getSize() > 0) {
                        String originalFileName = item.getName();
                        int index = originalFileName.lastIndexOf(".");
                        String ext = originalFileName.substring(index + 1);
                        String fileName = System.currentTimeMillis() + "." + ext;
                        File file = new File(Constant.DIR + "/product/" + fileName);
                        file.getParentFile().mkdirs();
                        item.write(file.toPath());
                        product.setImage("product/" + fileName);
                    }
                }
            }
 
            productService.insert(product);
            resp.sendRedirect(req.getContextPath() + "/admin/product/list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
 
