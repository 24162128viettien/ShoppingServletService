package vn.iotstar.service.impl;
 
import java.io.File;
 
import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.model.User;
import vn.iotstar.service.UserService;
import vn.iotstar.util.Constant;
 
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
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setFullname(fullname);
        user.setPassword(password);
        user.setPhone(phone);
        user.setRoleId(5); // Mặc định tài khoản thường
        user.setActive(1); // Tạm thời kích hoạt sẵn (chưa nối luồng OTP kích hoạt trong bước này)
        userDao.insert(user);
        return true;
    }
 
    @Override
    public void updateProfile(User newInfo) {
        // Lấy lại bản ghi gốc từ DB, chỉ cho phép sửa fullname/phone/images
        // -> tránh trường hợp form bị chỉnh sửa để đổi username/roleid/password trái phép
        User oldUser = userDao.getById(newInfo.getId());
        if (oldUser == null) {
            return;
        }
        oldUser.setFullname(newInfo.getFullname());
        oldUser.setPhone(newInfo.getPhone());
 
        if (newInfo.getImages() != null && !newInfo.getImages().equals(oldUser.getImages())) {
            // Xóa ảnh đại diện cũ (nếu có) trước khi lưu ảnh mới
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
 