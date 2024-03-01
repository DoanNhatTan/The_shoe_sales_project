/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package KhachHang;


import BH.event.EventItem;
import BH.form.FormHome;
import BH.model.ModelItem;
import KhachHangDAO.SanPhamDAO;
import SanPham.entity.SanPham;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.animation.timing.Animator;
import until.DialogHelper;
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
        jPanel1 = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        background1 = new BH.swing.Background();
        mainPanel = new BH.swing.MainPanel();
        jPanel2 = new javax.swing.JPanel();
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
        jLabel1 = new javax.swing.JLabel();
        btnBuy = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1117, 721));

        tabs.setBackground(new java.awt.Color(255, 255, 255));
        tabs.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tabs.setPreferredSize(new java.awt.Dimension(828, 370));

        mainPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainPanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1004, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 516, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(background1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tabs.addTab("Product_Image", jPanel4);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

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

        lblHinh2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinh2.setToolTipText("");
        lblHinh2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblHinh3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinh3.setToolTipText("");
        lblHinh3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout pnlHinhLayout = new javax.swing.GroupLayout(pnlHinh);
        pnlHinh.setLayout(pnlHinhLayout);
        pnlHinhLayout.setHorizontalGroup(
            pnlHinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHinhLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lblHinh1, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
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
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel1.setEnabled(false);

        btnBuy.setText("Buy");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(401, 401, 401)
                .addComponent(btnBuy)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(pnlHinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
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
                        .addGap(0, 97, Short.MAX_VALUE))))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addGap(27, 27, 27)
                .addComponent(btnBuy)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tabs.addTab("Product", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Giá", "Số lượng", "Mô tả", "Hình"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(259, 259, 259)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(123, 123, 123)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 105, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 917, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        tabs.addTab("List_Product", jPanel3);

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
                .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSPActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void tblCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            this.row = tblCustomer.getSelectedRow();
            this.edit();
        }
        
    }//GEN-LAST:event_tblCustomerMouseClicked

    private void jLabel24AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel24AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel24AncestorAdded

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
//        int ret = JOptionPane.showConfirmDialog(this, "Bạn có muốn lưu thành file Excel không", "Confirm",
//                JOptionPane.YES_NO_OPTION);
//        if (ret == JOptionPane.YES_OPTION) {
//            exportExcel(tblCustomer);
//            return;
//        }        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel24MouseClicked

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
              // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemKeyReleased

   



    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        // TODO add your handling code here:
       
        //        int rs = f.showOpenDialog(null);
    }//GEN-LAST:event_lblHinhMouseClicked

    private void btnLastMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLastMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLastMouseClicked

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextMouseClicked

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPreMouseClicked

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnFirstMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFirstMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFirstMouseClicked

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFirstActionPerformed

    private void mainPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_mainPanelMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private BH.swing.Background background1;
    private javax.swing.JButton btnBuy;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblHinh1;
    private javax.swing.JLabel lblHinh2;
    private javax.swing.JLabel lblHinh3;
    private BH.swing.MainPanel mainPanel;
    private javax.swing.JPanel pnlHinh;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

    SanPhamDAO dao=new SanPhamDAO();
    int row=-1;
    
    private void init() {
        this.row = -1;
        this.clearForm();
        this.fillTable();
        this.updateStatus();
        
        ///SanPham init
        home = new FormHome();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(home);
        testData();
    }
    
    private void fillTable() {
//        DefaultTableModel model = (DefaultTableModel) tblDS_SANPHAM.getModel();
//        model.setRowCount(0);
//        try{
//            List<SANPHAM> list = dao.selectAll();
//            for(SANPHAM sp : list){
//                Object[] row = {
//                    sp.getMASP(), sp.getTENSP(), sp.getGIA(), sp.getSOLUONG(), sp.getNGAYNHAP(), sp.getHANSUDUNG(), sp.getXUATXU(), sp.getMOTA(), sp.getMAND(), sp.getHINH()
//                };
//                model.addRow(row);
//            }
//        }catch(Exception e){
//            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
//        }
        
        DefaultTableModel model = (DefaultTableModel) tblCustomer.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTimKiem.getText();
            List<SanPham> list= dao.selectByKeyword(keyword);
            list.forEach(sp -> {
                Object[] tempRow = {sp.getMASP(), sp.getTENSP(), sp.getGIA(), sp.getSOLUONG(), sp.getMOTA(), sp.getHINH()
                };
                model.addRow(tempRow);
            });
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
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
        this.row = -1;
        this.updateStatus();
    }
    
    void edit(){
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
    
    private void updateStatus(){
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblCustomer.getRowCount() -1);
        // trạng thái form 
        txtMaSP.setEditable(!edit);
        txtTenSP.setEditable(!edit);
        //trạng thái điều hướng
        btnFirst.setEnabled(edit && !first);
        btnPre.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }
    
    private void setForm(SanPham sp){
        txtMaSP.setText(sp.getMASP());
        txtTenSP.setText(sp.getTENSP());
        txtGia.setText(String.valueOf(sp.getGIA()));
        txtSoLuong.setText(String.valueOf(sp.getSOLUONG()));
        txtMoTa.setText(sp.getMOTA());
        if (sp.getHINH()!= null) {
            try {
                lblHinh.setToolTipText(sp.getHINH());
                lblHinh.setIcon(XImage.readAndResize(lblHinh, sp.getHINH()));
            } catch (IOException ex) {
                setDefault();
                DialogHelper.alert(this, "Lỗi tải hình ảnh");
            }
        } else {
            lblHinh.setToolTipText(null);
        }
    }
    
    ///San Pham
    private FormHome home;
    private Animator animator;
    private Point animatePoint;
    private ModelItem itemSelected;
    
    private void testData() {
        home.setEvent(new EventItem() {
            @Override
            public void itemClick(Component com, ModelItem item) {
                if (itemSelected != null) {
                    mainPanel.setImageOld(itemSelected.getImage());
                }
                if (itemSelected != item) {
                    if (!animator.isRunning()) {
                        itemSelected = item;
                        animatePoint = getLocationOf(com);
                        mainPanel.setImage(item.getImage());
                        mainPanel.setImageLocation(animatePoint);
                        mainPanel.setImageSize(new Dimension(180, 120));
                        mainPanel.repaint();
                        home.setSelected(com);
                        animator.start();
                    }
                }
            }
        });
        int ID = 1;
        for (int i = 0; i <= 5; i++) {
            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is excluded from all promotional discounts and offers.", 160, "Adidas", new ImageIcon(getClass().getResource("/BH/image/img1.png"))));
            home.addItem(new ModelItem(ID++, "FORUM MID", "This product is excluded from all promotional discounts and offers.", 100, "Adidas", new ImageIcon(getClass().getResource("/BH/image/img2.png"))));
            home.addItem(new ModelItem(ID++, "SUPERNOVA", "NMD City Stock 2", 150, "Adidas", new ImageIcon(getClass().getResource("/BH/image/img3.png"))));
            home.addItem(new ModelItem(ID++, "Adidas", "NMD City Stock 2", 160, "Adidas", new ImageIcon(getClass().getResource("/BH/image/img4.png"))));
            home.addItem(new ModelItem(ID++, "Adidas", "NMD City Stock 2", 120, "Adidas", new ImageIcon(getClass().getResource("/BH/image/img5.png"))));
            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is excluded from all promotional discounts and offers.", 160, "Adidas", new ImageIcon(getClass().getResource("/BH/image/img6.png"))));
        }
        
    }

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
    
    
}


/////////////////////////////////////////////////////////////////////////

