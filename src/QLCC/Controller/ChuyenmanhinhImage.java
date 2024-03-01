/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLCC.Controller;

import QLCC.Been.DanhMucBeenImage;
import QLCCAdmin.UI.QLKH;
import QLCCAdmin.UI.QLNV;
import QLCCAdmin.UI.QLSP;
import QLCCAdmin.UI.QLTK1;
import QLCCAdmin.UI.QLTK;
import QLCCAdmin.UI.mainJPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class ChuyenmanhinhImage {

    private JPanel root;
    private String kindSelected = "";
    private java.util.List<DanhMucBeenImage> listItem = null;

    public ChuyenmanhinhImage(JPanel jpnRoot) {
        this.root = jpnRoot;
    }

    public void setView(JPanel jpnItem, JLabel jlbItem) {
        kindSelected = "Main";
//        jpnItem.setBackground(new Color(33, 96, 196));
//        jlbItem.setBackground(new Color(33, 96, 196));

        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new mainJPanel());
        root.validate();
        root.repaint();
    }

    public void setEvent(java.util.List<DanhMucBeenImage> listItem) {
        this.listItem = listItem;

        for (DanhMucBeenImage item : listItem) {
            item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
        }
    }

    public JPanel no;

    public void Button(java.util.List<DanhMucBeenImage> listItem2, String kind) {
        this.listItem = listItem2;
        switch (kind) {
            case "Main":
                no = new mainJPanel();
                break;
            case "QLKH":
                no = new QLKH();
                break;
            case "QLNV":
                no = new QLNV();
                break;
            case "QLSP":
                no = new QLSP();
                break;
            case "QLTK":
                no = new QLTK();
                break;
            case "QLTK1":
                no = new QLTK1();
                break;
            default:
                break;
        }
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(no);
        root.validate();
        root.repaint();
        setChageBackgroud(kind);

    }

    class LabelEvent implements MouseListener {

        private JPanel node;
        private String kind;
        private JPanel jpnItem;
        private JLabel jpbIItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jpbIItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jpbIItem = jpbIItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "Main":
                    node = new mainJPanel();
                    break;
                case "QLKH":
                    node = new QLKH();
                    break;
                case "QLNV":
                    node = new QLNV();
                    break;
                case "QLSP":
                    node = new QLSP();
                    break;
                case "QLTK1":
                    node = new QLTK1();
                    break;
                case "QLTK":
                    node = new QLTK();
                    break;
                default:
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChageBackgroud(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpbIItem.setBackground(new Color(136, 205, 246));
            //jpnItem.setBackground(new Color(136, 205, 246));
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpbIItem.setBackground(new Color(63, 134, 221));
            //jpnItem.setBackground(new Color(63, 134, 221));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)) {
                jpbIItem.setBackground(new Color(33, 96, 196));
                //jpnItem.setBackground(new Color(33, 96, 196));
            }
        }
    }

    private void setChageBackgroud(String kind) {
        for (DanhMucBeenImage item : listItem) {
            if (item.getKind().equalsIgnoreCase(kind)) {
                //item.getJpn().setBackground(new Color(63, 134, 221));
                item.getJlb().setBackground(new Color(63, 134, 221));
            } else {
                //item.getJpn().setBackground(new Color(33, 96, 196));
                item.getJlb().setBackground(new Color(33, 96, 196));
            }
        }
    }
}
