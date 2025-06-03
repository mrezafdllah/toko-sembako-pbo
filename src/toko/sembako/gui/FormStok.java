package toko.sembako.gui;

import toko.sembako.entity.Stok;
import toko.sembako.service.ServiceStok;
import toko.sembako.service.ServiceBarang;
import toko.sembako.dao.DaoStok;
import toko.sembako.dao.DaoBarang;
import toko.sembako.tablemodel.TableModelStok;
import toko.sembako.util.ValidationUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import toko.sembako.entity.Barang;

public class FormStok extends javax.swing.JPanel {
    private final ServiceStok service;
    private final ServiceBarang serviceBarang;
    private TableModelStok tableModel;
    private List<Stok> stokList;
    private List<Barang> barangList;

    public FormStok() {
        ServiceStok tempService = null;
        ServiceBarang tempServiceBarang = null;
        tempService = new DaoStok();
        tempServiceBarang = new DaoBarang();
        this.service = tempService;
        this.serviceBarang = tempServiceBarang;

        initComponents();
        setupTable();
        loadBarang();
        loadData();
    }

    private void initComponents() {
        jLabelHeadline = new javax.swing.JLabel("Toko Sembako Zandra");
        jLabel1 = new javax.swing.JLabel("Barang:");
        jComboBoxBarang = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel("Jumlah Stok:");
        jTextFieldJumlahStok = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel("Stok Minimum:");
        jTextFieldStokMinimum = new javax.swing.JTextField();
        jButtonSave = new javax.swing.JButton("Save");
        jButtonUpdate = new javax.swing.JButton("Update");
        jButtonDelete = new javax.swing.JButton("Delete");
        jButtonRefresh = new javax.swing.JButton("Refresh");
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableStok = new javax.swing.JTable();

        jLabelHeadline.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabelHeadline.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12));

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

        jTableStok.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableStok.getSelectionModel().addListSelectionListener(e -> loadSelectedRow());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelHeadline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldJumlahStok, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldStokMinimum, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jComboBoxBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldJumlahStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldStokMinimum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jScrollPane1.setViewportView(jTableStok);
    }

    private void setupTable() {
        tableModel = new TableModelStok();
        jTableStok.setModel(tableModel);
    }

    private void loadBarang() {
        jComboBoxBarang.removeAllItems();
        jComboBoxBarang.addItem("Pilih Barang");
        if (serviceBarang != null) {
            try {
                barangList = serviceBarang.getAll();
                for (Barang barang : barangList) {
                    jComboBoxBarang.addItem(barang.getNama()); // Tampilkan nama barang
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error loading barang: " + ex.getMessage());
            }
        }
    }

    private void loadData() {
        if (service != null) {
            try {
                List<Stok> list = service.getAll();
                tableModel.setData(new ArrayList<>(list));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage());
            }
        }
    }

    private void loadSelectedRow() {
        int selectedRow = jTableStok.getSelectedRow();
        if (selectedRow >= 0) {
            Stok stok = tableModel.getStok(selectedRow); // Asumsikan ada metode getStok
            int barangIndex = 0;
            if (barangList != null) {
                for (int i = 0; i < barangList.size(); i++) {
                    if (barangList.get(i).getId() == stok.getBarangId()) {
                        barangIndex = i + 1; // +1 karena indeks 0 adalah "Pilih Barang"
                        break;
                    }
                }
            }
            jComboBoxBarang.setSelectedIndex(barangIndex);
            jTextFieldJumlahStok.setText(String.valueOf(stok.getJumlahStok()));
            jTextFieldStokMinimum.setText(String.valueOf(stok.getStokMinimum()));
        }
    }

    private void saveAction() {
        if (!validateInput()) return;
        try {
            Stok stok = new Stok();
            int barangIndex = jComboBoxBarang.getSelectedIndex();
            if (barangIndex > 0 && barangList != null && barangIndex - 1 < barangList.size()) {
                stok.setBarangId(barangList.get(barangIndex - 1).getId());
            }
            stok.setJumlahStok(Integer.parseInt(jTextFieldJumlahStok.getText()));
            stok.setStokMinimum(Integer.parseInt(jTextFieldStokMinimum.getText()));
            stok.setTanggalUpdate(new java.util.Date());
            service.insert(stok);
            JOptionPane.showMessageDialog(this, "Stok berhasil ditambahkan");
            clearFields();
            loadData();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void updateAction() {
        int selectedRow = jTableStok.getSelectedRow();
        if (selectedRow < 0 || !validateInput()) return;
        try {
            Stok stok = new Stok();
            stok.setId((int) tableModel.getValueAt(selectedRow, 0));
            int barangIndex = jComboBoxBarang.getSelectedIndex();
            if (barangIndex > 0 && barangList != null && barangIndex - 1 < barangList.size()) {
                stok.setBarangId(barangList.get(barangIndex - 1).getId());
            }
            stok.setJumlahStok(Integer.parseInt(jTextFieldJumlahStok.getText()));
            stok.setStokMinimum(Integer.parseInt(jTextFieldStokMinimum.getText()));
            stok.setTanggalUpdate(new java.util.Date());
            service.update(stok);
            JOptionPane.showMessageDialog(this, "Stok berhasil diperbarui");
            clearFields();
            loadData();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void deleteAction() {
        int selectedRow = jTableStok.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Pilih baris untuk dihapus");
            return;
        }
        try {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            Stok stok = service.getById(id);
            if (stok != null) {
                service.delete(stok);
                JOptionPane.showMessageDialog(this, "Stok berhasil dihapus");
                clearFields();
                loadData();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error deleting: " + ex.getMessage());
        }
    }

    private boolean validateInput() {
        if (jComboBoxBarang.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Pilih barang");
            return false;
        }
        if (!ValidationUtil.isNumeric(jTextFieldJumlahStok.getText())) {
            JOptionPane.showMessageDialog(this, "Jumlah stok harus berupa angka");
            return false;
        }
        if (!ValidationUtil.isNumeric(jTextFieldStokMinimum.getText())) {
            JOptionPane.showMessageDialog(this, "Stok minimum harus berupa angka");
            return false;
        }
        return true;
    }

    private void clearFields() {
        jComboBoxBarang.setSelectedIndex(0);
        jTextFieldJumlahStok.setText("");
        jTextFieldStokMinimum.setText("");
    }

    private javax.swing.JLabel jLabelHeadline;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3;
    private javax.swing.JComboBox<String> jComboBoxBarang;
    private javax.swing.JTextField jTextFieldJumlahStok, jTextFieldStokMinimum;
    private javax.swing.JButton jButtonSave, jButtonUpdate, jButtonDelete, jButtonRefresh;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableStok;
}