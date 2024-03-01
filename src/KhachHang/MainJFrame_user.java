/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package KhachHang;




import QLCC.Been.DanhMucBeenImage;
import java.util.ArrayList;



import java.util.List;


public class MainJFrame_user extends javax.swing.JFrame {

   
    
    public MainJFrame_user() {
        initComponents();  
        this.setLocationRelativeTo(null);
        ChuyenmanhinhIKh controller = new ChuyenmanhinhIKh(pnlView);
        controller.setView(pnlHome, lblHome);
        List<DanhMucBeenImage> listItem = new ArrayList<>();
        listItem.add(new DanhMucBeenImage("Main",pnlHome,lblHome));
        listItem.add(new DanhMucBeenImage("SP",pnlSanPham,lblSanPham));
        
        controller.setEvent(listItem);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblHome = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pnlHoTro = new javax.swing.JPanel();
        pnlStatisticManage1 = new javax.swing.JPanel();
        lblHoTro = new javax.swing.JLabel();
        pnlSanPham = new javax.swing.JPanel();
        pnlResidentManage1 = new javax.swing.JPanel();
        lblSanPham = new javax.swing.JLabel();
        pnlTaiKhoan = new javax.swing.JPanel();
        pnlStaffManage1 = new javax.swing.JPanel();
        lblTaiKhoan = new javax.swing.JLabel();
        pnlTaskbar = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnSignOut = new javax.swing.JLabel();
        pnlHelp = new javax.swing.JPanel();
        pnlHelp1 = new javax.swing.JPanel();
        pnlView = new javax.swing.JPanel();
        pnlHome = new javax.swing.JPanel();
        pnlCustomerManage2 = new javax.swing.JPanel();
        pnlCustomerManage3 = new javax.swing.JPanel();
        pnlFooter = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        lblDongHo = new javax.swing.JLabel();
        pnlFooter1 = new javax.swing.JPanel();
        btnExit = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 480));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 679));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home.png"))); // NOI18N
        lblHome.setAutoscrolls(true);
        lblHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHomeMouseClicked(evt);
            }
        });
        jPanel1.add(lblHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 140, 30));

        pnlHoTro.setBackground(new java.awt.Color(255, 255, 255));

        pnlStatisticManage1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout pnlStatisticManage1Layout = new javax.swing.GroupLayout(pnlStatisticManage1);
        pnlStatisticManage1.setLayout(pnlStatisticManage1Layout);
        pnlStatisticManage1Layout.setHorizontalGroup(
            pnlStatisticManage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        pnlStatisticManage1Layout.setVerticalGroup(
            pnlStatisticManage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblHoTro.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblHoTro.setForeground(new java.awt.Color(153, 153, 153));
        lblHoTro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHoTro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/update.jpg"))); // NOI18N
        lblHoTro.setText("Hỗ trợ ");
        lblHoTro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlHoTroLayout = new javax.swing.GroupLayout(pnlHoTro);
        pnlHoTro.setLayout(pnlHoTroLayout);
        pnlHoTroLayout.setHorizontalGroup(
            pnlHoTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoTroLayout.createSequentialGroup()
                .addComponent(pnlStatisticManage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblHoTro, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 44, Short.MAX_VALUE))
        );
        pnlHoTroLayout.setVerticalGroup(
            pnlHoTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlStatisticManage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHoTroLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblHoTro)
                .addContainerGap())
        );

        jPanel1.add(pnlHoTro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        pnlSanPham.setBackground(new java.awt.Color(255, 255, 255));

        pnlResidentManage1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout pnlResidentManage1Layout = new javax.swing.GroupLayout(pnlResidentManage1);
        pnlResidentManage1.setLayout(pnlResidentManage1Layout);
        pnlResidentManage1Layout.setHorizontalGroup(
            pnlResidentManage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        pnlResidentManage1Layout.setVerticalGroup(
            pnlResidentManage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSanPham.setForeground(new java.awt.Color(153, 153, 153));
        lblSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/shoes.png"))); // NOI18N
        lblSanPham.setText("Sản phẩm");
        lblSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlSanPhamLayout = new javax.swing.GroupLayout(pnlSanPham);
        pnlSanPham.setLayout(pnlSanPhamLayout);
        pnlSanPhamLayout.setHorizontalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addComponent(pnlResidentManage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblSanPham)
                .addGap(0, 71, Short.MAX_VALUE))
        );
        pnlSanPhamLayout.setVerticalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlResidentManage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSanPhamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSanPham)
                .addContainerGap())
        );

        jPanel1.add(pnlSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        pnlTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));

        pnlStaffManage1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout pnlStaffManage1Layout = new javax.swing.GroupLayout(pnlStaffManage1);
        pnlStaffManage1.setLayout(pnlStaffManage1Layout);
        pnlStaffManage1Layout.setHorizontalGroup(
            pnlStaffManage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        pnlStaffManage1Layout.setVerticalGroup(
            pnlStaffManage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTaiKhoan.setForeground(new java.awt.Color(153, 153, 153));
        lblTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Staff 1.png"))); // NOI18N
        lblTaiKhoan.setText("Tài Khoản");
        lblTaiKhoan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlTaiKhoanLayout = new javax.swing.GroupLayout(pnlTaiKhoan);
        pnlTaiKhoan.setLayout(pnlTaiKhoanLayout);
        pnlTaiKhoanLayout.setHorizontalGroup(
            pnlTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTaiKhoanLayout.createSequentialGroup()
                .addComponent(pnlStaffManage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 59, Short.MAX_VALUE))
        );
        pnlTaiKhoanLayout.setVerticalGroup(
            pnlTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlStaffManage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTaiKhoanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTaiKhoan)
                .addContainerGap())
        );

        jPanel1.add(pnlTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        pnlTaskbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pnlTaskbar.png"))); // NOI18N
        jPanel1.add(pnlTaskbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 580));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/find 1.png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1749, 30, 30, 25));

        btnSignOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/btnSignOut.png"))); // NOI18N
        btnSignOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSignOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSignOutMouseClicked(evt);
            }
        });
        jPanel1.add(btnSignOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(1899, 20, -1, 45));

        pnlHelp.setBackground(new java.awt.Color(255, 255, 255));

        pnlHelp1.setBackground(new java.awt.Color(93, 147, 84));

        javax.swing.GroupLayout pnlHelp1Layout = new javax.swing.GroupLayout(pnlHelp1);
        pnlHelp1.setLayout(pnlHelp1Layout);
        pnlHelp1Layout.setHorizontalGroup(
            pnlHelp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        pnlHelp1Layout.setVerticalGroup(
            pnlHelp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlHelpLayout = new javax.swing.GroupLayout(pnlHelp);
        pnlHelp.setLayout(pnlHelpLayout);
        pnlHelpLayout.setHorizontalGroup(
            pnlHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHelpLayout.createSequentialGroup()
                .addComponent(pnlHelp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlHelpLayout.setVerticalGroup(
            pnlHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHelp1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(pnlHelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnlView.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlHomeLayout = new javax.swing.GroupLayout(pnlHome);
        pnlHome.setLayout(pnlHomeLayout);
        pnlHomeLayout.setHorizontalGroup(
            pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        pnlHomeLayout.setVerticalGroup(
            pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlViewLayout = new javax.swing.GroupLayout(pnlView);
        pnlView.setLayout(pnlViewLayout);
        pnlViewLayout.setHorizontalGroup(
            pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlViewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(944, Short.MAX_VALUE))
        );
        pnlViewLayout.setVerticalGroup(
            pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlViewLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(pnlHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(453, Short.MAX_VALUE))
        );

        jPanel1.add(pnlView, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 1050, 570));

        pnlCustomerManage2.setBackground(new java.awt.Color(255, 255, 255));

        pnlCustomerManage3.setBackground(new java.awt.Color(93, 147, 84));

        javax.swing.GroupLayout pnlCustomerManage3Layout = new javax.swing.GroupLayout(pnlCustomerManage3);
        pnlCustomerManage3.setLayout(pnlCustomerManage3Layout);
        pnlCustomerManage3Layout.setHorizontalGroup(
            pnlCustomerManage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        pnlCustomerManage3Layout.setVerticalGroup(
            pnlCustomerManage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlCustomerManage2Layout = new javax.swing.GroupLayout(pnlCustomerManage2);
        pnlCustomerManage2.setLayout(pnlCustomerManage2Layout);
        pnlCustomerManage2Layout.setHorizontalGroup(
            pnlCustomerManage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCustomerManage2Layout.createSequentialGroup()
                .addComponent(pnlCustomerManage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlCustomerManage2Layout.setVerticalGroup(
            pnlCustomerManage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCustomerManage3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(pnlCustomerManage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1250, 590));

        pnlFooter.setBackground(new java.awt.Color(102, 102, 102));

        jLabel2.setBackground(new java.awt.Color(236, 236, 236));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(236, 236, 236));
        jLabel2.setText("Name: ");

        lblName.setBackground(new java.awt.Color(236, 236, 236));
        lblName.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblName.setForeground(new java.awt.Color(236, 236, 236));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(236, 236, 236));
        jLabel3.setText("Role:");

        lblRole.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblRole.setForeground(new java.awt.Color(236, 236, 236));

        lblDongHo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDongHo.setForeground(new java.awt.Color(236, 236, 236));

        javax.swing.GroupLayout pnlFooterLayout = new javax.swing.GroupLayout(pnlFooter);
        pnlFooter.setLayout(pnlFooterLayout);
        pnlFooterLayout.setHorizontalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFooterLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(44, 44, 44)
                .addComponent(lblRole)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 929, Short.MAX_VALUE)
                .addComponent(lblDongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        pnlFooterLayout.setVerticalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(lblName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblRole, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblDongHo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(pnlFooter, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 620, 1270, 40));

        pnlFooter1.setBackground(new java.awt.Color(0, 204, 204));

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close1.png"))); // NOI18N
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExitMouseExited(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(153, 153, 153));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("QUẢN LÝ BÁN GIÀY DÉP");

        javax.swing.GroupLayout pnlFooter1Layout = new javax.swing.GroupLayout(pnlFooter1);
        pnlFooter1.setLayout(pnlFooter1Layout);
        pnlFooter1Layout.setHorizontalGroup(
            pnlFooter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFooter1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1156, Short.MAX_VALUE)
                .addGap(56, 56, 56)
                .addComponent(btnExit)
                .addContainerGap())
        );
        pnlFooter1Layout.setVerticalGroup(
            pnlFooter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFooter1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlFooter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        getContentPane().add(pnlFooter1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        // TODO add your handling code here:
        System.exit(0);
        
       
    }//GEN-LAST:event_btnExitMouseClicked

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExitMouseExited

    private void txtSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMouseClicked
        // TODO add your handling code here:
        


    }//GEN-LAST:event_txtSearchMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        

    }//GEN-LAST:event_txtSearchKeyReleased

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        
    }//GEN-LAST:event_jLabel8MouseClicked

    private void btnSignOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSignOutMouseClicked
        // TODO add your handling code here:
        this.dispose();

    }//GEN-LAST:event_btnSignOutMouseClicked

    private void lblHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHomeMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_lblHomeMouseClicked

    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame_user().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnExit;
    private javax.swing.JLabel btnSignOut;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JLabel lblHoTro;
    private javax.swing.JLabel lblHome;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JPanel pnlCustomerManage2;
    private javax.swing.JPanel pnlCustomerManage3;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JPanel pnlFooter1;
    private javax.swing.JPanel pnlHelp;
    private javax.swing.JPanel pnlHelp1;
    private javax.swing.JPanel pnlHoTro;
    private javax.swing.JPanel pnlHome;
    private javax.swing.JPanel pnlResidentManage1;
    private javax.swing.JPanel pnlSanPham;
    private javax.swing.JPanel pnlStaffManage1;
    private javax.swing.JPanel pnlStatisticManage1;
    private javax.swing.JPanel pnlTaiKhoan;
    private javax.swing.JLabel pnlTaskbar;
    private javax.swing.JPanel pnlView;
    // End of variables declaration//GEN-END:variables

}