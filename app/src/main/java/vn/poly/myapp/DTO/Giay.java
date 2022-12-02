package vn.poly.myapp.DTO;

public class Giay {
    private int id;
    private String ten;
    private String gia;
    private byte[] hinh;
    private float size;

    public Giay(int id, String ten, String gia, byte[] hinh, float size) {
        this.id = id;
        this.ten = ten;
        this.gia = gia;
        this.hinh = hinh;
        this.size = size;
    }

    public Giay() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }
}
