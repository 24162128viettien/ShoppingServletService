package vn.iotstar.dao;
import vn.iotstar.model.User;
public interface UserDao {
    User get(String username);
    User getById(int id);
    User getByUsernameOrEmail(String input);
    void insert(User user);
    void update(User user); // Dùng chung cho cả Profile và các cập nhật khác
    boolean checkExistUsername(String username);
    boolean checkExistEmail(String email);
 
    // Dùng cho tính năng OTP (đã chuẩn bị sẵn, chưa dùng ngay trong bước Profile này)
    void saveOtp(String username, String otp, java.sql.Timestamp expiry);
    void activateAccount(String username);
    void updatePassword(String username, String newPassword);
    void clearOtp(String username);
}
 
