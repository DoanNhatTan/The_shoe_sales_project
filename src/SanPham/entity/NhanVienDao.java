/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SanPham.entity;

import NhanVienDAO.DAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import until.jdbcHelper;

/**
 *
 * @author asus
 */
public class NhanVienDao extends DAO<NhanVien, String>{

    @Override
    public void insert(NhanVien model) {
        String sql="INSERT INTO NhanVien VALUES ([dbo].[AUTO_MaNV](), ?, ?, ?, ?, ?, ?, ?)";
        jdbcHelper.executeUpdate(sql, 
//        model.getMaNV(), 
        model.getTenNV(), 
        model.isGioiTinh(),
        model.getDiaChi(),
        model.getSdt(),
        model.getNgaySinh(),
        model.getMatKhau(),
        model.isVaiTro()); 
    }

    @Override
    public void update(NhanVien model) {
        String sql="UPDATE NhanVien SET TenNV =?, GioiTinh=? ,DiaChi=? ,DienThoai=? ,NgaySinh=? , MatKhau=? , VaiTro=? WHERE MaNV = ?";
        jdbcHelper.executeUpdate(sql, 
        model.getTenNV(), 
        model.isGioiTinh(),
        model.getDiaChi(),
        model.getSdt(),
        model.getNgaySinh(),
        model.getMatKhau(),
        model.isVaiTro(),
        model.getMaNV());
    }

    @Override
    public void delete(String maNV) {
        String sql = "DELETE FROM NhanVien WHERE MaNV = ? ";
        jdbcHelper.executeUpdate(sql, maNV);
    }

    @Override
    public NhanVien selectById(String maNV) {
        String sql = "SELECT * FROM NhanVien WHERE MaNV=?";
        List<NhanVien> list = selectBySql(sql, maNV);
        return list.size() > 0 ? list.get(0) : null;        
    }

    @Override
    public List<NhanVien> selectAll() {
        String sql = "SELECT * FROM NhanVien";
        return this.selectBySql(sql);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while(rs.next()){
                    NhanVien entity=new NhanVien();
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setTenNV(rs.getString("TenNV"));
                    entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                    entity.setDiaChi(rs.getString("DiaChi"));
                    entity.setSdt(rs.getString("DienThoai"));
                    entity.setNgaySinh(rs.getDate("NgaySinh"));
                    entity.setMatKhau(rs.getString("MatKhau"));
                    entity.setVaiTro(rs.getBoolean("VaiTro"));
                    list.add(entity);
                }
            }
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;  
    }
    
    public void qmk(NhanVien model) {
        String sql="UPDATE NhanVien SET MatKhau=? WHERE MaNV=?";
        jdbcHelper.executeUpdate(sql, 
        model.getMatKhau(), 
        model.getMaNV());  
    }
}
