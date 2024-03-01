/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;





import HELPER.jdbcHelper;
import QLCC.model.KhachHang;
import QLCC.model.SanPham;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            jdbcHelper.executeUpdate(INSERT_SQL, entity.getMaSP(), entity.getTenSP(), entity.getGia(), entity.getSoluong(),
                    entity.getNgayNhap(), entity.getMota(),  entity.getHinh());
        } catch (Exception ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(SanPham entity) {
        try {
        jdbcHelper.executeUpdate(UPDATE_SQL, entity.getTenSP(), entity.getGia(), entity.getSoluong(),
                    entity.getNgayNhap(), entity.getMota(),  entity.getHinh(),  entity.getMaSP());
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
                entity.setMaSP(rs.getString("MASP"));
                entity.setTenSP(rs.getString("TENSP"));
                entity.setGia(rs.getDouble("GIA"));
                entity.setSoluong(rs.getInt("SOLUONG"));
                entity.setNgayNhap(rs.getDate("NGAYNHAP"));
                entity.setMota(rs.getString("MOTA"));
                entity.setHinh(rs.getString("HINH"));

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

        public void update(KhachHang HD) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
//
        public KhachHang selectAll(String DC) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

   
    
}


    

