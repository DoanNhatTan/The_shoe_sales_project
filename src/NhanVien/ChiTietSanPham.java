/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package NhanVien;


import BH.form.FormHome;
import NhanVienDAO.GioHangDAO;
import NhanVienDAO.KhachHangDAO;
import SanPham.entity.DanhMucSP;
import SanPham.entity.DanhMucSpDAO;
import SanPham.entity.HoaDon;
import SanPham.entity.HoaDonChiTiet;
import SanPham.entity.HoaDonChiTietDAO;
import SanPham.entity.HoaDonDAO;
import SanPham.entity.KhachHang;
import SanPham.entity.SanPham;
import SanPham.entity.SanPhamDAO;
import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.Point;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import until.Auth;
import until.DialogHelper;
import until.MsgBox;
import until.XDate;
import until.XImage;

/**
 *
 * @author ASUS
 */
public class ChiTietSanPham extends javax.swing.JPanel {

    /**
     * Creates new form CustomerManagerJPanel
     */
    public ChiTietSanPham() {

        initComponents();
        init();
        chkShow.setToolTipText(date_format.format(XDate.now()));

    }
    
    SimpleDateFormat date_format = new SimpleDateFormat("yyyy/MM/dd");
    HoaDonChiTietDAO ctdao = new HoaDonChiTietDAO();
    KhachHangDAO khdao = new KhachHangDAO();
    HoaDonDAO hddao = new HoaDonDAO();
    
    public boolean checkTrungMaKH(JTextField txt) {//kiểm tra trùng mã khách hàng
        txt.setBackground(white);
        if (khdao.selectById(txt.getText()) == null) {
            return true;
        } else {
            txt.setBackground(Color.pink);
            MsgBox.showMessageDialog(this,"Mã khách hàng "+ txt.getText()+ " đã tồn tại");
            return false;
        }
    }
    
    public boolean checkSo(){
            try {
                int soluong =Integer.parseInt(txtSoLuong1.getText());
                float giamgia =Float.parseFloat(txtGiamGia.getText());
                
                if (giamgia <0 || giamgia > 100) {
                    MsgBox.showErrorDialog(this, "Giảm giá từ 0-100%", "THÔNG BÁO");
                    return false;
                }           
                if (soluong <= 0) {
                    MsgBox.showErrorDialog(this, "Số lượng sản phẩm phải lớn hơn 0", "THÔNG BÁO");
                    return false;
                }        
            } catch (Exception e) {
                MsgBox.showErrorDialog(this, "Phải là số", "THÔNG BÁO");
                return false;
            }
            return true;
        }
    
    void fillTableHDCTbyMaHD() {//đổ danh sách hóa đơn chi tiết vào bảng điểm  theo maHDBan
        DefaultTableModel model = (DefaultTableModel) tbHDCT.getModel();
        model.setRowCount(0);
            String mahd = txtMaHD.getText();
            List<Object[]> list = ctdao.getHDCTByMaHDBan(mahd); //gọi store procedure sp_bangdiem (lấy theo mã khóa học)
            for (Object[] row : list) { //cho vòng lặp duyệt qua mảng líst
                model.addRow(new Object[]{row[0], row[1], row[2], row[3], row[4],row[5],row[6]});
        }
    }
    
    DanhMucSpDAO dmspdao = new DanhMucSpDAO();
    
    void fillComboBoxDanhMucSP() { //đổ dữ liệu danh mục sản phaẩm lên combobox
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoaiSP.getModel(); //kết nối model với cbo
        model.removeAllElements();   //xóa toàn bộ item của cbo
        try {
            List<DanhMucSP> list = dmspdao.selectAll();
            for (DanhMucSP sp : list) {
                model.addElement(sp);    //thêm đối tượng (Object) vào model
            }
            this.fillComboBoxSP();
        } catch (Exception e) {
            MsgBox.showMessageDialog(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    
    SanPhamDAO spdao = new SanPhamDAO();
    void fillComboBoxSP(){
       DefaultComboBoxModel model = (DefaultComboBoxModel) cboSanPham.getModel();
       model.removeAllElements();
       DanhMucSP dmsp = (DanhMucSP) cboLoaiSP.getSelectedItem(); //lấy chuyên đề được chọn
       if (dmsp != null) {
           List<SanPham> list = spdao.selectSPByDanhMuc(dmsp.getMaDM()); //lấy khóa học theo mã chuyên đề
           for (SanPham sp : list) {
               model.addElement(sp);
           }
       }
   }
    
    void chonSanPham() {
        try {
            SanPham sp = (SanPham) cboSanPham.getSelectedItem(); 
            //lấy 1 Object được chọn từ combobox
            //có thể điền và lấy 1 Object từ combobox
            txtMaSP1.setText(sp.getMASP());
            txtDonGia.setText(String.valueOf(sp.getGIA()));

            
            this.rowhdct = -1;
        } catch (Exception e) {
            return;
        }
        
    }
    
    HoaDonChiTiet getModelHoaDonCTUpdate() {    
        //lấy thông tin hoadonchitiet từ form, return hoadonchitiet dùng để update
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        SanPham sp = (SanPham) cboSanPham.getSelectedItem();
        hdct.setMaSP(sp.getMASP());
        hdct.setSoLuong(Integer.valueOf(txtSoLuong1.getText()));
        hdct.setGiamGia(Float.valueOf(txtGiamGia.getText()));
        hdct.setMaHDBan(txtMaHD.getText());
        
        hdct.setMaHDCT(Integer.valueOf(cboSanPham.getToolTipText()));
        return hdct;
    }
    
    HoaDonChiTiet getModelHoaDonCTInsert() {    
        //lấy thông tin hoadonchitiet từ form, return hoadonchitiet dùng để insert
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        SanPham sp = (SanPham) cboSanPham.getSelectedItem();
        hdct.setMaSP(sp.getMASP());
        hdct.setSoLuong(Integer.valueOf(txtSoLuong1.getText()));
        hdct.setGiamGia(Float.valueOf(txtGiamGia.getText()));
        hdct.setMaHDBan(txtMaHD.getText());
        
        return hdct;
    }
    
    void setModelHDCT(HoaDonChiTiet hdct) {    //điền thông tin từ đt hoadonchitiet len form
        txtGiamGia.setText(String.valueOf(hdct.getGiamGia()));
        txtSoLuong1.setText(String.valueOf(hdct.getSoLuong())); 
        cboSanPham.getModel().setSelectedItem(spdao.selectById(hdct.getMaSP()));
    }
    
    int rowhdct = -1;
    void editHDCT() {
    //lấy maHDCT từ bảng theo index, lấy đt HoaDonChiTiet từ CSDL theo MaHDCT
    // đưa thông tin từ đt HoaDonChiTiet lên form, chuyển sang editable    
        try {
            Integer maHDCT = (Integer) tbHDCT.getValueAt(this.rowhdct, 0);
            HoaDonChiTiet hdct = ctdao.selectById(maHDCT); 
            if (hdct != null) {
                lblHDCT.setText(String.valueOf(maHDCT));
                cboSanPham.setToolTipText(String.valueOf(maHDCT));
//                lấy mã hdct set lên lblHDCT, lblHDCT đã cho foregroud trùng với màu backgroud để ẩn đi
                this.setModelHDCT(hdct);
                this.updateStatusHDCT();
            }
        } catch (Exception e) {
            MsgBox.showMessageDialog(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    
    void updateStatusHDCT(){ //cập nhật trạng thái form
          boolean edit = (this.row >= 0); //đang chọn một hàng nào đó ->> trạng thái edit   
          
          //trạng thái form
          btnThem.setEnabled(!edit);
          btnSua.setEnabled(edit);
          btnXoa.setEnabled(edit);
      }
    
    
    void insertHDCT() {
     /*
    lấy thông tin trên form cho vào đt hoadonchitiet
    thêm đt hoadonchitiet vào CSDL, load lại bảng
    xóa trắng form, để ở insertable
    */    
        HoaDonChiTiet model = getModelHoaDonCTInsert();
        String maHD = txtMaHD.getText();
         //dùng getModelHoaDonCTInsert vì ở đây Insert hoadonchitiet thì maHDCT là được sinh ra tự động
        try {
            ctdao.insert(model);            
            this.fillTableHDCTbyMaHD();
            this.clearHDCT();
            this.loadTongTien(maHD);
            MsgBox.showMessageDialog(this, "Thêm mới hóa đơn thành công!");
            tabs.setEnabledAt(0, true);
            if (chkShow.isSelected()) {
                this.fillTableHoaDon();
            }else{
                this.fillTableHoaDonHomNay();
            }
        } catch (HeadlessException e) {
            MsgBox.showMessageDialog(this, "Thêm mới hóa đơn thất bại!");
        }
    }
    
    void updateHDCT() {
    //lấy thông tin trên form cho vào đt hoadonchitiet
    //cập nhật bản ghi trong CSDL theo maHDCT và thông tin khác từ đt hoadonchitiet
    //load lại bảng    
        HoaDonChiTiet model = getModelHoaDonCTUpdate();
        String maHD = txtMaHD.getText();
        //dùng getModelHoaDonCTUpdate vì có gettext lblMaHDCT để cập nhật HoaDonChoTiet theo maHDCT
        try {
            ctdao.update(model);
            this.fillTableHDCTbyMaHD();
            this.clearHDCT();
            this.loadTongTien(maHD);
            if (chkShow.isSelected()) {
                this.fillTableHoaDon();
            }else{
                this.fillTableHoaDonHomNay();
            }
            MsgBox.showMessageDialog(this, "Cập nhật hóa đơn thành công!");
        } catch (Exception e) {
            MsgBox.showMessageDialog(this, "Cập nhật hóa đơn thất bại!");
            e.printStackTrace();
        }
    }
    
    void deleteHDCT() {
    //xóa bản ghi trong CSDL theo maHDCT lấy trên form
    //load lại bảng, xóa trắng form, chuyển sang insertable    
        if (MsgBox.showConfirmDialog(this, "Bạn thực sự muốn xóa hóa đơn này?")) {
            Integer maHDCT = Integer.valueOf(lblHDCT.getText()); //lấy mahdct ở lblhdct để xóa
            String maHD = txtMaHD.getText();
            try {
                ctdao.delete(maHDCT);
                this.fillTableHDCTbyMaHD();
                this.clearHDCT();
                this.loadTongTien(maHD);
                
                if (chkShow.isSelected()) {
                    this.fillTableHoaDon();
                }else{
                    this.fillTableHoaDonHomNay();
                }
                MsgBox.showMessageDialog(this, "Xóa hóa đon chi tiết thành công!");
            } catch (Exception e) {
                MsgBox.showMessageDialog(this, "Xóa hóa đơn chi tiết thất bại!");
            }
        }
    }
    
    
    void clearHDCT() {//xóa trắng form, chuyển sang insertable
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        //txtTenNV.setText(Auth.user.getTenNV());
        SanPham sanPham = (SanPham) cboSanPham.getSelectedItem();//lấy sanpham đang đc chọn ở combobox
        hdct.setMaSP(sanPham.getMASP());
        hdct.setMaHDBan(hdct.getMaHDBan());
        hdct.setGiamGia(hdct.getGiamGia());   
        hdct.setSoLuong(hdct.getSoLuong());
        this.setModelHDCT(hdct);

        txtThanhTien.setText("00");
        btnThem.setEnabled(true);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        txtMaSP1.setBackground(white);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        print_Dialog = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Area = new javax.swing.JTextArea();
        btnDone = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        themKH = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        txtSODIENTHOAI = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        them = new javax.swing.JButton();
        huy = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        pnlThongTinSP = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        pnlHinh = new javax.swing.JPanel();
        lblHinh = new javax.swing.JLabel();
        lblHinh1 = new javax.swing.JLabel();
        lblHinh2 = new javax.swing.JLabel();
        lblHinh3 = new javax.swing.JLabel();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPre = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        pnlDanhSachSP = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        pnlThongTinChung = new javax.swing.JPanel();
        jlbMaHD = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        txtNgayBan = new javax.swing.JTextField();
        jlbNgayBan = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jlbMaNV = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jblTenNV = new javax.swing.JLabel();
        jlbMaKh = new javax.swing.JLabel();
        jlbTenKH = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jlbDiaChi = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jlbSDT = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        cboTenKH = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbHoaDon = new javax.swing.JTable();
        btnSave = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnFirst1 = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext1 = new javax.swing.JButton();
        btnLast1 = new javax.swing.JButton();
        lblThemKH = new javax.swing.JLabel();
        chkShow = new javax.swing.JCheckBox();
        pnlThongTinChiTiet = new javax.swing.JPanel();
        lblTenSP = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbHDCT = new javax.swing.JTable();
        txtThanhTien = new javax.swing.JTextField();
        lblThanhTien = new javax.swing.JLabel();
        txtMaSP1 = new javax.swing.JTextField();
        lblMaSP = new javax.swing.JLabel();
        cboSanPham = new javax.swing.JComboBox<>();
        lblDonGIa = new javax.swing.JLabel();
        lblSoLuong = new javax.swing.JLabel();
        txtSoLuong1 = new javax.swing.JTextField();
        txtGiamGia = new javax.swing.JTextField();
        lblGiamGia = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnIn = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnXong = new javax.swing.JButton();
        lblHDCT = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblAnh = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cboLoaiSP = new javax.swing.JComboBox<>();

        print_Dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        print_Dialog.setTitle("IN HÓA ĐƠN");
        print_Dialog.setUndecorated(true);
        print_Dialog.setSize(new java.awt.Dimension(560, 590));

        jPanel3.setBackground(new java.awt.Color(180, 198, 166));

        Area.setEditable(false);
        Area.setColumns(20);
        Area.setRows(5);
        jScrollPane3.setViewportView(Area);

        btnDone.setBackground(new java.awt.Color(246, 185, 59));
        btnDone.setForeground(new java.awt.Color(30, 55, 153));
        btnDone.setText("PRINT");
        btnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoneActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(246, 185, 59));
        btnCancel.setForeground(new java.awt.Color(30, 55, 153));
        btnCancel.setText("CANCEL");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("HÓA ĐƠN THANH TOÁN");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(300, Short.MAX_VALUE)
                .addComponent(btnDone)
                .addGap(34, 34, 34)
                .addComponent(btnCancel)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDone)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        javax.swing.GroupLayout print_DialogLayout = new javax.swing.GroupLayout(print_Dialog.getContentPane());
        print_Dialog.getContentPane().setLayout(print_DialogLayout);
        print_DialogLayout.setHorizontalGroup(
            print_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        print_DialogLayout.setVerticalGroup(
            print_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        themKH.setTitle("THÊM KHÁCH HÀNG");
        themKH.setSize(new java.awt.Dimension(410, 250));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 204));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("THÊM NHANH KHÁCH HÀNG");

        jLabel8.setText("Tên khách hàng :");

        jLabel9.setText("Số điện thoại :");

        them.setBackground(new java.awt.Color(246, 185, 59));
        them.setForeground(new java.awt.Color(30, 55, 153));
        them.setText("THÊM");
        them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themActionPerformed(evt);
            }
        });

        huy.setBackground(new java.awt.Color(246, 185, 59));
        huy.setForeground(new java.awt.Color(30, 55, 153));
        huy.setText("HỦY");
        huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                huyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSODIENTHOAI)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(them)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(huy, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtSODIENTHOAI, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(them)
                    .addComponent(huy))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout themKHLayout = new javax.swing.GroupLayout(themKH.getContentPane());
        themKH.getContentPane().setLayout(themKHLayout);
        themKHLayout.setHorizontalGroup(
            themKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        themKHLayout.setVerticalGroup(
            themKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1117, 721));

        tabs.setBackground(new java.awt.Color(255, 255, 255));
        tabs.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tabs.setPreferredSize(new java.awt.Dimension(828, 370));

        pnlThongTinSP.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Mã sản phẩm:");

        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
            }
        });

        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Tên sản phẩm:");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Giá :");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Mô tả:");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Số lượng:");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane2.setViewportView(txtMoTa);

        pnlHinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblHinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblHinh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });

        lblHinh1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinh1.setToolTipText("");
        lblHinh1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblHinh1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinh1MouseClicked(evt);
            }
        });

        lblHinh2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinh2.setToolTipText("");
        lblHinh2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblHinh2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinh2MouseClicked(evt);
            }
        });

        lblHinh3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinh3.setToolTipText("");
        lblHinh3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblHinh3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinh3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlHinhLayout = new javax.swing.GroupLayout(pnlHinh);
        pnlHinh.setLayout(pnlHinhLayout);
        pnlHinhLayout.setHorizontalGroup(
            pnlHinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHinhLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lblHinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(lblHinh2, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addGap(51, 51, 51)
                .addComponent(lblHinh3, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addGap(40, 40, 40))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHinhLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
        );
        pnlHinhLayout.setVerticalGroup(
            pnlHinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHinhLayout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(pnlHinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblHinh3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHinh2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/last.jpg"))); // NOI18N
        btnLast.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLastMouseClicked(evt);
            }
        });
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/next.jpg"))); // NOI18N
        btnNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNextMouseClicked(evt);
            }
        });
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/prev.jpg"))); // NOI18N
        btnPre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPreMouseClicked(evt);
            }
        });
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/frist.jpg"))); // NOI18N
        btnFirst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFirstMouseClicked(evt);
            }
        });
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(pnlHinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenSP)
                            .addComponent(txtMaSP)
                            .addComponent(txtGia)
                            .addComponent(txtSoLuong)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))))
                .addGap(0, 97, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnFirst, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnPre, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnLast, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnNext, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(pnlHinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlThongTinSPLayout = new javax.swing.GroupLayout(pnlThongTinSP);
        pnlThongTinSP.setLayout(pnlThongTinSPLayout);
        pnlThongTinSPLayout.setHorizontalGroup(
            pnlThongTinSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinSPLayout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlThongTinSPLayout.setVerticalGroup(
            pnlThongTinSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinSPLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 52, Short.MAX_VALUE))
        );

        tabs.addTab("Thông tin sản phẩm", pnlThongTinSP);

        pnlDanhSachSP.setBackground(new java.awt.Color(255, 255, 255));

        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Giá", "Số lượng", "Mô tả", "Hình", "Hình 1", "Hình 2", "Hình 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCustomer.setRowHeight(24);
        tblCustomer.setSelectionBackground(new java.awt.Color(114, 187, 102));
        tblCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCustomerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCustomer);

        jLabel24.setBackground(java.awt.Color.lightGray);
        jLabel24.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Closed folder.png"))); // NOI18N
        jLabel24.setText("Excel");
        jLabel24.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jLabel24AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });

        txtTimKiem.setToolTipText("Tìm kiếm");
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/find 1.png"))); // NOI18N

        javax.swing.GroupLayout pnlDanhSachSPLayout = new javax.swing.GroupLayout(pnlDanhSachSP);
        pnlDanhSachSP.setLayout(pnlDanhSachSPLayout);
        pnlDanhSachSPLayout.setHorizontalGroup(
            pnlDanhSachSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachSPLayout.createSequentialGroup()
                .addGap(259, 259, 259)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(123, 123, 123)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 105, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDanhSachSPLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 917, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        pnlDanhSachSPLayout.setVerticalGroup(
            pnlDanhSachSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachSPLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(pnlDanhSachSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDanhSachSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        tabs.addTab("Danh sách sản phẩm", pnlDanhSachSP);

        pnlThongTinChung.setBackground(new java.awt.Color(255, 255, 255));
        pnlThongTinChung.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chung"));

        jlbMaHD.setText("Mã hóa đơn");

        txtMaHD.setEditable(false);
        txtMaHD.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtMaHD.setForeground(new java.awt.Color(255, 0, 0));
        txtMaHD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMaHDFocusGained(evt);
            }
        });
        txtMaHD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaHDKeyReleased(evt);
            }
        });

        txtNgayBan.setEditable(false);

        jlbNgayBan.setText("Ngày bán :");

        txtMaNV.setEditable(false);

        jlbMaNV.setText("Mã nhân viên :");

        txtTenNV.setEditable(false);

        jblTenNV.setForeground(new java.awt.Color(255, 0, 51));
        jblTenNV.setText("Tên nhân viên :");

        jlbMaKh.setText("Tên khách hàng");

        jlbTenKH.setText("Mã khách hàng :");

        txtMaKH.setEditable(false);

        jlbDiaChi.setText("Địa chỉ :");

        txtDiaChi.setEditable(false);

        jlbSDT.setText("Điện thoại :");

        txtSDT.setEditable(false);

        cboTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenKHActionPerformed(evt);
            }
        });

        tbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "MÃ HÓA ĐƠN", "MÃ NHÂN VIÊN", "NGÀY BÁN", "MÃ KHÁCH HÀNG", "TỔNG TIỀN"
            }
        ));
        tbHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbHoaDon);

        btnSave.setBackground(new java.awt.Color(246, 185, 59));
        btnSave.setForeground(new java.awt.Color(30, 55, 153));
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(246, 185, 59));
        btnEdit.setForeground(new java.awt.Color(30, 55, 153));
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(246, 185, 59));
        btnDelete.setForeground(new java.awt.Color(30, 55, 153));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnNew.setBackground(new java.awt.Color(246, 185, 59));
        btnNew.setForeground(new java.awt.Color(30, 55, 153));
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnFirst1.setBackground(new java.awt.Color(246, 185, 59));
        btnFirst1.setForeground(new java.awt.Color(30, 55, 153));
        btnFirst1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/frist.jpg"))); // NOI18N
        btnFirst1.setToolTipText("");
        btnFirst1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirst1ActionPerformed(evt);
            }
        });

        btnPrev.setBackground(new java.awt.Color(246, 185, 59));
        btnPrev.setForeground(new java.awt.Color(30, 55, 153));
        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/prev.jpg"))); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext1.setBackground(new java.awt.Color(246, 185, 59));
        btnNext1.setForeground(new java.awt.Color(30, 55, 153));
        btnNext1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/next.jpg"))); // NOI18N
        btnNext1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext1ActionPerformed(evt);
            }
        });

        btnLast1.setBackground(new java.awt.Color(246, 185, 59));
        btnLast1.setForeground(new java.awt.Color(30, 55, 153));
        btnLast1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/last.jpg"))); // NOI18N
        btnLast1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLast1ActionPerformed(evt);
            }
        });

        lblThemKH.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblThemKH.setText("-----THÊM NHANH KHÁCH HÀNG-----");
        lblThemKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThemKHMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblThemKHMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblThemKHMouseExited(evt);
            }
        });

        chkShow.setSelected(true);
        chkShow.setText("Xem tất cả hóa đơn ?");
        chkShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkShowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlThongTinChungLayout = new javax.swing.GroupLayout(pnlThongTinChung);
        pnlThongTinChung.setLayout(pnlThongTinChungLayout);
        pnlThongTinChungLayout.setHorizontalGroup(
            pnlThongTinChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlThongTinChungLayout.createSequentialGroup()
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEdit)
                .addGap(12, 12, 12)
                .addComponent(btnDelete)
                .addGap(18, 18, 18)
                .addComponent(btnNew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 400, Short.MAX_VALUE)
                .addComponent(btnFirst1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPrev)
                .addGap(18, 18, 18)
                .addComponent(btnNext1)
                .addGap(18, 18, 18)
                .addComponent(btnLast1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinChungLayout.createSequentialGroup()
                .addGroup(pnlThongTinChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlbMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlbNgayBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlbMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jblTenNV))
                .addGap(22, 22, 22)
                .addGroup(pnlThongTinChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtNgayBan)
                    .addComponent(txtMaNV)
                    .addComponent(txtTenNV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlThongTinChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jlbTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlbMaKh))
                    .addComponent(jlbSDT)
                    .addComponent(jlbDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboTenKH, 0, 224, Short.MAX_VALUE)
                    .addComponent(txtSDT)
                    .addComponent(txtDiaChi)
                    .addComponent(txtMaKH)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinChungLayout.createSequentialGroup()
                .addComponent(chkShow)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblThemKH)
                .addGap(22, 22, 22))
        );
        pnlThongTinChungLayout.setVerticalGroup(
            pnlThongTinChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinChungLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlThongTinChungLayout.createSequentialGroup()
                        .addComponent(cboTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123))
                    .addGroup(pnlThongTinChungLayout.createSequentialGroup()
                        .addGroup(pnlThongTinChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbMaHD)
                            .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbMaKh))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlThongTinChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbNgayBan)
                            .addComponent(txtNgayBan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbTenKH)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlThongTinChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbMaNV)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbDiaChi)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlThongTinChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jblTenNV)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbSDT)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(pnlThongTinChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThemKH)
                    .addComponent(chkShow))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnNew)
                    .addComponent(btnEdit)
                    .addComponent(btnSave)
                    .addComponent(btnFirst1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLast1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tabs.addTab("HÓA ĐƠN", pnlThongTinChung);

        pnlThongTinChiTiet.setBackground(new java.awt.Color(255, 255, 255));
        pnlThongTinChiTiet.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chi tiết hóa đơn"));
        pnlThongTinChiTiet.setPreferredSize(new java.awt.Dimension(750, 530));

        lblTenSP.setText("Tên sản phẩm :");

        tbHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "MÃ HDCT", "MÃ HÓA ĐƠN", "TÊN SẢN PHẨM", "SỐ LƯỢNG", "ĐƠN GIÁ", "GIẢM GIÁ", "THÀNH TIỀN"
            }
        ));
        tbHDCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHDCTMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbHDCT);

        txtThanhTien.setEditable(false);

        lblThanhTien.setText("Thành tiền :");

        txtMaSP1.setEditable(false);

        lblMaSP.setText("Mã SP :");

        cboSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSanPhamActionPerformed(evt);
            }
        });

        lblDonGIa.setText("Đơn giá :");

        lblSoLuong.setText("Số lượng :");

        txtSoLuong1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLuong1KeyReleased(evt);
            }
        });

        txtGiamGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiamGiaKeyReleased(evt);
            }
        });

        lblGiamGia.setText("Giảm giá :");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnThem.setBackground(new java.awt.Color(246, 185, 59));
        btnThem.setForeground(new java.awt.Color(30, 55, 153));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(246, 185, 59));
        btnSua.setForeground(new java.awt.Color(30, 55, 153));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(246, 185, 59));
        btnXoa.setForeground(new java.awt.Color(30, 55, 153));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnIn.setBackground(new java.awt.Color(246, 185, 59));
        btnIn.setForeground(new java.awt.Color(30, 55, 153));
        btnIn.setText("In");
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
            }
        });

        btnMoi.setBackground(new java.awt.Color(246, 185, 59));
        btnMoi.setForeground(new java.awt.Color(30, 55, 153));
        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnXong.setBackground(new java.awt.Color(246, 185, 59));
        btnXong.setForeground(new java.awt.Color(30, 55, 153));
        btnXong.setText("Xong");
        btnXong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem))
                .addGap(66, 66, 66)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXong)
                    .addComponent(btnXoa))
                .addGap(26, 26, 26))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        lblHDCT.setForeground(new java.awt.Color(255, 255, 255));
        lblHDCT.setText("HDCT :");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("%");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("VNĐ");

        lblTongTien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongTien.setText("0.0");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setText("TỔNG TIỀN :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("VNĐ");

        lblAnh.setText("Ảnh ");

        txtDonGia.setEditable(false);

        jLabel7.setText("Loại sản phẩm :");

        cboLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlThongTinChiTietLayout = new javax.swing.GroupLayout(pnlThongTinChiTiet);
        pnlThongTinChiTiet.setLayout(pnlThongTinChiTietLayout);
        pnlThongTinChiTietLayout.setHorizontalGroup(
            pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinChiTietLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(pnlThongTinChiTietLayout.createSequentialGroup()
                        .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblMaSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 101, Short.MAX_VALUE))
                            .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlThongTinChiTietLayout.createSequentialGroup()
                                .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboSanPham, 0, 192, Short.MAX_VALUE)
                                    .addComponent(txtMaSP1)
                                    .addComponent(cboLoaiSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinChiTietLayout.createSequentialGroup()
                                        .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblDonGIa, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(19, 19, 19)
                                        .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtDonGia)
                                            .addComponent(txtSoLuong1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinChiTietLayout.createSequentialGroup()
                                        .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtGiamGia)
                                            .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlThongTinChiTietLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlThongTinChiTietLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinChiTietLayout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel4))
                                            .addComponent(lblHDCT, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addGroup(pnlThongTinChiTietLayout.createSequentialGroup()
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 345, Short.MAX_VALUE)))))))
                .addContainerGap())
        );
        pnlThongTinChiTietLayout.setVerticalGroup(
            pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinChiTietLayout.createSequentialGroup()
                .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinChiTietLayout.createSequentialGroup()
                        .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaSP))
                        .addGap(18, 18, 18)
                        .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlThongTinChiTietLayout.createSequentialGroup()
                        .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDonGIa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoLuong1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSoLuong))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGiamGia, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)))
                        .addGap(11, 11, 11)
                        .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblThanhTien)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(lblHDCT)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(4, 4, 4)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlThongTinChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addContainerGap())
        );

        tabs.addTab("HÓA ĐƠN CHI TIẾT", pnlThongTinChiTiet);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 1022, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoneActionPerformed
//        this.printBill();
//        print_Dialog.dispose();

    }//GEN-LAST:event_btnDoneActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        print_Dialog.dispose();
        Area.setText("");
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
        //        int ret = JOptionPane.showConfirmDialog(this, "Bạn có muốn lưu thành file Excel không", "Confirm",
            //                JOptionPane.YES_NO_OPTION);
        //        if (ret == JOptionPane.YES_OPTION) {
            //            exportExcel(tblCustomer);
            //            return;
            //        }        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel24MouseClicked

    private void jLabel24AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel24AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel24AncestorAdded

    private void tblCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.row = tblCustomer.getSelectedRow();
            this.edit();
        }
    }//GEN-LAST:event_tblCustomerMouseClicked

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        this.row = 0;
          this.edit();
          tblCustomer.setRowSelectionInterval(row, row);
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnFirstMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFirstMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFirstMouseClicked

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        // TODO add your handling code here:
        if (this.row > 0) {
              this.row--;
              this.edit();
              tblCustomer.setRowSelectionInterval(row, row);
          }
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnPreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPreMouseClicked

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        if (this.row < tblCustomer.getRowCount() - 1) {
              this.row++;
              this.edit();
              tblCustomer.setRowSelectionInterval(row, row);
          }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextMouseClicked

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        this.row = tblCustomer.getRowCount() -1 ;
           this.edit();
           tblCustomer.setRowSelectionInterval(row, row);
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnLastMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLastMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLastMouseClicked

    private void lblHinh3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinh3MouseClicked
        // TODO add your handling code here:
        List<SanPham> list = dao.selectAll();
        for (SanPham sp : list) {
            try {
                lblHinh.setToolTipText(sp.getHINH1());
                lblHinh.setIcon(XImage.readAndResize(lblHinh, sp.getHINH3()));

            } catch (IOException ex) {
                DialogHelper.alert(this, "Lỗi tải hình ảnh");
            }
        }
    }//GEN-LAST:event_lblHinh3MouseClicked

    private void lblHinh2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinh2MouseClicked
        // TODO add your handling code here:
        List<SanPham> list = dao.selectAll();
        for (SanPham sp : list) {
            try {
                lblHinh.setToolTipText(sp.getHINH1());
                lblHinh.setIcon(XImage.readAndResize(lblHinh, sp.getHINH2()));

            } catch (IOException ex) {
                DialogHelper.alert(this, "Lỗi tải hình ảnh");
            }
        }
    }//GEN-LAST:event_lblHinh2MouseClicked

    private void lblHinh1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinh1MouseClicked
        // TODO add your handling code here:
        List<SanPham> list = dao.selectAll();
        for (SanPham sp : list) {
            try {
                lblHinh.setToolTipText(sp.getHINH1());
                lblHinh.setIcon(XImage.readAndResize(lblHinh, sp.getHINH1()));

            } catch (IOException ex) {
                DialogHelper.alert(this, "Lỗi tải hình ảnh");
            }
        }
    }//GEN-LAST:event_lblHinh1MouseClicked

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        // TODO add your handling code here:

        //        int rs = f.showOpenDialog(null);
    }//GEN-LAST:event_lblHinhMouseClicked

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSPActionPerformed

    private void txtMaHDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaHDFocusGained
        txtMaHD.setBackground(white);
    }//GEN-LAST:event_txtMaHDFocusGained

    private void txtMaHDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaHDKeyReleased
        if (checkTrungMa(txtMaHD)) {
            return;
        }
    }//GEN-LAST:event_txtMaHDKeyReleased

    private void cboTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenKHActionPerformed
        //this.chonKhachHang();
    }//GEN-LAST:event_cboTenKHActionPerformed

    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked
        if (evt.getClickCount() == 1) {
            this.row = tbHoaDon.rowAtPoint(evt.getPoint());
            if (this.row >= 0) {
                this.edit();
                this.fillTableHDCTbyMaHD();
                tabs.setSelectedIndex(3);
                tabs.setEnabledAt(1, true);
                this.clearHDCT();
            }
        }
    }//GEN-LAST:event_tbHoaDonMouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        this.insertHĐ();
        tabs.setEnabledAt(1, true);

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (MsgBox.showConfirmDialog(this, "Bạn chắc chắn muốm cập nhập hóa đơn này ")) {
            this.updateHD();
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        this.deleteHĐ();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        this.clear();
        this.clearHDCT();
        this.fillTableHDCTbyMaHD();
        tabs.setEnabledAt(1, false);
        if (chkShow.isSelected()) {
            this.fillTableHoaDon();
        }else{
            this.fillTableHoaDonHomNay();
        }
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnFirst1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirst1ActionPerformed
        
          this.row = 0;
          this.edit();
          tbHoaDon.setRowSelectionInterval(row, row);
    
    }//GEN-LAST:event_btnFirst1ActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        if (this.row > 0) {
              this.row--;
              this.edit();
              tbHoaDon.setRowSelectionInterval(row, row);
          }
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNext1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext1ActionPerformed
        if (this.row < tbHoaDon.getRowCount() - 1) {
              this.row++;
              this.edit();
              tbHoaDon.setRowSelectionInterval(row, row);
          } 
    }//GEN-LAST:event_btnNext1ActionPerformed

    private void btnLast1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLast1ActionPerformed
        this.row = tbHoaDon.getRowCount() -1 ;
           this.edit();
           tbHoaDon.setRowSelectionInterval(row, row);
    }//GEN-LAST:event_btnLast1ActionPerformed

    private void lblThemKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThemKHMouseClicked
        themKH.setVisible(true);
    }//GEN-LAST:event_lblThemKHMouseClicked

    private void lblThemKHMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThemKHMouseEntered
        lblThemKH.setForeground(Color.red);
    }//GEN-LAST:event_lblThemKHMouseEntered

    private void lblThemKHMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThemKHMouseExited
        lblThemKH.setForeground(Color.black);
    }//GEN-LAST:event_lblThemKHMouseExited

    private void chkShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkShowActionPerformed
        if (chkShow.isSelected()) {
            this.fillTableHoaDon();
        } else {
            this.fillTableHoaDonHomNay();
        }
    }//GEN-LAST:event_chkShowActionPerformed

    private void cboSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSanPhamActionPerformed
        this.chonSanPham();
        this.TinhTien();
    }//GEN-LAST:event_cboSanPhamActionPerformed

    private void txtSoLuong1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuong1KeyReleased
        if (txtSoLuong1.getText().isEmpty()) {
            return;
        }
        if (checkSo()) {
            this.TinhTien();
        }
    }//GEN-LAST:event_txtSoLuong1KeyReleased

    private void txtGiamGiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiamGiaKeyReleased
        if (txtGiamGia.getText().isEmpty()) {
            return;
        }
        if (checkSo()) {
            this.TinhTien();
        }
    }//GEN-LAST:event_txtGiamGiaKeyReleased

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        this.insertHDCT();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (isEmtyHDCT()) {
            this.updateHDCT();
        }else{
            MsgBox.showErrorDialog(this, "Đơn giá và số lượng không được để trống", "LỖI");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        this.deleteHDCT();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        print_Dialog.setVisible(true);
        String maHD = txtMaHD.getText();
        this.showBill(maHD);
    }//GEN-LAST:event_btnInActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        this.clearHDCT();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnXongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXongActionPerformed
        this.clearHDCT();
        tabs.setSelectedIndex(0);
        tabs.setEnabledAt(1, false);
        this.clear();
        lblTongTien.setText("0.0");
        MsgBox.showMessageDialog(this, "Hoàn thành");
        clearTableHDCT();
    }//GEN-LAST:event_btnXongActionPerformed

    private void cboLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiSPActionPerformed
        this.fillComboBoxSP();
    }//GEN-LAST:event_cboLoaiSPActionPerformed

    private void themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themActionPerformed
        StringBuilder sb= new StringBuilder();
        checkSDT(txtSODIENTHOAI, sb);

        if (txtSODIENTHOAI.getText().isEmpty() || txtTen.getText().isEmpty()) {
            MsgBox.showMessageDialog(this, "Bạn chưa nhập đầy đủ thông tin !");
        }else{
            if (sb.length() > 0) {
                JOptionPane.showMessageDialog(this, sb.toString(), "INVALID DATA !", JOptionPane.ERROR_MESSAGE);
                return;
            }
            this.insertKH();
            txtTen.setText("");
            txtSODIENTHOAI.setText("");
        }

    }//GEN-LAST:event_themActionPerformed

    private void huyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_huyActionPerformed
        themKH.dispose();
        txtTen.setText("");
        txtSODIENTHOAI.setText("");
    }//GEN-LAST:event_huyActionPerformed

    private void tbHDCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHDCTMouseClicked
        if (evt.getClickCount() == 1) {
            this.rowhdct = tbHDCT.rowAtPoint(evt.getPoint());
            if (this.row >= 0) {
                this.editHDCT();
                this.TinhTien();
            }
        }
    }//GEN-LAST:event_tbHDCTMouseClicked
    
    public boolean isEmtyHDCT() {//kiểm tra nhập liệu để trống
        if (txtSoLuong1.getText().isEmpty() || txtGiamGia.getText().isEmpty()) {
            return false;
        }
        return true;
    }
    
    
    void TinhTien(){
        if (txtDonGia.getText().isEmpty() || txtSoLuong1.getText().isEmpty() || txtGiamGia.getText().isEmpty()) {
            return;
        }
        float dongia = Float.parseFloat(txtDonGia.getText());
        int soluong = Integer.parseInt(txtSoLuong1.getText());
        float giamgia = Float.parseFloat(txtGiamGia.getText());
        
        float tong = ((dongia * soluong)*(100-giamgia)/100);
        String thanhtien = String.format("%.2f", tong);
        txtThanhTien.setText(thanhtien);
        lblTongTien.setText(txtThanhTien.getText());
    }
    
    public static boolean checkSDT(JTextField field, StringBuilder sb) {
        boolean ok = true;

        Pattern pattern = Pattern.compile("(0?)(3[2-9]|5[6|8|9]"
                + "|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$"); //biểu thức chính quy số điện thoại VN tìm từ trên mạng
//        Pattern pattern = Pattern.compile("^[0-9._-]{10,11}$"); phải là số và có từ 10 đến 11 kí tự
        
        Matcher matcher = pattern.matcher(field.getText());

        if (!matcher.find()) {
            sb.append("Số điện thoại không hợp lệ");
            ok = false;
        }
        return ok;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Area;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDone;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnFirst1;
    private javax.swing.JButton btnIn;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnLast1;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnNext1;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXong;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboLoaiSP;
    private javax.swing.JComboBox<String> cboSanPham;
    private javax.swing.JComboBox<String> cboTenKH;
    private javax.swing.JCheckBox chkShow;
    private javax.swing.JButton huy;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel jblTenNV;
    private javax.swing.JLabel jlbDiaChi;
    private javax.swing.JLabel jlbMaHD;
    private javax.swing.JLabel jlbMaKh;
    private javax.swing.JLabel jlbMaNV;
    private javax.swing.JLabel jlbNgayBan;
    private javax.swing.JLabel jlbSDT;
    private javax.swing.JLabel jlbTenKH;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblDonGIa;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblHDCT;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblHinh1;
    private javax.swing.JLabel lblHinh2;
    private javax.swing.JLabel lblHinh3;
    private javax.swing.JLabel lblMaSP;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblTenSP;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JLabel lblThemKH;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JPanel pnlDanhSachSP;
    private javax.swing.JPanel pnlHinh;
    private javax.swing.JPanel pnlThongTinChiTiet;
    private javax.swing.JPanel pnlThongTinChung;
    private javax.swing.JPanel pnlThongTinSP;
    private javax.swing.JDialog print_Dialog;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tbHDCT;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JButton them;
    private javax.swing.JDialog themKH;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMaSP1;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtNgayBan;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSODIENTHOAI;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtSoLuong1;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

    SanPhamDAO dao = new SanPhamDAO();
    GioHangDAO ghDao = new GioHangDAO();
    HoaDonDAO hdDao = new HoaDonDAO();
    int row = -1;

    private void init() {
        this.row = -1;
        this.clearForm();
        this.fillTable();
        this.updateStatus();
        fillComboBoxKH();
//        fillComboBoxSP();
        fillComboBoxDanhMucSP();
        this.clear();
        fillTableHoaDon();
        tabs.setEnabledAt(3, false);
        viewTableHDCT();
        print_Dialog.setLocation(350, 80);
        themKH.setLocationRelativeTo(null);
    }
    
    
    

    private void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblCustomer.getModel();
        model.setRowCount(0);
        try {
            List<SanPham> list = dao.selectAll();
            for (SanPham sp : list) {
                Object[] row = {
                    sp.getMASP(), sp.getTENSP(), sp.getGIA(), sp.getSOLUONG(), sp.getMOTA(), sp.getHINH(), sp.getHINH1(), sp.getHINH2(), sp.getHINH3()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }

//        DefaultTableModel model = (DefaultTableModel) tblCustomer.getModel();
//        model.setRowCount(0);
//        try {
//            String keyword = txtTimKiem.getText();
//            List<SanPham> list= dao.selectByKeyword(keyword);
//            list.forEach(sp -> {
//                Object[] tempRow = {sp.getMASP(), sp.getTENSP(), sp.getGIA(), sp.getSOLUONG(), sp.getMOTA(), sp.getHINH()
//                };
//                model.addRow(tempRow);
//            });
//        } catch (Exception e) {
//            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
//        }
    }

    private void clearForm() {
        SanPham sp = new SanPham();
        this.setForm(sp);
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtGia.setText("");
        txtSoLuong.setText("");
        txtMoTa.setText("");
        lblHinh.setIcon(null);
        lblHinh1.setIcon(null);
        lblHinh2.setIcon(null);
        lblHinh3.setIcon(null);
        this.row = -1;
        this.updateStatus();
    }

    void edit() {
        String masp = (String) tblCustomer.getValueAt(this.row, 0);
        SanPham sp = dao.selectById(masp);
        this.setForm(sp);
        tabs.setSelectedIndex(0);
        this.updateStatus();

    }
    

    private void setDefault() {
        try {
            lblHinh.setToolTipText(null);
            XImage.setDefaultImage(lblHinh);
        } catch (IOException ex) {
            DialogHelper.alert(this, "Lỗi tải hình ảnh");
        }
    }

    private void updateStatus() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblCustomer.getRowCount() - 1);
        // trạng thái form 
        txtMaSP.setEditable(!edit);
        txtTenSP.setEditable(!edit);
        //trạng thái điều hướng
        btnFirst.setEnabled(edit && !first);
        btnPre.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }

    private void setForm(SanPham sp) {
        txtMaSP.setText(sp.getMASP());
        txtTenSP.setText(sp.getTENSP());
        txtGia.setText(String.valueOf(sp.getGIA()));
        txtSoLuong.setText(String.valueOf(sp.getSOLUONG()));
        txtMoTa.setText(sp.getMOTA());
        if (sp.getHINH() != null && sp.getHINH() != null && sp.getHINH() != null && sp.getHINH() != null) {
            try {
                lblHinh.setToolTipText(sp.getHINH());
                lblHinh1.setToolTipText(sp.getHINH1());
                lblHinh2.setToolTipText(sp.getHINH2());
                lblHinh3.setToolTipText(sp.getHINH3());
                lblHinh.setIcon(XImage.readAndResize(lblHinh, sp.getHINH()));
                lblHinh1.setIcon(XImage.readAndResize(lblHinh1, sp.getHINH1()));
                lblHinh2.setIcon(XImage.readAndResize(lblHinh2, sp.getHINH2()));
                lblHinh3.setIcon(XImage.readAndResize(lblHinh3, sp.getHINH3()));
            } catch (IOException ex) {
                setDefault();
                DialogHelper.alert(this, "Lỗi tải hình ảnh 11");
            }
        } else {
            lblHinh.setToolTipText(null);
        }
    }
    
    
    
    
    
    
    
    

    ///San Pham
    private FormHome home;
    


    private Point getLocationOf(Component com) {
        Point p = home.getPanelItemLocation();
        int x = p.x;
        int y = p.y;
        int itemX = com.getX();
        int itemY = com.getY();
        int left = 10;
        int top = 35;
        return new Point(x + itemX + left, y + itemY + top);
    }
    
    
    
    /////////////////////////////////////////////////////
    
    void fillComboBoxKH() { //đổ dữ liệu danh mục sản phẩm lên combobox
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTenKH.getModel(); //kết nối model với cbo
        model.removeAllElements();   //xóa toàn bộ item của cbo
        try {
            List<KhachHang> list = khdao.selectAll();
            for (KhachHang kh : list) {
                model.addElement(kh);    //thêm đối tượng (Object) vào model
            }
        } catch (Exception e) {
            MsgBox.showMessageDialog(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    
    
//    void chonKhachHang() {
//        KhachHang kh = (KhachHang) cboTenKH.getSelectedItem(); 
////        if (!kh.isThanhVien()) {
////            txtGiamGia.setText("0");
////        }else{
////            txtGiamGia.setText("10");
////        }
//        //lấy 1 Object được chọn từ combobox
//        //có thể điền và lấy 1 Object từ combobox
//        txtMaKH.setText(kh.getMaKH());
//        txtDiaChi.setText(kh.getDiaChi());
//        txtSDT.setText(kh.getSDT());    
////        fillTableHoaDonKH();
//    }
    
    
    HoaDon getModel() {    //lấy thông tin hoadon từ form, return hoadon
        HoaDon hd = new HoaDon();
        KhachHang kh = (KhachHang) cboTenKH.getSelectedItem();
        hd.setMaKH(kh.getMaKH());
        //hd.setMaNV(Auth.user.getMaNV());
        hd.setNgayBan(XDate.toDate(txtNgayBan.getText(),"dd/MM/yyyy"));
//        hd.setMaHDBan(txtMaHD.getText());
        
        return hd;
    }
    
    void setModel(HoaDon hd) {    //điền thông tin từ đt hoadon vào form
        txtMaHD.setText(hd.getMaHDBan());
        //lưu ý thêm getModel() khi áp dụng với đối tượng, ko cần thêm khi dùng với String VD: cbo.setSelectedItem("Item A");
        //tìm đt khachHang theo maKH rồi setSelectedItem cho combobox
        txtMaNV.setText(hd.getMaNV());
        txtNgayBan.setText(XDate.toString(hd.getNgayBan(),"dd/MM/yyyy"));
        cboTenKH.getModel().setSelectedItem(khdao.selectById(hd.getMaKH())); 
    }
    
    // đổ toàn bộ hoadon theo khachhang từ CSDL vào bảng
//    void fillTableHoaDonKH() {//đổ danh sách hoadon theo makh vao bang
//        DefaultTableModel model = (DefaultTableModel) tbHoaDon.getModel();
//        model.setRowCount(0);
//            KhachHang kh = (KhachHang) cboTenKH.getSelectedItem(); //lấy khóa học 
//            List<Object[]> list = hddao.getHoaDonTheoKH(kh.getMaKH()); //gọi store procedure sp_bangdiem (lấy theo mã khóa học)
//            for (Object[] row : list) { //cho vòng lặp duyệt qua mảng líst
//                model.addRow(new Object[]{row[0], row[1], row[2], row[3], row[4]});
//        }
//    }
    
    void fillTableHoaDon() {//đổ danh sách hóa đơn
        DefaultTableModel model = (DefaultTableModel) tbHoaDon.getModel();
        model.setRowCount(0);
        List<Object[]> list = hddao.getHoaDon();//gọi store procedure sp_HoaDon
            for (Object[] row : list) {//cho vòng lặp duyệt qua mảng líst
               model.addRow(row);
        }
    }
    
    void fillTableHoaDonHomNay() {//đổ danh sách hóa đơn
        DefaultTableModel model = (DefaultTableModel) tbHoaDon.getModel();
        model.setRowCount(0);
        String ngay = chkShow.getToolTipText();
        List<Object[]> list = hddao.getHoaDonTheoNgay(ngay);
            for (Object[] row : list) {//cho vòng lặp duyệt qua mảng líst
               model.addRow(row);
        }
    }
    
    void insertHĐ() {
     /*
    lấy thông tin trên form cho vào đt hoadon
    thêm đt hoadon vào CSDL, load lại bảng
    xóa trắng form, để ở insertable
    */    
        HoaDon hd = getModel();
        hd.setNgayBan(XDate.now());
        try {
            if (MsgBox.showConfirmDialog(this, "Tiếp tục thêm sản phẩm vào hóa đơn của bạn ?")) {
                hddao.insert(hd);
                this.getHDVuaThem();
                this.fillTableHoaDon();
                tabs.setSelectedIndex(1);
                tabs.setEnabledAt(0, false);
                this.clearHDCT();
            }
            return;
//            hddao.insert(hd);
//            this.fillTableHoaDon();
//            tab.setSelectedIndex(1);
//            MsgBox.showMessageDialog(this, "Mời thêm chi tiết hóa đơn");
        } catch (HeadlessException e) {
            MsgBox.showMessageDialog(this, "Thêm mới hóa đơn thất bại!");
        }
    }
    
      void updateHD(){
    //lấy thông tin trên form để
    //cập nhật hoadon theo MaHDBan
          HoaDon model = getModel();
          try {
              hddao.update(model);
              this.fillTableHDCTbyMaHD();
              this.clear();
              if (chkShow.isSelected()) {
                    this.fillTableHoaDon();
                }else{
                    this.fillTableHoaDonHomNay();
                }
              MsgBox.showMessageDialog(this, "Cập khách hóa đơn thành công");
              
          } catch (Exception e) {
              MsgBox.showMessageDialog(this, "Cập nhật hóa đơn thất bại !");
              e.printStackTrace();
          }
      }
      
    void deleteHĐ(){
    //lấy hoadon trên form, xóa sản phẩm theo MaHDBan
    //xóa trắng form
              String maHD = txtMaHD.getText();
              if  (MsgBox.showConfirmDialog(this, "Bạn chắc chắn muốn xóa hóa đơn này ?")) {
                  try {
                    hddao.delete(maHD);
                    this.fillTableHDCTbyMaHD();
                    this.clear();
                    
                    if (chkShow.isSelected()) {
                        this.fillTableHoaDon();
                    }else{
                        this.fillTableHoaDonHomNay();
                    }
                    MsgBox.showMessageDialog(this, "Xóa hóa đơn này thành công !");
                  }catch (Exception e) {
                    MsgBox.showMessageDialog(this, "Không thể xóa hóa đơn này !"); 
                    e.printStackTrace();
            }
        }              
    }
    
    void clear() {//xóa trắng form, chuyển sang insertable
        HoaDon model = new HoaDon();
        //txtTenNV.setText(Auth.user.getTenNV());
        KhachHang khachHang = (KhachHang) cboTenKH.getSelectedItem();//lấy chuyenDe đang đc chọn ở combobox
//        model.setMaKH(khachHang.getMaKH());
        //model.setMaNV(Auth.user.getMaNV());   //người tạo là nhanVien đang đăng nhập
        model.setMaHDBan(model.getMaHDBan());
        model.setNgayBan(XDate.now());   //ngày tạo là ngày hiện tại
//        fillTableHoaDon();
        this.setModel(model);
        this.updateStatusHD();
        txtMaHD.setEditable(false);
        btnSave.setEnabled(true);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnFirst.setEnabled(false);
        btnNext.setEnabled(false);
        btnPrev.setEnabled(false);
        btnLast.setEnabled(false);
    }
    
    
    void editHD() {
    //lấy mahodonban từ bảng theo index, lấy đt hoadon từ CSDL theo mahoadonban
    // đưa thông tin từ đt hoadon lên form, chuyển sang editable    
//        setTrang();
        try {
            String mahd = (String) tbHoaDon.getValueAt(this.row, 0);
            HoaDon model = hddao.selectById(mahd);
            loadTongTien(mahd);
            if (model != null) {
                this.setModel(model);
                this.updateStatusHD();
            }
        } catch (Exception e) {
            MsgBox.showMessageDialog(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    
    void updateStatusHD(){ //cập nhật trạng thái form
          boolean edit = (this.row >= 0); //đang chọn một hàng nào đó ->> trạng thái edit
          boolean first = (this.row == 0); //đang ở bản ghi đầu tiên 
          boolean last = (this.row == tbHoaDon.getRowCount() - 1); // đang ở bản ghi cuối cùng    
          
          //trạng thái form
          txtMaHD.setEditable(!edit);
          btnSave.setEnabled(!edit);
          btnEdit.setEnabled(edit);
          btnDelete.setEnabled(edit);
          
          //trạng thái điều hướng 
          btnFirst.setEnabled(edit && !first);
          btnPrev.setEnabled(edit && !first);
          btnNext.setEnabled(edit && !last);
          btnLast.setEnabled(edit && !last);
      }
    
    void first(){
          this.row = 0;
          this.edit();
          tbHoaDon.setRowSelectionInterval(row, row);
    }
    
    void prev(){
          if (this.row > 0) {
              this.row--;
              this.edit();
              tbHoaDon.setRowSelectionInterval(row, row);
          }
    }
      
    void next(){
           if (this.row < tbHoaDon.getRowCount() - 1) {
              this.row++;
              this.edit();
              tbHoaDon.setRowSelectionInterval(row, row);
          } 
    }
    
    void last(){
           this.row = tbHoaDon.getRowCount() -1 ;
           this.edit();
           tbHoaDon.setRowSelectionInterval(row, row);
    }
    
    public boolean checkTrungMa(JTextField txt) {//kiểm tra trùng mã nhân viên
        txt.setBackground(white);
        if (hddao.selectById(txt.getText()) == null) {
            return true;
        } else {
            txt.setBackground(Color.pink);
            MsgBox.showMessageDialog(this,"Mã hóa đơn "+ txt.getText()+ " đã tồn tại");
            return false;
        }
    }
    
    private void loadTongTien(String MaHD){
        Connection connec;
        String url;
        try {
            url= "jdbc:sqlserver://localhost:1433;databaseName=shopgiay_Dung;user=sa;password=123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connec= DriverManager.getConnection(url);
            
            String sql = "select MaHDBan,TongTien from DoanhThu_View where MaHDBan = ?";
            
            PreparedStatement pstmt = connec.prepareStatement(sql);
            
            pstmt.setString(1, MaHD);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                lblTongTien.setText(rs.getString("TongTien"));
            }
        } catch (Exception e) {
        }
    }
    
    void clearTableHDCT(){
        DefaultTableModel tbModel = (DefaultTableModel) tbHDCT.getModel ();
        tbModel.setRowCount (0);
        tbHDCT.revalidate();
    }
    
    void viewTableHDCT(){
        tbHDCT.getColumnModel().getColumn(0).setPreferredWidth(80);
        tbHDCT.getColumnModel().getColumn(1).setPreferredWidth(120);
        tbHDCT.getColumnModel().getColumn(2).setPreferredWidth(250);
        tbHDCT.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbHDCT.getColumnModel().getColumn(4).setPreferredWidth(90);
        tbHDCT.getColumnModel().getColumn(5).setPreferredWidth(90);
        tbHDCT.getColumnModel().getColumn(6).setPreferredWidth(110);
        tbHDCT.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
    }
    
    void showBill(String maHD){
        String maHDBan = txtMaHD.getText();
        String ngayBan = txtNgayBan.getText();
        String manv = txtMaNV.getText();
        String tenNV = txtTenNV.getText();
        String maKH = txtMaKH.getText();
        String tong = lblTongTien.getText();
//        String tenKH = cboTenKH.getSelectedItem();
        
        Area.setText(Area.getText()+"   *************************************************"
                + "***************************************************************\n");
        Area.setText(Area.getText()+"   * \t            CỬA HÀNG THỜI TRANG NIKE STORE                                   *\n");
        Area.setText(Area.getText()+"   ***********************************************"
                + "*****************************************************************\n");
        Area.setText(Area.getText()+("\n"));
        Area.setText(Area.getText()+("\n"));
        Area.setText(Area.getText()+("\t\tHÓA ĐƠN THANH TOÁN"));
        Area.setText(Area.getText()+("\n"));
        Area.setText(Area.getText()+("\n"));
        Area.setText(Area.getText()+("\n"));
        
        Area.setText(Area.getText()+"Mã hóa đơn         : "+maHDBan + "\t\t" + "Ngày bán              :"+"   "+ngayBan +"\n");
        Area.setText(Area.getText()+"Mã nhân viên       : "+"   "+manv + "\t\t" + "Tên nhân viên       :" +"   "+tenNV +"\n");
        Area.setText(Area.getText()+"Mã khách hàng    : "+"   "+maKH + "\t\t" + "Tên khách hàng    :" +"   "+cboTenKH.getSelectedItem() +"\n");
        Area.setText(Area.getText()+("\n"));
        Area.setText(Area.getText()+("\n"));
        Area.setText(Area.getText()+("\n"));
        
        DefaultTableModel model = (DefaultTableModel)tbHDCT.getModel();
        Area.setText(Area.getText()+("Tên sản phẩm"+"\t\t"+"Số lượng"+"\t"+"Đơn giá"+"\t"+"Giảm giá(%)"+"\t"+"Thành tiền"+"\n"));
        Area.setText(Area.getText()+("\n"));
        for (int i = 0; i < tbHDCT.getRowCount(); i++) {
            String TenSP = tbHDCT.getValueAt(i, 2).toString();
            String soLuong = tbHDCT.getValueAt(i, 3).toString();
            String donGia = tbHDCT.getValueAt(i, 4).toString();
            String giamGia = tbHDCT.getValueAt(i, 5).toString();
            String thanhTien = tbHDCT.getValueAt(i, 6).toString();
            
            Area.setText(Area.getText()+(TenSP+"\t"+soLuong+"\t"+donGia+"\t"+giamGia+"\t"+thanhTien+"\n"));
        }
        
            Area.setText(Area.getText()+("\n"));
            Area.setText(Area.getText()+("\n"));
            Area.setText(Area.getText()+("\n"));
            Area.setText(Area.getText()+("TỔNG TIỀN    :   "+tong+" VNĐ"+"\n"));
            
            Area.setText(Area.getText()+("\n"));
            Area.setText(Area.getText()+("\n"));
            Area.setText(Area.getText()+"   ****************************************************************************************************************\n");

            Area.setText(Area.getText()+("\tNIKE STORE CẢM ƠN VÀ HẸN GẶP LẠI QUÝ KHÁCH !"));
    }
    
    void printBill(){
        try {
            Area.print();
            MsgBox.showMessageDialog(this, "IN HÓA ĐƠN HOÀN TẤT !");
            tabs.setSelectedIndex(0);
            this.clearHDCT();
            this.clearTableHDCT();
            tabs.setEnabledAt(1, false);
            Area.setText("");
        } catch (Exception e) {
            MsgBox.showMessageDialog(this, "LỖI"+e.toString());
        }
    }
    
    KhachHang getForm(){    
    //lấy thông tin trên form cho vào đt khách hàng
    //return khách hàng
    
        KhachHang kh = new KhachHang();
        kh.setHoTen(txtTen.getText());
        kh.setSDT(txtSODIENTHOAI.getText());
        return kh;
    }
    
    void insertKH(){
    //lấy thông tin trên form để
    //thêm (đăng kí) khách hàng - quản lí vào CSDL
          KhachHang model = getForm();
          try {
              khdao.insert(model);
              MsgBox.showMessageDialog(this, "Thêm mới khách hàng thành công");
              themKH.dispose();
          } catch (Exception e) {
              MsgBox.showMessageDialog(this, "Thêm mới khách hàng thất bại !");
              e.printStackTrace();
          }
      }
    
    private void getHDVuaThem(){
        Connection connec;
        String url;
        try {
            url= "jdbc:sqlserver://localhost:1433;databaseName=shopgiay_Dung;user=sa;password=123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connec= DriverManager.getConnection(url);
            
            String sql = "SELECT TOP 1 MaHDBan FROM HoaDon ORDER BY MaHDBan DESC; ";
            
            PreparedStatement pstmt = connec.prepareStatement(sql);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                txtMaHD.setText(rs.getString("MaHDBan"));
            }
        } catch (Exception e) {
        }
    }
    
}

/////////////////////////////////////////////////////////////////////////

