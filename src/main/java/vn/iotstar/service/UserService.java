package vn.iotstar.service;
import vn.iotstar.model.User;
public interface UserService {
    User login(String username, String password);
    User get(String username);
    User getById(int id);
    void insert(User user);
    boolean checkExistUsername(String username);
    boolean checkExistEmail(String email);
 
    boolean register(String email, String username, String fullname, String password, String phone);
 
    void updateProfile(User user);
}
 
















