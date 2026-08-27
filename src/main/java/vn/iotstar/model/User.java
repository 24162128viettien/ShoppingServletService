package vn.iotstar.model;

import java.sql.Date;

public class User {
    private int id;
    private String email;
    private String username;
    private String fullname;
    private String password;
    private String phone;
    private int roleid;
    private Date createdDate;
    private String images;

    public User() {
    }

    public User(String email, String username, String fullname, String password, String phone, int roleid, Date createdDate, String images) {
        this.email = email;
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.phone = phone;
        this.roleid = roleid;
        this.createdDate = createdDate;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}