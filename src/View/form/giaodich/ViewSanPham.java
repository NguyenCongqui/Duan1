/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.form.giaodich;

import DomainModel.NCC;
import DomainModel.Sach;
import DomainModel.TheLoai;
import Service.Impl.NCCImpl;
import Service.Impl.SachImpl;
import Service.Impl.TheLoaiImpl;
import Services.NCCService;
import Services.SachService;
import Services.TheLoaiServie;
import ViewModel.SachViewModel;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.layout.AC;

/**
 *
 * @author ADMIN
 */
public class ViewSanPham extends javax.swing.JPanel implements  Runnable,ThreadFactory{

    private DefaultComboBoxModel cbModel ;
    private DefaultComboBoxModel cbModel2;
    private DefaultTableModel tblModel ;
    private List<SachViewModel> listSachView ;
    private List<NCC> listNCC ;
    private List<TheLoai> listtheloai ;
    private TheLoaiServie svTheLoai ;
    private SachService svSach ;
    private NCCService svNCC;
    private WebcamPanel panel = null;
    private static Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    public ViewSanPham() {
        initComponents();
        cbModel =(DefaultComboBoxModel) cbo_loaiSach.getModel();
        tblModel = (DefaultTableModel) tbl_sanPham.getModel();
        cbModel2 = (DefaultComboBoxModel) cboNhaCungCaP.getModel();
        svTheLoai = new TheLoaiImpl();
        svSach = new SachImpl();
        svNCC = new NCCImpl();
        listtheloai = svTheLoai.getlistTheLoai();
        listSachView = svSach.getAll();
        listNCC = svNCC.getAll();
        statusform();
        fillcomboxTheLoai();
        fillComBoxCNN();
        showData(listSachView);
        txtID.disable();
        initwebcam();

    }
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t ;
        
    }
    public void run(){
    do {        
        try {
             Thread.sleep(100);
        } catch (Exception e) {
        }
        Result result =null;
        BufferedImage image = null;
        if (webcam.isOpen()) {
            if ((image = webcam.getImage() )== null) {
                continue;
            }
        }
        if (image == null) {
            continue;
        }
        LuminanceSource soure = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(soure));
        try {
             result = new MultiFormatReader().decode(bitmap);
        } catch (Exception e) {
        }
        if (result != null) {
            txtMaSach.setText(result.getText());
            
        }
       
        
        
       
    } while (true);
}
     public static void closeCam(){
        if (webcam == null) {
            return;
        }
        webcam.close();
    }
  private void initwebcam(){
    Dimension size = WebcamResolution.QQVGA.getSize();
    webcam = Webcam.getWebcams().get(0);
    webcam.setViewSize(size);
    panel = new WebcamPanel(webcam);
    panel.setPreferredSize(size);
    panel.setFPSDisplayed(true);
  
   jPanel6.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 150));
  
    executor.execute( this);
    
}  

    public void statusform() {
        txt_tenLoaiSach.setVisible(false);
        rdo_DangKinhDoanh.setSelected(true);
        btn_them.setVisible(false);
    }

    public void initTable() {
        String[] cols = new String[]{"ID Sach", "Ten Sach", "Loai Sach", "Trang thai"};
        tblModel.setColumnIdentifiers(cols);
        tbl_sanPham.setModel(tblModel);
        listSachView = svSach.getAll();
        showData(listSachView);
    }

    public void showData(List<SachViewModel> listSachView) {
        tblModel.setRowCount(0);
        for (SachViewModel s : listSachView) {
            Object[] row = new Object[]{s.getId(),s.getMaSach(), s.getTenSach(), s.getLoaiSach(),s.getNXB(), s.isTrangThai() == true ? "Đang kinh doanh" : "Ngừng kinh doanh"};
            tblModel.addRow(row);
        }
    }

    public void showtheloai() {
        TheLoai tl = (TheLoai) cbo_loaiSach.getSelectedItem();
        if (tl == null) {
            return;
        } else {
            txt_tenLoaiSach.setText(tl.getTenTheLoai());
        }
    }
    public void fillComBoxCNN(){
        DefaultComboBoxModel comode2 = (DefaultComboBoxModel) cboNhaCungCaP.getModel();
        cboNhaCungCaP.removeAllItems();
        listNCC = svNCC.getAll();
        for (NCC ncc : listNCC) {
            comode2.addElement(ncc);
        }
    }
    public void fillcomboxTheLoai() {
        DefaultComboBoxModel comode = (DefaultComboBoxModel) cbo_loaiSach.getModel();
        cbo_loaiSach.removeAllItems();
        listtheloai = svTheLoai.getlistTheLoai();
        for (TheLoai tl : listtheloai) {
            comode.addElement(tl);
        }

    }
    public void fillData(int index){
        SachViewModel s = svSach.getAll().get(index);
        
    }
    public TheLoai guidata() {
        TheLoai tl = new TheLoai();
        tl.setTenTheLoai(txt_tenLoaiSach.getText());
        
        return tl;
    }
    public Sach guiDataSach(){
        return new Sach(txtMaSach.getText(), txtTenSach1.getText(), listtheloai.get(cbo_loaiSach.getSelectedIndex()).getIdTheLoai(), listNCC.get(cboNhaCungCaP.getSelectedIndex()).getIdNCC(), rdo_DangKinhDoanh.isSelected());
//        return new Sach(txtTenSach1.getText(), listtheloai.get(cbo_loaiSach.getSelectedIndex()).getIdTheLoai(), rdo_DangKinhDoanh.isSelected());
    }
    public void TimTheoTen() {
        String temp = txtTimKiem.getText();
        List<SachViewModel> listSearch = new ArrayList<>();
        listSearch = svSach.searchTen(temp);
        tblModel = (DefaultTableModel) tbl_sanPham.getModel();
        tblModel.setRowCount(0);
        if (listSearch.isEmpty()) {
            lblTb2.setText("Không tìm thay san pham : " + temp);
          return;
        }
        for (SachViewModel p : listSearch) {
            tblModel.addRow(new Object[]{
                p.getId(),
                p.getMaSach(),
                p.getTenSach(),
                p.getLoaiSach(),
                p.getNXB(),
                p.isTrangThai()==true?"Đang Kinh Doanh":"Ngừng Kinh Doanh",
               
            });
        }
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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new View.form.TextField();
        myButton2 = new View.form.MyButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblTb2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_sanPham = new View.form.TableColumn();
        jPanel4 = new javax.swing.JPanel();
        cbo_loaiSach = new View.form.Combobox();
        jLabel3 = new javax.swing.JLabel();
        rdo_DangKinhDoanh = new View.form.RadioButtonCustom();
        rdo_NgungKinhDoanh = new View.form.RadioButtonCustom();
        myButton3 = new View.form.MyButton();
        myButton4 = new View.form.MyButton();
        myButton5 = new View.form.MyButton();
        myButton6 = new View.form.MyButton();
        myButton7 = new View.form.MyButton();
        txt_tenLoaiSach = new View.form.TextField();
        btn_them = new View.form.MyButton();
        txtTenSach1 = new View.form.TextField();
        txtID = new View.form.TextField();
        txtMaSach = new View.form.TextField();
        cboNhaCungCaP = new View.form.Combobox();
        myButton8 = new View.form.MyButton();
        btn_TatQuetMa = new View.form.MyButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Sản Phẩm");

        txtTimKiem.setLabelText("Tìm theo tên hoặc mã");
        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });

        myButton2.setText("Xuất");
        myButton2.setRadius(20);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quét Mã", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 360, 150));

        lblTb2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTb2.setForeground(new java.awt.Color(255, 51, 51));
        lblTb2.setText("jLabel4");

        tbl_sanPham.setBackground(new java.awt.Color(255, 255, 255));
        tbl_sanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Sách", "Mã Sách", "Tên Sách", "Loại Sách", "NXB", "Trạng Thái"
            }
        ));
        tbl_sanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_sanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_sanPham);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi Tiết Sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        cbo_loaiSach.setLabeText("Loại Sách");
        cbo_loaiSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbo_loaiSachMouseClicked(evt);
            }
        });

        jLabel3.setText("Trạng Thái");

        buttonGroup1.add(rdo_DangKinhDoanh);
        rdo_DangKinhDoanh.setText("Đang Kinh Doanh ");
        rdo_DangKinhDoanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_DangKinhDoanhActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdo_NgungKinhDoanh);
        rdo_NgungKinhDoanh.setText("Ngừng Kinh Doanh");

        myButton3.setText("Tạo Mới");
        myButton3.setRadius(20);
        myButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton3ActionPerformed(evt);
            }
        });

        myButton4.setText("Thêm");
        myButton4.setRadius(20);
        myButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton4ActionPerformed(evt);
            }
        });

        myButton5.setText("Cập Nhập");
        myButton5.setRadius(20);
        myButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton5ActionPerformed(evt);
            }
        });

        myButton6.setText("Xóa");
        myButton6.setRadius(20);
        myButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton6ActionPerformed(evt);
            }
        });

        myButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon/icons8_Plus_32.png"))); // NOI18N
        myButton7.setBorderColor(new java.awt.Color(255, 255, 255));
        myButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton7ActionPerformed(evt);
            }
        });

        txt_tenLoaiSach.setLabelText("Tên Loại Sách");
        txt_tenLoaiSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tenLoaiSachActionPerformed(evt);
            }
        });

        btn_them.setText("Thêm");
        btn_them.setRadius(20);
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        txtTenSach1.setLabelText("Tên Sách");
        txtTenSach1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSach1ActionPerformed(evt);
            }
        });

        txtID.setLabelText("ID");

        txtMaSach.setLabelText("Mã Sách");

        cboNhaCungCaP.setLabeText("Nhà cung cấp");

        myButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon/icons8_barcode_reader_32.png"))); // NOI18N
        myButton8.setBorderColor(new java.awt.Color(0, 0, 0));
        myButton8.setRadius(20);
        myButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton8ActionPerformed(evt);
            }
        });

        btn_TatQuetMa.setText("Tắt Quét Mã");
        btn_TatQuetMa.setRadius(20);
        btn_TatQuetMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TatQuetMaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbo_loaiSach, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tenLoaiSach, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(myButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(79, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(rdo_DangKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(rdo_NgungKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtTenSach1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                    .addComponent(txtMaSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_TatQuetMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(myButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(myButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(myButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(myButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(myButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cboNhaCungCaP, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(myButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cbo_loaiSach, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_tenLoaiSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboNhaCungCaP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(jLabel3)
                                .addGap(26, 26, 26)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdo_DangKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdo_NgungKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtTenSach1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(myButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(myButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(myButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(myButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btn_TatQuetMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(myButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTb2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 192, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(154, 154, 154))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTb2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableShowProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableShowProductsMouseClicked
        // editProducts();
        // TODO add your handling code here:
    }//GEN-LAST:event_tableShowProductsMouseClicked

    private void myButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton7ActionPerformed
        // TODO add your handling code here:
        if (txt_tenLoaiSach.isVisible()) {
            txt_tenLoaiSach.setVisible(false);
            
            btn_them.setVisible(false);
 
        } else {
            txt_tenLoaiSach.setVisible(true);
  
            btn_them.setVisible(true);
 
            showtheloai();
        }

    }//GEN-LAST:event_myButton7ActionPerformed

    private void txt_tenLoaiSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tenLoaiSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tenLoaiSachActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed

        JOptionPane.showMessageDialog(this,svTheLoai.inerts(guidata()));
        fillcomboxTheLoai();


    }//GEN-LAST:event_btn_themActionPerformed

    private void cbo_loaiSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbo_loaiSachMouseClicked

    }//GEN-LAST:event_cbo_loaiSachMouseClicked

    private void myButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton4ActionPerformed
//        listtheloai = svTheLoai.getlistTheLoai();
//        int indexTl = cbo_loaiSach.getSelectedIndex();
//        TheLoai tl = listtheloai.get(indexTl);
//        String tenSach = txtTenSach.getText();
//        boolean trangThai = rdo_DangKinhDoanh.isSelected();
//        Sach s = new Sach( tenSach, tl.getIdTheLoai(), trangThai);
        JOptionPane.showMessageDialog(this, svSach.inert(guiDataSach()));
        listSachView = svSach.getAll();
        showData(listSachView);

    }//GEN-LAST:event_myButton4ActionPerformed

    private void myButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton3ActionPerformed
        cbo_loaiSach.setSelectedIndex(0);
        txtTenSach1.setText("");
        txtMaSach.setText("");
        rdo_DangKinhDoanh.setSelected(false);
        rdo_NgungKinhDoanh.setSelected(false);
    }//GEN-LAST:event_myButton3ActionPerformed

    private void rdo_DangKinhDoanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_DangKinhDoanhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_DangKinhDoanhActionPerformed

    private void myButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton5ActionPerformed
       String id = txtID.getText() ;
       JOptionPane.showMessageDialog(this,svSach.update(guiDataSach(), id));
       listSachView = svSach.getAll();
       showData(listSachView);
    }//GEN-LAST:event_myButton5ActionPerformed

    private void tbl_sanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_sanPhamMouseClicked
        int row = tbl_sanPham.getSelectedRow();
        SachViewModel s = listSachView.get(row);
        txtID.setText(s.getId()+"");
        txtMaSach.setText(s.getMaSach());
        txtTenSach1.setText(s.getTenSach());
        cboNhaCungCaP.setSelectedItem(s.getNXB());
        cbo_loaiSach.setSelectedItem(s.getLoaiSach());
        boolean trangThai = s.isTrangThai();
        if(trangThai){
            rdo_DangKinhDoanh.setSelected(true);
        }else{
            rdo_NgungKinhDoanh.setSelected(true);
        }
    }//GEN-LAST:event_tbl_sanPhamMouseClicked

    private void txtTenSach1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSach1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSach1ActionPerformed

    private void myButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton6ActionPerformed
         String id = txtID.getText();
         JOptionPane.showMessageDialog(this, svSach.delete(id));
         listSachView = svSach.getAll();
         showData(listSachView);
    }//GEN-LAST:event_myButton6ActionPerformed

    private void myButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton8ActionPerformed
        // TODO add your handling code here:
//        QR qr = new QR();
//        qr.setVisible(true);
//if (webcam == null) {
//            return;
//        }
//        webcam.close();
//        this.dispose();
       //initwebcam();
        
    }//GEN-LAST:event_myButton8ActionPerformed

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        TimTheoTen();
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void btn_TatQuetMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TatQuetMaActionPerformed
        // TODO add your handling code here:
        if (webcam == null) {
            return;
        }
        webcam.close();
        
    }//GEN-LAST:event_btn_TatQuetMaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.form.MyButton btn_TatQuetMa;
    private View.form.MyButton btn_them;
    private javax.swing.ButtonGroup buttonGroup1;
    private View.form.Combobox cboNhaCungCaP;
    private View.form.Combobox cbo_loaiSach;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTb2;
    private View.form.MyButton myButton2;
    private View.form.MyButton myButton3;
    private View.form.MyButton myButton4;
    private View.form.MyButton myButton5;
    private View.form.MyButton myButton6;
    private View.form.MyButton myButton7;
    private View.form.MyButton myButton8;
    private View.form.RadioButtonCustom rdo_DangKinhDoanh;
    private View.form.RadioButtonCustom rdo_NgungKinhDoanh;
    private View.form.TableColumn tbl_sanPham;
    private View.form.TextField txtID;
    private View.form.TextField txtMaSach;
    private View.form.TextField txtTenSach1;
    private View.form.TextField txtTimKiem;
    private View.form.TextField txt_tenLoaiSach;
    // End of variables declaration//GEN-END:variables
}
