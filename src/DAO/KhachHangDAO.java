/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import HELPER.jdbcHelper;

import QLCC.model.KhachHang;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class KhachHangDAO extends DAO<KhachHang,String>
{
    String INSERT_SQL = "INSERT INTO KHACHHANG(MAKH, HOTEN, GIOITINH, DIACHI, SDT, Email,Hinh)" +
                     "VALUES(?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE KHACHHANG SET HOTEN = ?, GIOITINH = ?, DIACHI = ?, SDT = ?,Email=? ,Hinh=? WHERE MAKH = ?";
    String DELETE_SQL = "DELETE FROM KHACHHANG WHERE MAKH = ?";
    String SELECT_ALL_SQL = "SELECT * FROM KHACHHANG";
    String SELECT_BY_ID_SQL = "SELECT * FROM KHACHHANG WHERE MAKH = ?";

    @Override
    public void insert(KhachHang entity) {
        try {
            jdbcHelper.executeUpdate(INSERT_SQL, entity.getMaKH(), entity.getHoTen(), entity.isGioiTinh(), entity.getDiaChi(), entity.getSDT(),entity.getEmail());
        } catch (Exception ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(KhachHang entity) {
       try {
            jdbcHelper.executeUpdate(UPDATE_SQL, entity.getHoTen(), entity.isGioiTinh(), entity.getDiaChi(), entity.getSDT(),entity.getEmail(),entity.getHinh() ,entity.getMaKH());
        } catch (Exception ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String id) {
       try {
            jdbcHelper.executeUpdate(DELETE_SQL, id);
        } catch (Exception ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public KhachHang selectById(String id) {
       List<KhachHang> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhachHang> selectAll() {
       return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<KhachHang> selectBySql(String sql, Object... args) {
       List<KhachHang> list = new ArrayList<KhachHang>();
        try{
            ResultSet rs = jdbcHelper.executeQuery(sql, args);
            while(rs.next()){
                KhachHang entity = new KhachHang();
                entity.setMaKH(rs.getString("MAKH"));
                entity.setHoTen(rs.getString("HOTEN"));
                entity.setGioiTinh(rs.getBoolean("GIOITINH"));
                entity.setDiaChi(rs.getString("DIACHI"));
                entity.setSDT(rs.getString("SDT"));
                entity.setEmail(rs.getString("Email"));
                entity.setHinh(rs.getString("Hinh"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
        public List<KhachHang> selectByKeyword(String keyword){
        String SELECT_BY_NAME_SQL = "SELECT * FROM KHACHHANG WHERE MAKH LIKE ?";
        return this.selectBySql(SELECT_BY_NAME_SQL, "%" + keyword + "%");
    }
}
