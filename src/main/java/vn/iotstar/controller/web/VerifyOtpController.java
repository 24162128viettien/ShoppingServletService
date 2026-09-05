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
 
@WebServlet(urlPatterns = { "/verify-otp" })
public class VerifyOtpController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl();
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        req.setAttribute("username", username);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/verify-otp.jsp");
        dispatcher.forward(req, resp);
    }
 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String otp = req.getParameter("otp");
 
        boolean success = userService.verifyOtp(username, otp);
 
        if (success) {
            resp.sendRedirect(req.getContextPath() + "/login?activated=1");
        } else {
            req.setAttribute("username", username);
            req.setAttribute("alert", "Mã OTP không đúng hoặc đã hết hạn. Vui lòng thử lại.");
            req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
        }
    }
}
 
