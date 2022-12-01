package vn.poly.myapp.DTO;

public class GoogleDTO {
    private String stt;
    private String email;
    private  String hoTen;
    private  String diaChi;
    private  String soDienThoai;
    private  int gioiTinh;
    private  String TenNguoiNhanHang;
    private  String tenTinh;
    private  String tenHuyen;
    private  String tenXa;
    private  String tenDuong;

    public GoogleDTO(String stt, String email, String hoTen, String diaChi, String soDienThoai, int gioiTinh, String tenNguoiNhanHang, String tenTinh, String tenHuyen, String tenXa, String tenDuong) {
        this.stt = stt;
        this.email = email;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.gioiTinh = gioiTinh;
        TenNguoiNhanHang = tenNguoiNhanHang;
        this.tenTinh = tenTinh;
        this.tenHuyen = tenHuyen;
        this.tenXa = tenXa;
        this.tenDuong = tenDuong;
    }

    public GoogleDTO() {
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
