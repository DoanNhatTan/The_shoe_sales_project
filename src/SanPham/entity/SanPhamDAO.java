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
public class SanPhamDAO extends DAO<SanPham, String>{

    @Override
    public void insert(SanPham model) {
        String sql="INSERT INTO SanPham VALUES ([dbo].[AUTO_MaSP](), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcHelper.executeUpdate(sql, 
//        model.getMaSP(), 
        model.getTENSP(), 
        model.getGIA(),
        model.getSOLUONG(),
        model.getMOTA(),
        model.getHINH(),
        model.getHINH1(),
        model.getHINH2(),
        model.getHINH3(),
        model.getMaDM(),
        model.getSize());
    }

    @Override
    public void update(SanPham model) {
        String sql="UPDATE SanPham SET TENSP =?, GIA=?, SOLUONG=? ,MOTA=? , HINH=? , HINH1=?, HINH2=?, HINH3=?, MaDM = ?, Size = ? WHERE MASP = ?";
        jdbcHelper.executeUpdate(sql, 
        model.getTENSP(), 
        model.getGIA(),
        model.getSOLUONG(),
        model.getMOTA(),
        model.getHINH(),
        model.getHINH1(),
        model.getHINH2(),
        model.getHINH3(),
        model.getMaDM(),
        model.getSize());
    }

    @Override
    public void delete(String maSP) {
        String sql = "DELETE FROM SanPham WHERE MASP = ? ";
        jdbcHelper.executeUpdate(sql, maSP);
    }

    @Override
    public SanPham selectById(String maSP) {
        String sql = "SELECT * FROM SanPham WHERE MASP=?";
        List<SanPham> list = selectBySql(sql, maSP);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<SanPham> selectAll() {
        String sql = "SELECT * FROM SanPham";
        return this.selectBySql(sql);
    }

    @Override
    protected List<SanPham> selectBySql(String sql, Object... args) {
        List<SanPham> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while(rs.next()){
                    SanPham entity=new SanPham();
                    entity.setMASP(rs.getString("MASP"));
                    entity.setTENSP(rs.getString("TENSP"));
                    entity.setGIA(rs.getFloat("GIA"));
                    entity.setSOLUONG(rs.getInt("SOLUONG"));
                    entity.setMOTA(rs.getString("MOTA"));
                    entity.setHINH(rs.getString("HINH"));
                    entity.setHINH1(rs.getString("HINH1"));
                    entity.setHINH2(rs.getString("HINH2"));
                    entity.setHINH3(rs.getString("HINH3"));
                    entity.setMaDM(rs.getInt("MaDM"));
                    entity.setSize(rs.getString("Size"));
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
    
    
    public List<SanPham> selectSPByDanhMuc(int maDM){ //tìm kiếm sản phẩm theo danh mục sản phẩm
        String sql = "SELECT * FROM SanPham WHERE MaDM = ?";
        return this.selectBySql(sql, maDM);
    }
    
    public List<SanPham> selectByKeyword(String keyword){ //tìm kiếm sản phẩm theo từ khóa
        String sql = "SELECT * FROM SanPham WHERE TenSP LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%");
    }
    
}
