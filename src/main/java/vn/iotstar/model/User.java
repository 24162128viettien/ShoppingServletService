package vn.iotstar.model;
 
import java.io.Serializable;
import java.sql.Timestamp;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
 
@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
 
    @NotBlank(message = "Tên đăng nhập không được để trống")
    @Size(min = 4, max = 50, message = "Tên đăng nhập phải từ 4-50 ký tự")
    @Column(name = "username", columnDefinition = "NVARCHAR(50) NOT NULL")
    private String username;
 
    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 6, message = "Mật khẩu phải từ 6 ký tự trở lên")
    @Column(name = "password", columnDefinition = "NVARCHAR(100) NOT NULL")
    private String password;
 
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    @Column(name = "email", columnDefinition = "NVARCHAR(100) NOT NULL")
    private String email;
 
    @NotBlank(message = "Họ tên không được để trống")
    @Column(name = "fullname", columnDefinition = "NVARCHAR(100) NULL")
    private String fullname;
 
    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại phải đủ 10 số, bắt đầu bằng 0")
    @Column(name = "phone", columnDefinition = "NVARCHAR(20) NULL")
    private String phone;
 
    @Column(name = "roleid")
    private int roleid = 5;
 
    // Mới thêm: ảnh đại diện, dùng cho chức năng Profile
    @Column(name = "images", columnDefinition = "NVARCHAR(255) NULL")
    private String images;
 
    @Column(name = "active")
    private Integer active = 0; // Dùng Integer (không phải int) để tránh lỗi nếu cột NULL ở dữ liệu cũ
 
    @Column(name = "otp_code", columnDefinition = "NVARCHAR(10) NULL")
    private String otpCode;
 
    @Column(name = "otp_expiry")
    private Timestamp otpExpiry;
 
    public User() {
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getFullname() {
        return fullname;
    }
 
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
 
    public String getPhone() {
        return phone;
    }
 
    public void setPhone(String phone) {
        this.phone = phone;
    }
 
    public int getRoleId() {
        return roleid;
    }
 
    public void setRoleId(int roleid) {
        this.roleid = roleid;
    }
 
    public String getImages() {
        return images;
    }
 
    public void setImages(String images) {
        this.images = images;
    }
 
    public Integer getActive() {
        return active;
    }
 
    public void setActive(Integer active) {
        this.active = active;
    }
 
    public String getOtpCode() {
        return otpCode;
    }
 
    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }
 
    public Timestamp getOtpExpiry() {
        return otpExpiry;
    }
 
    public void setOtpExpiry(Timestamp otpExpiry) {
        this.otpExpiry = otpExpiry;
    }
}
 
