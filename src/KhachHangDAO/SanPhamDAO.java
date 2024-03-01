/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KhachHangDAO;





import KhachHang.*;
import SanPham.entity.SanPham;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import until.jdbcHelper;

/**
 *
 * @author ASUS
 */
public class SanPhamDAO extends DAO<SanPham , String>{
    
    String INSERT_SQL = "INSERT INTO SANPHAM(MASP, TENSP, GIA, SOLUONG, NGAYNHAP, MOTA,  HINH)" +
                        "VALUES(?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE SANPHAM SET  TENSP = ?, GIA = ?, SOLUONG = ?, NGAYNHAP = ?, MOTA = ?, HINH = ? WHERE MASP = ?";
    String DELETE_SQL = "DELETE FROM SANPHAM WHERE MASP = ?";
    
    
    String SELECT_ALL_SQL = "SELECT * FROM sanpham";
    String SELECT_BY_ID_SQL = "SELECT * FROM SANPHAM WHERE MASP = ?";
    
    @Override
    public void insert(SanPham entity) {
       try {
            jdbcHelper.executeUpdate(INSERT_SQL, entity.getMASP(), entity.getTENSP(), entity.getGIA(), entity.getSOLUONG(),
                     entity.getMOTA(),  entity.getHINH());
        } catch (Exception ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(SanPham entity) {
        try {
        jdbcHelper.executeUpdate(INSERT_SQL, entity.getMASP(), entity.getTENSP(), entity.getGIA(), entity.getSOLUONG(),
                     entity.getMOTA(),  entity.getHINH());
        } catch (Exception ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void delete(String id) {
        try {
            jdbcHelper.executeUpdate(DELETE_SQL, id);
        } catch (Exception ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public SanPham selectById(String id) {
        List<SanPham> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<SanPham> selectAll() {
       return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<SanPham> selectBySql(String sql, Object... args) {
       List<SanPham> list = new ArrayList<SanPham>();
        try{
            ResultSet rs = jdbcHelper.executeQuery(sql, args);
            while(rs.next()){
                SanPham entity = new SanPham();
                entity.setMASP(rs.getString("MASP"));
                entity.setTENSP(rs.getString("TENSP"));
                entity.setGIA(rs.getDouble("GIA"));
                entity.setSOLUONG(rs.getInt("SOLUONG"));
                entity.setMOTA(rs.getString("MOTA"));
                entity.setHINH(rs.getString("HINH"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
     public List<SanPham> selectByKeyword(String keyword){
        String SELECT_BY_NAME_SQL = "SELECT * FROM SANPHAM WHERE TENSP LIKE ?";
        return this.selectBySql(SELECT_BY_NAME_SQL, "%" + keyword + "%");
    }



   
    
}


    

