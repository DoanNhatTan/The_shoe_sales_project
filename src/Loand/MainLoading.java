package Loand;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class MainLoading extends javax.swing.JDialog {
    public MainLoading(java.awt.Frame parent, boolean modal) {
    super(parent,modal);
        setUndecorated(true);
        initComponents();

        new Timer(20,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = jbl_loand.getValue();
                if(i < 100){
                   jbl_loand.setValue(i + 1);
                if(i == 10)
                    lblLoading.setText("Turning On Modules ...");
                if(i == 20)
                    lblLoading.setText("Loading Modules ...");
                if(i == 50)
                    lblLoading.setText("Connecting to Database ...");
                if(i == 70)
                    lblLoading.setText("Connection Successful ...");
                if(i == 80)
                    lblLoading.setText("Launching Application ...");
                }else {
                    MainLoading.this.dispose();

                }
            }
        }).start();
        this.setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblName1 = new javax.swing.JLabel();
        lblinfo = new javax.swing.JLabel();
        lblLoading = new javax.swing.JLabel();
        jbl_loand = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblName1.setBackground(new java.awt.Color(255, 255, 255));
        lblName1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblName1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblName1.setText("ShopGiay");
        jPanel1.add(lblName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 372, 1024, -1));

        lblinfo.setFont(new java.awt.Font("Genshin", 1, 24)); // NOI18N
        lblinfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblinfo.setText("AN TOÀN - CHẤT LƯỢNG - UY TÍN");
        jPanel1.add(lblinfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 436, 1024, -1));

        lblLoading.setBackground(new java.awt.Color(255, 255, 255));
        lblLoading.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblLoading.setText("Loading ...");
        jPanel1.add(lblLoading, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 474, -1, -1));

        jbl_loand.setStringPainted(true);
        jPanel1.add(jbl_loand, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 474, 840, 32));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/backgroudTrangChinh.jpeg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, -1, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainLoading dialog = new MainLoading(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jbl_loand;
    private javax.swing.JLabel lblLoading;
    private javax.swing.JLabel lblName1;
    private javax.swing.JLabel lblinfo;
    // End of variables declaration//GEN-END:variables
}
