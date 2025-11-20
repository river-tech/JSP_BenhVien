package model;

public class Admin {
    private String username;
    private String password;
    private String hoTen;
    private String email;

    public Admin() {
    }

    public Admin(String username, String password, String hoTen, String email) {
        this.username = username;
        this.password = password;
        this.hoTen = hoTen;
        this.email = email;
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

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

