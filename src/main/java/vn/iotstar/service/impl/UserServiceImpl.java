package vn.iotstar.service.impl;
 
import java.io.File;
import java.sql.Timestamp;
 
import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.model.User;
import vn.iotstar.service.UserService;
import vn.iotstar.util.Constant;
import vn.iotstar.util.MailUtil;
import vn.iotstar.util.OtpUtil;
 
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
 
    @Override
    public User login(String username, String password) {
        User user = userDao.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
 
    @Override
    public User get(String username) {
        return userDao.get(username);
    }
 
    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }
 
    @Override
    public void insert(User user) {
        userDao.insert(user);
    }
 
    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }
 
    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }
 
    @Override
    public boolean register(String email, String username, String fullname, String password, String phone) {
        if (userDao.checkExistUsername(username)) {
            return false;
        }
        String otp = OtpUtil.generateOtp();
        Timestamp expiry = new Timestamp(System.currentTimeMillis() + 5 * 60 * 1000); 
 
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setFullname(fullname);
        user.setPassword(password);
        user.setPhone(phone);
        user.setRoleId(5); 
        user.setActive(0); 
        user.setOtpCode(otp);
        user.setOtpExpiry(expiry);
        userDao.insert(user);
 
        MailUtil.sendOtpEmail(email, otp, "activate");
        return true;
    }
 
    @Override
    public boolean verifyOtp(String username, String otp) {
        User user = userDao.get(username);
        if (user == null || otp == null) {
            return false;
        }
        if (user.getOtpCode() == null || user.getOtpExpiry() == null) {
            return false; 
        }
        if (!user.getOtpCode().equals(otp)) {
            return false; 
        }
        if (user.getOtpExpiry().before(new Timestamp(System.currentTimeMillis()))) {
            return false; 
        }
        userDao.activateAccount(username);
        return true;
    }
 
    @Override
    public String sendResetPasswordOtp(String usernameOrEmail) {
        User user = userDao.getByUsernameOrEmail(usernameOrEmail);
        if (user == null) {
            return null; 
        }
        String otp = OtpUtil.generateOtp();
        Timestamp expiry = new Timestamp(System.currentTimeMillis() + 5 * 60 * 1000); 
        userDao.saveOtp(user.getUsername(), otp, expiry);
        MailUtil.sendOtpEmail(user.getEmail(), otp, "reset");
        return user.getUsername();
    }
 
    @Override
    public boolean resetPassword(String username, String otp, String newPassword) {
        User user = userDao.get(username);
        if (user == null || otp == null) {
            return false;
        }
        if (user.getOtpCode() == null || user.getOtpExpiry() == null) {
            return false; 
        }
        if (!user.getOtpCode().equals(otp)) {
            return false; 
        }
        if (user.getOtpExpiry().before(new Timestamp(System.currentTimeMillis()))) {
            return false; 
        }
        userDao.updatePassword(username, newPassword); 
        return true;
    }
 
    @Override
    public void updateProfile(User newInfo) {
        User oldUser = userDao.getById(newInfo.getId());
        if (oldUser == null) {
            return;
        }
        oldUser.setFullname(newInfo.getFullname());
        oldUser.setPhone(newInfo.getPhone());
 
        if (newInfo.getImages() != null && !newInfo.getImages().equals(oldUser.getImages())) {
            String oldImage = oldUser.getImages();
            if (oldImage != null) {
                File file = new File(Constant.DIR + "/" + oldImage);
                if (file.exists()) {
                    file.delete();
                }
            }
            oldUser.setImages(newInfo.getImages());
        }
 
        userDao.update(oldUser);
    }
}
 
