package vn.poly.myapp.DTO;

public class ThongTin {
    private  String maTK;
    private  String hoTen;
    private String email;
    private  String diaChi;
    private  String soDienThoai;
    private  int gioiTinh;
    private  String TenNguoiNhanHang;
    private  String tenTinh;
    private  String tenHuyen;
    private  String tenXa;
    private  String tenDuong;
    private String tenDangNhap;


    public ThongTin(String maTK, String hoTen, String email, String diaChi, String soDienThoai, int gioiTinh, String tenNguoiNhanHang, String tenTinh, String tenHuyen, String tenXa, String tenDuong, String tenDangNhap) {
        this.maTK = maTK;
        this.hoTen = hoTen;
        this.email = email;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.gioiTinh = gioiTinh;
        TenNguoiNhanHang = tenNguoiNhanHang;
        this.tenTinh = tenTinh;
        this.tenHuyen = tenHuyen;
        this.tenXa = tenXa;
        this.tenDuong = tenDuong;
        this.tenDangNhap = tenDangNhap;
    }

    public ThongTin() {
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getMaTK() {
        return maTK;
    }

    public void setMaTK(String maTK) {
        this.maTK = maTK;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getTenNguoiNhanHang() {
        return TenNguoiNhanHang;
    }

    public void setTenNguoiNhanHang(String tenNguoiNhanHang) {
        TenNguoiNhanHang = tenNguoiNhanHang;
    }

    public String getTenTinh() {
        return tenTinh;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }

    public String getTenHuyen() {
        return tenHuyen;
    }

    public void setTenHuyen(String tenHuyen) {
        this.tenHuyen = tenHuyen;
    }

    public String getTenXa() {
        return tenXa;
    }

    public void setTenXa(String tenXa) {
        this.tenXa = tenXa;
    }

    public String getTenDuong() {
        return tenDuong;
    }

    public void setTenDuong(String tenDuong) {
        this.tenDuong = tenDuong;
    }
}
