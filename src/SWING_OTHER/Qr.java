/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SWING_OTHER;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
 
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author Administrator
 */
public class Qr extends JPanel {
    private ImageIcon icon;
 
    public Qr() {
 
        loadImage();
        initPanel();
    }
 
    private void loadImage() {
 
        icon = new ImageIcon("qr-code.png");
    }
 
    private void initPanel() {
 
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();
        setPreferredSize(new Dimension(w, h));
    }
 
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
 
        icon.paintIcon(this, g, 0, 0);
    }
    
    
}

