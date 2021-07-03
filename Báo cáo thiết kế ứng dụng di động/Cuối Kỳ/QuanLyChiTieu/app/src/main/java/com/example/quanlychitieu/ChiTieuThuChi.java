package com.example.quanlychitieu;

import java.util.Date;

public class ChiTieuThuChi {
    private String TenLoaiChiTieu;
    private int idTenChiTieuChiTiet;
    private String TenChiTieu;
    private Double soTien;
    private String date;
    private int idLoaiCT;

    public ChiTieuThuChi(String tenLoaiChiTieu, int idTenChiTieuChiTiet, String tenChiTieu, Double soTien, String date, int idLoaiCT) {
        TenLoaiChiTieu = tenLoaiChiTieu;
        this.idTenChiTieuChiTiet = idTenChiTieuChiTiet;
        TenChiTieu = tenChiTieu;
        this.soTien = soTien;
        this.date = date;
        this.idLoaiCT = idLoaiCT;
    }

    public ChiTieuThuChi() {
    }

    @Override
    public String toString() {
        return "ChiTieuThuChi{" +
                "TenLoaiChiTieu='" + TenLoaiChiTieu + '\'' +
                ", idTenChiTieuChiTiet=" + idTenChiTieuChiTiet +
                ", TenChiTieu='" + TenChiTieu + '\'' +
                ", soTien=" + soTien +
                ", date='" + date + '\'' +
                ", idLoaiCT=" + idLoaiCT +
                '}';
    }

    public String getTenLoaiChiTieu() {
        return TenLoaiChiTieu;
    }

    public void setTenLoaiChiTieu(String tenLoaiChiTieu) {
        TenLoaiChiTieu = tenLoaiChiTieu;
    }

    public int getIdTenChiTieuChiTiet() {
        return idTenChiTieuChiTiet;
    }

    public void setIdTenChiTieuChiTiet(int idTenChiTieuChiTiet) {
        this.idTenChiTieuChiTiet = idTenChiTieuChiTiet;
    }

    public String getTenChiTieu() {
        return TenChiTieu;
    }

    public void setTenChiTieu(String tenChiTieu) {
        TenChiTieu = tenChiTieu;
    }

    public Double getSoTien() {
        return soTien;
    }

    public void setSoTien(Double soTien) {
        this.soTien = soTien;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdLoaiCT() {
        return idLoaiCT;
    }

    public void setIdLoaiCT(int idLoaiCT) {
        this.idLoaiCT = idLoaiCT;
    }
}
