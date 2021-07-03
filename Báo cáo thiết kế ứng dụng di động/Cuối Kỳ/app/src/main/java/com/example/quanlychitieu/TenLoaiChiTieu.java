package com.example.quanlychitieu;

public class TenLoaiChiTieu {
    private String name;
    private int id;
    private int idLoaiCT;

    public TenLoaiChiTieu(String name, int id, int idLoaiCT) {
        this.name = name;
        this.id = id;
        this.idLoaiCT = idLoaiCT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLoaiCT() {
        return idLoaiCT;
    }

    public void setIdLoaiCT(int idLoaiCT) {
        this.idLoaiCT = idLoaiCT;
    }

    @Override
    public String toString() {
        return "TenLoaiChiTieu{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", idLoaiCT=" + idLoaiCT +
                '}';
    }

    public TenLoaiChiTieu() {
    }
}
