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
public class HoaDon 
{
    private String MaHD;
    private String MaND;
    private Date NgayBan;
    private String MaKH;
    private double TongTien;

    public HoaDon() {
    }
    
    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getMaND() {
        return MaND;
    }

    public void setMaND(String MaND) {
        this.MaND = MaND;
    }

    public Date getNgayBan() {
        return NgayBan;
    }

    public void setNgayBan(Date NgayBan) {
        this.NgayBan = NgayBan;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    public HoaDon(String MaHD, String MaND, Date NgayBan, String MaKH, double TongTien) {
        this.MaHD = MaHD;
        this.MaND = MaND;
        this.NgayBan = NgayBan;
        this.MaKH = MaKH;
        this.TongTien = TongTien;
    }
    
    
}
