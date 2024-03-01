/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLCC.model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class ChiTietHoaDon {
    private String MAHD;
    private String MaSP;
    private Date NgayBan;
    private int SoLuong;
    private String GhiChu;

    public String getMAHD() {
        return MAHD;
    }

    public void setMAHD(String MAHD) {
        this.MAHD = MAHD;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public Date getNgayBan() {
        return NgayBan;
    }

    public void setNgayBan(Date NgayBan) {
        this.NgayBan = NgayBan;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public ChiTietHoaDon(String MAHD, String MaSP, Date NgayBan, int SoLuong, String GhiChu) {
        this.MAHD = MAHD;
        this.MaSP = MaSP;
        this.NgayBan = NgayBan;
        this.SoLuong = SoLuong;
        this.GhiChu = GhiChu;
    }
    public ChiTietHoaDon()
    {
        
    }
}
