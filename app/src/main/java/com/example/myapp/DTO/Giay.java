package com.example.myapp.DTO;

public class Giay {
    private int id;
    private String ten;
    private float gia;
    private byte[] hinh;
    private float size;

    public Giay(int id, String ten, float gia, byte[] hinh, float size) {
        this.id = id;
        this.ten = ten;
        this.gia = gia;
        this.hinh = hinh;
        this.size = size;
    }

    public Giay(){}

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

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float gia) {
        this.size = size;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }
}
