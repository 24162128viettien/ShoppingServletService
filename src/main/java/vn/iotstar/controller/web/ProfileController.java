package vn.iotstar.controller.web;
 
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Pattern;
 
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
 
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;
 
import vn.iotstar.model.User;
import vn.iotstar.service.UserService;
import vn.iotstar.service.impl.UserServiceImpl;
import vn.iotstar.util.Constant;
 
@WebServlet(urlPatterns = { "/profile" })
public class ProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl();
 
    // Số điện thoại VN: bắt đầu bằng 0, đủ 10 số
    private static final Pattern PHONE_PATTERN = Pattern.compile("^0\\d{9}$");
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        User account = (User) session.getAttribute("account");
        // Lấy lại bản ghi mới nhất từ DB (phòng khi đã cập nhật ở thiết bị khác)
        User user = userService.getById(account.getId());
        req.setAttribute("user", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/profile.jsp");
        dispatcher.forward(req, resp);
    }
 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        User account = (User) session.getAttribute("account");
 
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
 
        User newInfo = new User();
        newInfo.setId(account.getId());
        String fullname = null;
        String phone = null;
        String newImageFileName = null;
 
        try {
            DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
            JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
            List<FileItem> items = upload.parseRequest(req);
 
            for (FileItem item : items) {
                if (item.isFormField()) {
                    if (item.getFieldName().equals("fullname")) {
                        fullname = item.getString(StandardCharsets.UTF_8);
                    } else if (item.getFieldName().equals("phone")) {
                        phone = item.getString(StandardCharsets.UTF_8);
                    }
                } else {
                    if (item.getFieldName().equals("images") && item.getSize() > 0) {
                        String originalFileName = item.getName();
                        int index = originalFileName.lastIndexOf(".");
                        String ext = originalFileName.substring(index + 1);
                        String fileName = "avatar_" + System.currentTimeMillis() + "." + ext;
                        File file = new File(Constant.DIR + "/avatar/" + fileName);
                        file.getParentFile().mkdirs(); // Tạo thư mục avatar nếu chưa có
                        item.write(file.toPath());
                        newImageFileName = "avatar/" + fileName;
                    }
                }
            }
 
            // ===== Validation =====
            if (fullname == null || fullname.trim().isEmpty()) {
                req.setAttribute("error", "Họ tên không được để trống");
                req.setAttribute("user", account);
                req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
                return;
            }
            if (phone == null || phone.trim().isEmpty() || !PHONE_PATTERN.matcher(phone.trim()).matches()) {
                req.setAttribute("error", "Số điện thoại không hợp lệ (phải đủ 10 số, bắt đầu bằng 0)");
                req.setAttribute("user", account);
                req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
                return;
            }
 
            newInfo.setFullname(fullname.trim());
            newInfo.setPhone(phone.trim());
            newInfo.setImages(newImageFileName); // null nếu không upload ảnh mới -> service tự giữ ảnh cũ
 
            userService.updateProfile(newInfo);
 
            // Cập nhật lại session để navbar/thông tin hiển thị đúng ngay lập tức
            User updated = userService.getById(account.getId());
            session.setAttribute("account", updated);
 
            req.setAttribute("success", "Cập nhật thông tin thành công!");
            req.setAttribute("user", updated);
            req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
 
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Có lỗi xảy ra, vui lòng thử lại");
            req.setAttribute("user", account);
            req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
        }
    }
}
 
