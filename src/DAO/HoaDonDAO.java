/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import HELPER.jdbcHelper;
import QLCC.model.HoaDon;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class HoaDonDAO extends DAO<HoaDon,String>{
    String INSERT_SQL = "INSERT INTO HOADON (MAHD, MAND,ngayban, MAKH,tongtien) VALUES(?,?,?,?,?)";
    String DELETE_SQL = "DELETE FROM HOADON WHERE MAHD = ?";
    String SELECT_BY_ID_SQL = "SELECT * FROM HOADON WHERE MAHD = ?";
    String SELECT_ALL_SQL = "SELECT * FROM HOADON";
    String SELECT_MAHD_SQL = "SELECT TOP 1 MAHD FROM HOADON ORDER BY (MAHD) DESC";
    
    @Override
    public void insert(HoaDon entity) {
       try{
            jdbcHelper.executeUpdate(INSERT_SQL, entity.getMaHD(), entity.getMaND(), entity.getNgayBan(), entity.getMaHD(), entity.getTongTien());
       }catch(Exception ex){
           Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @Override
    public void update(HoaDon entity) {
       
    }

    @Override
    public void delete(String id) {
        try {
            jdbcHelper.executeUpdate(DELETE_SQL, id);
        } catch (Exception ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public HoaDon selectById(String id) {
         List<HoaDon> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<HoaDon> selectAll() {
      return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<HoaDon>();
        try{
            ResultSet rs = jdbcHelper.executeQuery(sql, args);
            while(rs.next()){
                HoaDon entity=new HoaDon();
                entity.setMaHD("MAHD");
                entity.setMaND(rs.getString("Ma ND"));
                entity.setNgayBan(rs.getDate("Ngay ban"));
                entity.setMaKH("Ma KH");
                entity.setTongTien(rs.getFloat("Tong Tien"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    
    }
}
