package com.example.myapp.DTO;

public class TaiKhoan {
    private String tenDangNhap;
    private String hoTen;

    private String email;
    private String matKhau;

    public TaiKhoan(String tenDangNhap, String hoTen, String email, String matKhau) {
        this.tenDangNhap = tenDangNhap;
        this.hoTen = hoTen;
        this.email = email;
        this.matKhau = matKhau;
    }

    public TaiKhoan() {
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
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

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
