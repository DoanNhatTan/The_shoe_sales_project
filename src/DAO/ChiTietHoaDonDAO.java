/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import HELPER.jdbcHelper;
import QLCC.model.ChiTietHoaDon;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class ChiTietHoaDonDAO extends DAO<ChiTietHoaDon,String>{
        String INSERT_SQL = "INSERT INTO CHITIETHOADON (MAHD, MASP, NGAYBAN,SoLuong,ghichu) VALUES(?,?,?,?,?)";

    @Override
    public void insert(ChiTietHoaDon entity) {
       try{
            jdbcHelper.executeUpdate(INSERT_SQL, entity.getMAHD(), entity.getMaSP(), entity.getNgayBan(),entity.getSoLuong(),entity.getGhiChu());
       }catch(Exception ex){
           Logger.getLogger(ChiTietHoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @Override
    public void update(ChiTietHoaDon entity) {
        try {
            jdbcHelper.executeUpdate(INSERT_SQL, entity.getMAHD(), entity.getMaSP(), entity.getNgayBan(),entity.getSoLuong(),entity.getGhiChu());
        } catch (Exception e) {
        }
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ChiTietHoaDon selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ChiTietHoaDon> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<ChiTietHoaDon> selectBySql(String sql, Object... args) {
        List<ChiTietHoaDon> list = new ArrayList<ChiTietHoaDon>();
        try{
            ResultSet rs = jdbcHelper.executeQuery(sql, args);
            while(rs.next()){
                ChiTietHoaDon entity = new ChiTietHoaDon();
                entity.setMAHD(rs.getString("MAHD"));
                entity.setMaSP(rs.getString("MASP"));
                entity.setNgayBan(rs.getDate("NGAYBAN"));
                entity.setSoLuong(rs.getInt("So luong"));
                entity.setGhiChu(rs.getString("Ghi chu"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    
    }
    
}
