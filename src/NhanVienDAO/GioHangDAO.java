/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NhanVienDAO;


import SanPham.entity.GioHang;
import SanPham.entity.SanPhamDAO;
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
public class GioHangDAO extends DAO<GioHang, String> {

    String INSERT_SQL = "INSERT INTO GIOHANG(MAKH, TENSP, SOLUONG, DONGIA, SIZE)"
            + "VALUES(?,?,?,?)";
    String UPDATE_SQL = "UPDATE GIOHANG SET  MAKH = ?, TENSP = ?, SOLUONG = ?, DONGIA = ?,SIZE = ?";
    String DELETE_SQL = "DELETE FROM GIOHANG WHERE ID = ?";

    String SELECT_ALL_SQL = "SELECT * FROM GIOHANG";
    String SELECT_BY_ID_SQL = "select gh.id,\n"
            + "		gh.makh,\n"
            + "		gh.tensp,\n"
            + "		sp.masp,\n"
            + "		gh.soluong,\n"
            + "		sp.GIA,\n"
            + "		sp.MOTA\n"
            + "from khachhang kh left join giohang gh on kh.MaKH=gh.makh\n"
            + "				left join sanpham sp on gh.tensp=sp.TENSP\n"
            + "where kh.MaKH = ?";

    @Override
    public void insert(GioHang entity) {
        try {
            jdbcHelper.executeUpdate(INSERT_SQL, entity.getID(), entity.getMAKH(), entity.getTENSP(), entity.getSOLUONG(),
                    entity.getGIA(), entity.getSIZE());
        } catch (Exception ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(GioHang entity) {
        try {
            jdbcHelper.executeUpdate(INSERT_SQL, entity.getID(), entity.getMAKH(), entity.getTENSP(), entity.getSOLUONG(),
                    entity.getGIA(), entity.getSIZE());
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
    public GioHang selectById(String id) {
        List<GioHang> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<GioHang> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<GioHang> selectBySql(String sql, Object... args) {
        List<GioHang> list = new ArrayList<GioHang>();
        try {
            ResultSet rs = jdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                GioHang entity = new GioHang();
                entity.setID(rs.getString("ID"));
                entity.setMAKH(rs.getString("MAKH"));
                entity.setTENSP(rs.getString("TENSP"));
                entity.setSOLUONG(rs.getInt("SOLUONG"));
                entity.setGIA(rs.getFloat("DONGIA"));
                entity.setSIZE(rs.getString("SIZE"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
