/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLCC.model;

/**
 *
 * @author ADMIN
 */
public class NguoiDung {
    private String MaND;
    private String HoTen;
    private String MatKhau;
    private boolean GioiTinh ;
    private String DiaChi;
    private String Email;
    private String SDT;
    private boolean ChucVu;

    public NguoiDung(String MaND, String HoTen, String MatKhau, String DiaChi, String Email, String SDT, boolean ChucVu) {
        this.MaND = MaND;
        this.HoTen = HoTen;
        this.MatKhau = MatKhau;
      
        this.DiaChi = DiaChi;
        this.Email = Email;
        this.SDT = SDT;
        this.ChucVu = ChucVu;
    }

    public String getMaND() {
        return MaND;
    }

    public void setMaND(String MaND) {
        this.MaND = MaND;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public boolean isChucVu() {
        return ChucVu;
    }

    public void setChucVu(boolean ChucVu) {
        this.ChucVu = ChucVu;
    }
    public NguoiDung()
    {
        
    }
    
    
}
