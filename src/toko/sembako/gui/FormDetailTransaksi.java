package toko.sembako.gui;

import toko.sembako.entity.DetailTransaksi;
import toko.sembako.service.ServiceDetailTransaksi;
import toko.sembako.service.ServiceTransaksi;
import toko.sembako.service.ServiceBarang;
import toko.sembako.dao.DaoDetailTransaksi;
import toko.sembako.dao.DaoTransaksi;
import toko.sembako.dao.DaoBarang;
import toko.sembako.tablemodel.TableModelDetailTransaksi;
import toko.sembako.util.ValidationUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import toko.sembako.entity.Barang;
import toko.sembako.entity.Transaksi;

public class FormDetailTransaksi extends javax.swing.JPanel {
    private final ServiceDetailTransaksi service;
    private final ServiceTransaksi serviceTransaksi;
    private final ServiceBarang serviceBarang;
    private TableModelDetailTransaksi tableModel;
    private List<Transaksi> transaksiList;
    private List<Barang> barangList;

    public FormDetailTransaksi() {
        ServiceDetailTransaksi tempService = null;
        ServiceTransaksi tempServiceTransaksi = null;
        ServiceBarang tempServiceBarang = null;
        tempService = new DaoDetailTransaksi();
        tempServiceTransaksi = new DaoTransaksi();
        tempServiceBarang = new DaoBarang();
        this.service = tempService;
        this.serviceTransaksi = tempServiceTransaksi;
        this.serviceBarang = tempServiceBarang;

        initComponents();
        setupTable();
        loadTransaksi();
        loadBarang();
        loadData();
        setupSubtotalCalculation();
    }

    private void initComponents() {
        jLabelHeadline = new javax.swing.JLabel("Toko Sembako Zandra");
        jLabel1 = new javax.swing.JLabel("Transaksi ID:");
        jComboBoxTransaksi = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel("Barang ID:");
        jComboBoxBarang = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel("Jumlah:");
        jTextFieldJumlah = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel("Harga Satuan:");
        jTextFieldHargaSatuan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel("Subtotal:");
        jTextFieldSubtotal = new javax.swing.JTextField();
        jButtonSave = new javax.swing.JButton("Save");
        jButtonUpdate = new javax.swing.JButton("Update");
        jButtonDelete = new javax.swing.JButton("Delete");
        jButtonRefresh = new javax.swing.JButton("Refresh");
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDetailTransaksi = new javax.swing.JTable();

        jLabelHeadline.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabelHeadline.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12));

        jTextFieldSubtotal.setEditable(false); // Subtotal dihitung otomatis

        jButtonSave.setBackground(new java.awt.Color(0, 153, 0));
        jButtonSave.setForeground(new java.awt.Color(255, 255, 255));
        jButtonUpdate.setBackground(new java.awt.Color(0, 102, 204));
        jButtonUpdate.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDelete.setBackground(new java.awt.Color(204, 0, 0));
        jButtonDelete.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRefresh.setBackground(new java.awt.Color(153, 153, 153));
        jButtonRefresh.setForeground(new java.awt.Color(255, 255, 255));

        jButtonSave.addActionListener(e -> saveAction());
        jButtonUpdate.addActionListener(e -> updateAction());
        jButtonDelete.addActionListener(e -> deleteAction());
        jButtonRefresh.addActionListener(e -> loadData());

        jTableDetailTransaksi.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableDetailTransaksi.getSelectionModel().addListSelectionListener(e -> loadSelectedRow());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelHeadline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldHargaSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelHeadline)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldHargaSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave)
                    .addComponent(jButtonUpdate)
                    .addComponent(jButtonDelete)
                    .addComponent(jButtonRefresh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jTableDetailTransaksi);
    }

    private void setupTable() {
        tableModel = new TableModelDetailTransaksi();
        jTableDetailTransaksi.setModel(tableModel);
    }

    private void loadTransaksi() {
        jComboBoxTransaksi.removeAllItems();
        jComboBoxTransaksi.addItem("Pilih Transaksi");
        if (serviceTransaksi != null) {
            try {
                transaksiList = serviceTransaksi.getAll();
                for (Transaksi transaksi : transaksiList) {
                    jComboBoxTransaksi.addItem(String.valueOf(transaksi.getId()));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error loading transaksi: " + ex.getMessage());
            }
        }
    }

    private void loadBarang() {
        jComboBoxBarang.removeAllItems();
        jComboBoxBarang.addItem("Pilih Barang");
        if (serviceBarang != null) {
            try {
                barangList = serviceBarang.getAll();
                for (Barang barang : barangList) {
                    jComboBoxBarang.addItem(String.valueOf(barang.getId()));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error loading barang: " + ex.getMessage());
            }
        }
    }

    private void loadData() {
        if (service != null) {
            try {
                List<DetailTransaksi> list = service.getAll();
                tableModel.setData(new ArrayList<>(list));
                loadTransaksi(); // Perbarui daftar transaksi
                loadBarang(); // Perbarui daftar barang
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Service tidak diinisialisasi. Periksa koneksi database.");
        }
    }

    private void loadSelectedRow() {
        int selectedRow = jTableDetailTransaksi.getSelectedRow();
        if (selectedRow >= 0) {
            DetailTransaksi detail = tableModel.getDetailTransaksi(selectedRow);
            int transaksiId = detail.getTransaksiId();
            int transaksiIndex = 0;
            if (transaksiList != null) {
                for (int i = 0; i < transaksiList.size(); i++) {
                    if (transaksiList.get(i).getId() == transaksiId) {
                        transaksiIndex = i + 1; // +1 karena indeks 0 adalah "Pilih Transaksi"
                        break;
                    }
                }
            }
            jComboBoxTransaksi.setSelectedIndex(transaksiIndex);

            int barangId = detail.getBarangId();
            int barangIndex = 0;
            if (barangList != null) {
                for (int i = 0; i < barangList.size(); i++) {
                    if (barangList.get(i).getId() == barangId) {
                        barangIndex = i + 1; // +1 karena indeks 0 adalah "Pilih Barang"
                        break;
                    }
                }
            }
            jComboBoxBarang.setSelectedIndex(barangIndex);

            jTextFieldJumlah.setText(String.valueOf(detail.getJumlah()));
            jTextFieldHargaSatuan.setText(String.valueOf(detail.getHargaSatuan()));
            jTextFieldSubtotal.setText(String.valueOf(detail.getSubtotal()));
        }
    }

    private void setupSubtotalCalculation() {
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculateSubtotal();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculateSubtotal();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculateSubtotal();
            }

            private void calculateSubtotal() {
                try {
                    int jumlah = jTextFieldJumlah.getText().isEmpty() ? 0 : Integer.parseInt(jTextFieldJumlah.getText());
                    double hargaSatuan = jTextFieldHargaSatuan.getText().isEmpty() ? 0 : Double.parseDouble(jTextFieldHargaSatuan.getText());
                    double subtotal = jumlah * hargaSatuan;
                    jTextFieldSubtotal.setText(String.valueOf(subtotal));
                } catch (NumberFormatException ex) {
                    jTextFieldSubtotal.setText("0.0");
                }
            }
        };
        jTextFieldJumlah.getDocument().addDocumentListener(documentListener);
        jTextFieldHargaSatuan.getDocument().addDocumentListener(documentListener);
    }

    private void saveAction() {
        if (!validateInput()) return;
        try {
            DetailTransaksi detail = new DetailTransaksi();
            int transaksiIndex = jComboBoxTransaksi.getSelectedIndex();
            if (transaksiIndex > 0 && transaksiList != null && transaksiIndex - 1 < transaksiList.size()) {
                detail.setTransaksiId(transaksiList.get(transaksiIndex - 1).getId());
            }
            int barangIndex = jComboBoxBarang.getSelectedIndex();
            if (barangIndex > 0 && barangList != null && barangIndex - 1 < barangList.size()) {
                detail.setBarangId(barangList.get(barangIndex - 1).getId());
            }
            detail.setJumlah(Integer.parseInt(jTextFieldJumlah.getText()));
            detail.setHargaSatuan(Double.parseDouble(jTextFieldHargaSatuan.getText()));
            detail.setSubtotal(Double.parseDouble(jTextFieldSubtotal.getText()));
            service.insert(detail);
            JOptionPane.showMessageDialog(this, "Detail transaksi berhasil ditambahkan");
            clearFields();
            loadData();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void updateAction() {
        int selectedRow = jTableDetailTransaksi.getSelectedRow();
        if (selectedRow < 0 || !validateInput()) return;
        try {
            DetailTransaksi detail = new DetailTransaksi();
            detail.setId((int) tableModel.getValueAt(selectedRow, 0));
            int transaksiIndex = jComboBoxTransaksi.getSelectedIndex();
            if (transaksiIndex > 0 && transaksiList != null && transaksiIndex - 1 < transaksiList.size()) {
                detail.setTransaksiId(transaksiList.get(transaksiIndex - 1).getId());
            }
            int barangIndex = jComboBoxBarang.getSelectedIndex();
            if (barangIndex > 0 && barangList != null && barangIndex - 1 < barangList.size()) {
                detail.setBarangId(barangList.get(barangIndex - 1).getId());
            }
            detail.setJumlah(Integer.parseInt(jTextFieldJumlah.getText()));
            detail.setHargaSatuan(Double.parseDouble(jTextFieldHargaSatuan.getText()));
            detail.setSubtotal(Double.parseDouble(jTextFieldSubtotal.getText()));
            service.update(detail);
            JOptionPane.showMessageDialog(this, "Detail transaksi berhasil diperbarui");
            clearFields();
            loadData();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void deleteAction() {
        int selectedRow = jTableDetailTransaksi.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Pilih baris untuk dihapus");
            return;
        }
        try {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            DetailTransaksi detail = service.getById(id);
            if (detail != null) {
                service.delete(detail);
                JOptionPane.showMessageDialog(this, "Detail transaksi berhasil dihapus");
                clearFields();
                loadData();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error deleting: " + ex.getMessage());
        }
    }

    private boolean validateInput() {
        if (jComboBoxTransaksi.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Pilih transaksi");
            return false;
        }
        if (jComboBoxBarang.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Pilih barang");
            return false;
        }
        if (!ValidationUtil.isNumeric(jTextFieldJumlah.getText())) {
            JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka");
            return false;
        }
        if (!ValidationUtil.isNumeric(jTextFieldHargaSatuan.getText())) {
            JOptionPane.showMessageDialog(this, "Harga satuan harus berupa angka");
            return false;
        }
        return true;
    }

    private void clearFields() {
        jComboBoxTransaksi.setSelectedIndex(0);
        jComboBoxBarang.setSelectedIndex(0);
        jTextFieldJumlah.setText("");
        jTextFieldHargaSatuan.setText("");
        jTextFieldSubtotal.setText("");
    }

    private javax.swing.JLabel jLabelHeadline;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5;
    private javax.swing.JComboBox<String> jComboBoxTransaksi, jComboBoxBarang;
    private javax.swing.JTextField jTextFieldJumlah, jTextFieldHargaSatuan, jTextFieldSubtotal;
    private javax.swing.JButton jButtonSave, jButtonUpdate, jButtonDelete, jButtonRefresh;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDetailTransaksi;
}