package Models;

import java.util.Date;

public class Users {
    private int id;
    private String name;
    private Date birthday;
    private boolean gender;
    private String phone; // Đổi từ int sang String
    private String email;
    private String address;
    private String inname;
    private String password;
    private String role; // Thay isAdmin bằng role

    public Users() {
    }

    public Users(int id, String name, Date birthday, boolean gender, String phone, String email, String address, String inname, String password, String role) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.inname = inname;
        this.password = password;
        this.role = role;
    }

    // Getters và Setters hiện có
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getBirthday() { return birthday; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }

    public boolean isGender() { return gender; }
    public void setGender(boolean gender) { this.gender = gender; }

    public String getPhone() { return phone; } // Đổi từ getPhoneNumber thành getPhone
    public void setPhone(String phone) { this.phone = phone; } // Đổi từ int sang String

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getInname() { return inname; }
    public void setInname(String inname) { this.inname = inname; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; } // Thay isAdmin() bằng getRole()
    public void setRole(String role) { this.role = role; } // Thay setAdmin() bằng setRole()

    // Kiểm tra xem người dùng có phải admin không
    public boolean isAdmin() {
        return "Admin".equalsIgnoreCase(role);
    }

    @Override
    public String toString() {
        return id + "," + name + "," + birthday + ", " + gender + ", " + phone + ", " + email + ", " + address + ", " + inname + ", " + password + ", " + role;
    }
}