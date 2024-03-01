/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SanPham.entity;

/**
 *
 * @author ASUS
 */
public class SanPham {
    private String MASP;
    private String TENSP;
    private double GIA;
    private int SOLUONG;
    private String MOTA;
    private String HINH;
    private String HINH1;
    private String HINH2;
    private String HINH3;
    private int maDM;
    private String size;

    public SanPham() {
    }

    public SanPham(String MASP, String TENSP, double GIA, int SOLUONG, String MOTA, String HINH, String HINH1, String HINH2, String HINH3, int maDM, String size) {
        this.MASP = MASP;
        this.TENSP = TENSP;
        this.GIA = GIA;
        this.SOLUONG = SOLUONG;
        this.MOTA = MOTA;
        this.HINH = HINH;
        this.HINH1 = HINH1;
        this.HINH2 = HINH2;
        this.HINH3 = HINH3;
        this.maDM = maDM;
        this.size = size;
    }

    public String getMASP() {
        return MASP;
    }

    public void setMASP(String MASP) {
        this.MASP = MASP;
    }

    public String getTENSP() {
        return TENSP;
    }

    public void setTENSP(String TENSP) {
        this.TENSP = TENSP;
    }

    public double getGIA() {
        return GIA;
    }

    public void setGIA(double GIA) {
        this.GIA = GIA;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public String getMOTA() {
        return MOTA;
    }

    public void setMOTA(String MOTA) {
        this.MOTA = MOTA;
    }

    public String getHINH() {
        return HINH;
    }

    public void setHINH(String HINH) {
        this.HINH = HINH;
    }

    public String getHINH1() {
        return HINH1;
    }

    public void setHINH1(String HINH1) {
        this.HINH1 = HINH1;
    }

    public String getHINH2() {
        return HINH2;
    }

    public void setHINH2(String HINH2) {
        this.HINH2 = HINH2;
    }

    public String getHINH3() {
        return HINH3;
    }

    public void setHINH3(String HINH3) {
        this.HINH3 = HINH3;
    }

    public int getMaDM() {
        return maDM;
    }

    public void setMaDM(int maDM) {
        this.maDM = maDM;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    
    
    
}
