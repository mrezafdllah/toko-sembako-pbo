package toko.sembako.gui;

import toko.sembako.entity.Supplier;
import toko.sembako.service.ServiceSupplier;
import toko.sembako.dao.DaoSupplier;
import toko.sembako.tablemodel.TableModelSupplier;
import toko.sembako.util.ValidationUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FormSupplier extends javax.swing.JPanel {
    private final ServiceSupplier service;
    private TableModelSupplier tableModel;

    public FormSupplier() {
        ServiceSupplier tempService = null;
        tempService = new DaoSupplier();
        this.service = tempService;

        initComponents();
        setupTable();
        loadData();
    }

    private void initComponents() {
        jLabelHeadline = new javax.swing.JLabel("Toko Sembako Zandra");
        jLabel1 = new javax.swing.JLabel("Nama:");
        jTextFieldNama = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel("Alamat:");
        jTextFieldAlamat = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel("Telepon:");
        jTextFieldTelepon = new javax.swing.JTextField();
        jButtonSave = new javax.swing.JButton("Save");
        jButtonUpdate = new javax.swing.JButton("Update");
        jButtonDelete = new javax.swing.JButton("Delete");
        jButtonRefresh = new javax.swing.JButton("Refresh");
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSupplier = new javax.swing.JTable();

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

        jTableSupplier.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableSupplier.getSelectionModel().addListSelectionListener(e -> loadSelectedRow());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelHeadline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldNama, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jTextFieldNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jScrollPane1.setViewportView(jTableSupplier);
    }

    private void setupTable() {
        tableModel = new TableModelSupplier();
        jTableSupplier.setModel(tableModel);
    }

    private void loadData() {
        if (service != null) {
            try {
                List<Supplier> list = service.getAll();
                tableModel.setData(new ArrayList<>(list));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage());
            }
        }
    }

    private void loadSelectedRow() {
        int selectedRow = jTableSupplier.getSelectedRow();
        if (selectedRow >= 0) {
            jTextFieldNama.setText(tableModel.getValueAt(selectedRow, 1).toString());
            jTextFieldAlamat.setText(tableModel.getValueAt(selectedRow, 2) != null ? tableModel.getValueAt(selectedRow, 2).toString() : "");
            jTextFieldTelepon.setText(tableModel.getValueAt(selectedRow, 3) != null ? tableModel.getValueAt(selectedRow, 3).toString() : "");
        }
    }

    private void saveAction() {
        if (!validateInput()) return;
        try {
            Supplier supplier = new Supplier();
            supplier.setNama(jTextFieldNama.getText());
            supplier.setAlamat(jTextFieldAlamat.getText());
            supplier.setTelepon(jTextFieldTelepon.getText());
            service.insert(supplier);
            JOptionPane.showMessageDialog(this, "Supplier berhasil ditambahkan");
            clearFields();
            loadData();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void updateAction() {
        int selectedRow = jTableSupplier.getSelectedRow();
        if (selectedRow < 0 || !validateInput()) return;
        try {
            Supplier supplier = new Supplier();
            supplier.setId((int) tableModel.getValueAt(selectedRow, 0));
            supplier.setNama(jTextFieldNama.getText());
            supplier.setAlamat(jTextFieldAlamat.getText());
            supplier.setTelepon(jTextFieldTelepon.getText());
            service.update(supplier);
            JOptionPane.showMessageDialog(this, "Supplier berhasil diperbarui");
            clearFields();
            loadData();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void deleteAction() {
        int selectedRow = jTableSupplier.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Pilih baris untuk dihapus");
            return;
        }
        try {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            Supplier supplier = service.getById(id);
            if (supplier != null) {
                service.delete(supplier);
                JOptionPane.showMessageDialog(this, "Supplier berhasil dihapus");
                clearFields();
                loadData();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error deleting: " + ex.getMessage());
        }
    }

    private boolean validateInput() {
        if (!ValidationUtil.isNotEmpty(jTextFieldNama.getText())) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong");
            return false;
        }
        return true;
    }

    private void clearFields() {
        jTextFieldNama.setText("");
        jTextFieldAlamat.setText("");
        jTextFieldTelepon.setText("");
    }

    private javax.swing.JLabel jLabelHeadline;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3;
    private javax.swing.JTextField jTextFieldNama, jTextFieldAlamat, jTextFieldTelepon;
    private javax.swing.JButton jButtonSave, jButtonUpdate, jButtonDelete, jButtonRefresh;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableSupplier;
}