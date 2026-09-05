package vn.iotstar.controller.web;
 
import java.io.IOException;
 
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
import vn.iotstar.service.UserService;
import vn.iotstar.service.impl.UserServiceImpl;
 
@WebServlet(urlPatterns = { "/reset-password" })
public class ResetPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl();
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        req.setAttribute("username", username);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/reset-password.jsp");
        dispatcher.forward(req, resp);
    }
 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String otp = req.getParameter("otp");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");
 
        if (newPassword == null || newPassword.isEmpty() || !newPassword.equals(confirmPassword)) {
            req.setAttribute("username", username);
            req.setAttribute("alert", "Mật khẩu xác nhận không khớp.");
            req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
            return;
        }
 
        boolean success = userService.resetPassword(username, otp, newPassword);
 
        if (success) {
            resp.sendRedirect(req.getContextPath() + "/login?reset=1");
        } else {
            req.setAttribute("username", username);
            req.setAttribute("alert", "Mã OTP không đúng hoặc đã hết hạn. Vui lòng thử lại.");
            req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
        }
    }
}
 
