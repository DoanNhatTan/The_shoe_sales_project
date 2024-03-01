/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import HELPER.jdbcHelper;
import QLCC.model.NguoiDung;
import QLCC.model.SanPham;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class NguoiDungDAO extends DAO<NguoiDung, String>{
    String INSERT_SQL = "INSERT INTO NGUOIDUNG VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE NGUOIDUNG SET HOTEN = ?, MATKHAU = ?, GIOITINH = ?, DIACHI = ?, EMAIL = ?, SDT = ?, ChucVu = ? WHERE MaND = ?";
    String DELETE_SQL = "DELETE FROM NGUOIDUNG WHERE MaND = ?";
    String SELECT_ALL_SQL = "SELECT * FROM NGUOIDUNG";
    String SELECT_BY_ID_SQL = "SELECT * FROM NGUOIDUNG WHERE MaND = ?"; 

    @Override
    public void insert(NguoiDung entity)
    {
         try{
             jdbcHelper.executeUpdate(INSERT_SQL, entity.getMaND(), entity.getHoTen(), entity.getMatKhau(), entity.isGioiTinh(), entity.getDiaChi(),
                   entity.getEmail(), entity.getSDT(), entity.isChucVu());
       }catch(Exception ex){
            Logger.getLogger(NguoiDungDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @Override
    public void update(NguoiDung entity) {
       try {
            jdbcHelper.executeUpdate(UPDATE_SQL, entity.getHoTen(), entity.getMatKhau(),entity.isGioiTinh(),entity.getDiaChi(),entity.getEmail(),entity.getSDT(),entity.isChucVu(),
                      entity.getMaND());
        } catch (Exception ex) {
            Logger.getLogger(NguoiDungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String id) {
        try {
            jdbcHelper.executeUpdate(DELETE_SQL, id);
        } catch (Exception ex) {
            Logger.getLogger(NguoiDungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public NguoiDung selectById(String id) {
       List<NguoiDung> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    
    @Override
    public List<NguoiDung> selectAll() {
       return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<NguoiDung> selectBySql(String sql, Object... args) {
         List<NguoiDung> list = new ArrayList<NguoiDung>();
        try{
            ResultSet rs = jdbcHelper.executeQuery(sql, args);
            while(rs.next()){
                NguoiDung entity = new NguoiDung();
                entity.setMaND(rs.getString("MAND"));
                entity.setHoTen(rs.getString("HOTEN"));
                entity.setMatKhau(rs.getString("MATKHAU"));
                entity.setGioiTinh(rs.getBoolean("GIOITINH"));
                entity.setDiaChi(rs.getString("DIACHI"));
                entity.setEmail(rs.getString("EMAIL"));
                entity.setSDT(rs.getString("SDT"));
                entity.setChucVu(rs.getBoolean("ChucVu"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
     public List<NguoiDung> selectByKeyword(String keyword){
        String SELECT_BY_NAME_SQL = "SELECT * FROM nguoidung WHERE Mand LIKE ?";
        return this.selectBySql(SELECT_BY_NAME_SQL, "%" + keyword + "%");
    }
}
