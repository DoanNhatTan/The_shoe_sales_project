/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SanPham.entity;

/**
 *
 * @author ASUS
 */
public class GioHang {
    String ID;
    String MAKH;
    String TENSP;
    int SOLUONG;
    float GIA;
    String SIZE;

    public GioHang() {
    }

    public GioHang(String ID, String MAKH, String TENSP, int SOLUONG, float GIA, String SIZE) {
        this.ID = ID;
        this.MAKH = MAKH;
        this.TENSP = TENSP;
        this.SOLUONG = SOLUONG;
        this.GIA = GIA;
        this.SIZE = SIZE;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMAKH() {
        return MAKH;
    }

    public void setMAKH(String MAKH) {
        this.MAKH = MAKH;
    }

    public String getTENSP() {
        return TENSP;
    }

    public void setTENSP(String TENSP) {
        this.TENSP = TENSP;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public float getGIA() {
        return GIA;
    }

    public void setGIA(float GIA) {
        this.GIA = GIA;
    }

    public String getSIZE() {
        return SIZE;
    }

    public void setSIZE(String SIZE) {
        this.SIZE = SIZE;
    }

    
    
}
