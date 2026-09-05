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
 
@WebServlet(urlPatterns = { "/forgot-password" })
public class ForgotPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl();
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/forgot-password.jsp");
        dispatcher.forward(req, resp);
    }
 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String usernameOrEmail = req.getParameter("usernameOrEmail");
 
        String realUsername = userService.sendResetPasswordOtp(usernameOrEmail);
 
        if (realUsername != null) {
            resp.sendRedirect(req.getContextPath() + "/reset-password?username=" + realUsername);
        } else {
            req.setAttribute("alert", "Không tìm thấy tài khoản hoặc email nào khớp.");
            req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
        }
    }
}
 
