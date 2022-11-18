/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.form.giaodich;

import Service.Impl.ChiTietSachImpl;
import Services.ChiTietSachService;
import ViewModel.MatHang01;
import ViewModel.MatHangViewModel;
import java.awt.ActiveEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author ADMIN
 */
public class ViewMatHang extends javax.swing.JPanel {

    ViewThemMatHang themMatHang = new ViewThemMatHang();
    ChiTietSachService chitietsachService = new ChiTietSachImpl();
    List<MatHang01>  listmathang = new ArrayList<>();
    DefaultTableModel tbl_model = new DefaultTableModel();
    FormSuaMatHang suamatHang ;
    List<MatHangViewModel> ListMatHangViewModel = new ArrayList<>();
    
    
    
    
    
    public ViewMatHang() {
        initComponents();
        tbl_model = (DefaultTableModel) tbl_matHang.getModel();
        ListMatHangViewModel = chitietsachService.getlist();
        filldata01();
        rdo_tatca.setSelected(true);
        themMatHang.addFilltable(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                themMatHang.inerts();
                filldata01();
            }
        });
        
    }
    Locale lc = new Locale("vn", "VN");
    NumberFormat nf = NumberFormat.getInstance(lc);
    public void filldata01(){
        tbl_model.setRowCount(0);
        for (MatHangViewModel mh : ListMatHangViewModel) {
            tbl_model.addRow(new Object[]{
               mh.getIdchitietsach(), mh.getMasach(),mh.getTenSach(),nf.format(mh.getGiaban()) + " đ",mh.getTenNgonNgu(),mh.getTenTacGia(),mh.getTenNxb(),mh.getSoluongton()
            });
        }
    }
     public void filldata(){
        tbl_model.setRowCount(0);
        for (MatHang01 mh : listmathang) {
            tbl_model.addRow(new Object[]{
               mh.getIdCTSach(), mh.getMasach(),mh.getTensach(),nf.format(mh.getGiaBan()) + " đ",mh.getTenNgonNgu(),mh.getTenTacGia(),mh.getTenNxb(),mh.getSoLuong()
            });
        }
    }
    public void filldataLoc(){
//        tbl_model = (DefaultTableModel) tbl_matHang.getModel();
//        tbl_model.setRowCount(0);
        listmathang = chitietsachService.getlistConHang();
        filldata();
        
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
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textField1 = new View.form.TextField();
        myButton1 = new View.form.MyButton();
        myButton2 = new View.form.MyButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btn_dangKinhDoanh = new View.form.RadioButtonCustom();
        btn_NgungKinhDoanh = new View.form.RadioButtonCustom();
        jPanel6 = new javax.swing.JPanel();
        Rdo_TuThaypToiCao = new View.form.RadioButtonCustom();
        rdo_TuCaoToiThap = new View.form.RadioButtonCustom();
        jPanel7 = new javax.swing.JPanel();
        rdo_tatca = new View.form.RadioButtonCustom();
        radioButtonCustom2 = new View.form.RadioButtonCustom();
        rdo_hetHang = new View.form.RadioButtonCustom();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_matHang = new View.form.TableColumn();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Mặt Hàng");

        textField1.setLabelText("Tìm theo mã hoặc tên");

        myButton1.setText("Tìm");
        myButton1.setRadius(20);
        myButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton1ActionPerformed(evt);
            }
        });

        myButton2.setText("Thêm Mới");
        myButton2.setRadius(20);
        myButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addGap(139, 139, 139)
                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106)
                .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Trạng Thái"));

        buttonGroup1.add(btn_dangKinhDoanh);
        btn_dangKinhDoanh.setText("Đang Kinh Doanh");
        btn_dangKinhDoanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dangKinhDoanhActionPerformed(evt);
            }
        });

        buttonGroup1.add(btn_NgungKinhDoanh);
        btn_NgungKinhDoanh.setText("Ngừng Kinh Doanh");
        btn_NgungKinhDoanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NgungKinhDoanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_dangKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_NgungKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btn_dangKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btn_NgungKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Giá Bán"));

        buttonGroup1.add(Rdo_TuThaypToiCao);
        Rdo_TuThaypToiCao.setText("Từ thấp tới cao");
        Rdo_TuThaypToiCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rdo_TuThaypToiCaoActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdo_TuCaoToiThap);
        rdo_TuCaoToiThap.setText("Từ cao tới thấp");
        rdo_TuCaoToiThap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_TuCaoToiThapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdo_TuCaoToiThap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Rdo_TuThaypToiCao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Rdo_TuThaypToiCao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(rdo_TuCaoToiThap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Tồn Kho"));

        buttonGroup1.add(rdo_tatca);
        rdo_tatca.setText("Tất Cả");
        rdo_tatca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_tatcaActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioButtonCustom2);
        radioButtonCustom2.setText("Còn Hàng Trong Kho");
        radioButtonCustom2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonCustom2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdo_hetHang);
        rdo_hetHang.setText("Hết Hàng Trong Kho");
        rdo_hetHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_hetHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioButtonCustom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdo_hetHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdo_tatca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(rdo_tatca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(radioButtonCustom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(rdo_hetHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbl_matHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Ma", "Tên Sách", "Giá Bán", "Tên Ngôn Ngữ", "Tên Tác Giả", "Tên Nhà Xuất Bản", "Số Lượng Tồn"
            }
        ));
        tbl_matHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_matHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_matHang);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 975, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myButton1ActionPerformed

    private void myButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton2ActionPerformed
        // TODO add your handling code here:
       themMatHang.setVisible(true);
        
    }//GEN-LAST:event_myButton2ActionPerformed

    private void rdo_tatcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_tatcaActionPerformed
        // TODO add your handling code here:
        ListMatHangViewModel = chitietsachService.getlist();
        filldata01();
    }//GEN-LAST:event_rdo_tatcaActionPerformed

    private void radioButtonCustom2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonCustom2ActionPerformed
        filldataLoc();
    }//GEN-LAST:event_radioButtonCustom2ActionPerformed

    private void rdo_hetHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_hetHangActionPerformed
        // TODO add your handling code here:
        listmathang = chitietsachService.getlistHetHang();
        filldata();
    }//GEN-LAST:event_rdo_hetHangActionPerformed

    private void btn_dangKinhDoanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dangKinhDoanhActionPerformed
        // TODO add your handling code here:
        listmathang = chitietsachService.getlistDangKinhDoanh();
        filldata();
    }//GEN-LAST:event_btn_dangKinhDoanhActionPerformed

    private void btn_NgungKinhDoanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NgungKinhDoanhActionPerformed
        // TODO add your handling code here:
        listmathang = chitietsachService.getlistNgungKinhDoanh();
        filldata();
    }//GEN-LAST:event_btn_NgungKinhDoanhActionPerformed

    private void Rdo_TuThaypToiCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rdo_TuThaypToiCaoActionPerformed
        // TODO add your handling code here:
        listmathang = chitietsachService.getlistThapToiCao();
        filldata();
    }//GEN-LAST:event_Rdo_TuThaypToiCaoActionPerformed

    private void rdo_TuCaoToiThapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_TuCaoToiThapActionPerformed
        // TODO add your handling code here:
        listmathang = chitietsachService.getlistCaoToiThap();
        filldata();
    }//GEN-LAST:event_rdo_TuCaoToiThapActionPerformed

    private void tbl_matHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_matHangMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            int index = tbl_matHang.getSelectedRow();
            int idchitietSach =  (int) tbl_matHang.getValueAt(index, 0);
            ListMatHangViewModel = chitietsachService.getlist();
            float giaban = 0;
            for (int i = 0; i < ListMatHangViewModel.size(); i++) {
                if (ListMatHangViewModel.get(i).getIdchitietsach() == idchitietSach) {
                    giaban = ListMatHangViewModel.get(1).getGiaban();
                }
            }
              String tenSach = (String) tbl_matHang.getValueAt(index, 2);
               String tenNgonNgu = (String) tbl_matHang.getValueAt(index, 4);
                String tenTacGia = (String) tbl_matHang.getValueAt(index, 5);
                 String tenNhaXuatBan = (String) tbl_matHang.getValueAt(index, 6);
                 suamatHang = new FormSuaMatHang(tenSach, giaban, tenNgonNgu, tenTacGia, tenNhaXuatBan, idchitietSach);
                 suamatHang.themSuKienUpdate(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   suamatHang.update();
                   filldata();
                }
                 });
                 suamatHang.setVisible(true);
                      }
    }//GEN-LAST:event_tbl_matHangMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.form.RadioButtonCustom Rdo_TuThaypToiCao;
    private View.form.RadioButtonCustom btn_NgungKinhDoanh;
    private View.form.RadioButtonCustom btn_dangKinhDoanh;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private View.form.MyButton myButton1;
    private View.form.MyButton myButton2;
    private View.form.RadioButtonCustom radioButtonCustom2;
    private View.form.RadioButtonCustom rdo_TuCaoToiThap;
    private View.form.RadioButtonCustom rdo_hetHang;
    private View.form.RadioButtonCustom rdo_tatca;
    private View.form.TableColumn tbl_matHang;
    private View.form.TextField textField1;
    // End of variables declaration//GEN-END:variables
}
