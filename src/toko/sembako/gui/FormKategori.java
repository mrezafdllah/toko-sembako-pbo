package toko.sembako.gui;

import toko.sembako.entity.Kategori;
import toko.sembako.service.ServiceKategori;
import toko.sembako.dao.DaoKategori;
import toko.sembako.tablemodel.TableModelKategori;
import toko.sembako.util.ValidationUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FormKategori extends javax.swing.JPanel {
    private final ServiceKategori service;
    private TableModelKategori tableModel;

    public FormKategori() {
        ServiceKategori tempService = null;
        tempService = new DaoKategori();
        this.service = tempService;

        initComponents();
        setupTable();
        loadData();
    }

    private void initComponents() {
        jLabelHeadline = new javax.swing.JLabel("Toko Sembako Zandra");
        jLabel1 = new javax.swing.JLabel("Nama Kategori:");
        jTextFieldNamaKategori = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel("Deskripsi:");
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaDeskripsi = new javax.swing.JTextArea();
        jButtonSave = new javax.swing.JButton("Save");
        jButtonUpdate = new javax.swing.JButton("Update");
        jButtonDelete = new javax.swing.JButton("Delete");
        jButtonRefresh = new javax.swing.JButton("Refresh");
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableKategori = new javax.swing.JTable();

        jLabelHeadline.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabelHeadline.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12));

        jTextAreaDeskripsi.setColumns(20);
        jTextAreaDeskripsi.setRows(5);
        jScrollPane2.setViewportView(jTextAreaDeskripsi);

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

        jTableKategori.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableKategori.getSelectionModel().addListSelectionListener(e -> loadSelectedRow());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelHeadline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldNamaKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jTextFieldNamaKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jScrollPane1.setViewportView(jTableKategori);
    }

    private void setupTable() {
        tableModel = new TableModelKategori();
        jTableKategori.setModel(tableModel);
    }

    private void loadData() {
        if (service != null) {
            try {
                List<Kategori> list = service.getAll();
                tableModel.setData(new ArrayList<>(list));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Service tidak diinisialisasi. Periksa koneksi database.");
        }
    }

    private void loadSelectedRow() {
        int selectedRow = jTableKategori.getSelectedRow();
        if (selectedRow >= 0) {
            jTextFieldNamaKategori.setText(tableModel.getValueAt(selectedRow, 1).toString());
            jTextAreaDeskripsi.setText(tableModel.getValueAt(selectedRow, 2) != null ? tableModel.getValueAt(selectedRow, 2).toString() : "");
        }
    }

    private void saveAction() {
        if (!validateInput()) return;
        try {
            Kategori kategori = new Kategori();
            kategori.setNamaKategori(jTextFieldNamaKategori.getText());
            kategori.setDeskripsi(jTextAreaDeskripsi.getText());
            service.insert(kategori);
            JOptionPane.showMessageDialog(this, "Kategori berhasil ditambahkan");
            clearFields();
            loadData();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void updateAction() {
        int selectedRow = jTableKategori.getSelectedRow();
        if (selectedRow < 0 || !validateInput()) return;
        try {
            Kategori kategori = new Kategori();
            kategori.setId((int) tableModel.getValueAt(selectedRow, 0));
            kategori.setNamaKategori(jTextFieldNamaKategori.getText());
            kategori.setDeskripsi(jTextAreaDeskripsi.getText());
            service.update(kategori);
            JOptionPane.showMessageDialog(this, "Kategori berhasil diperbarui");
            clearFields();
            loadData();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void deleteAction() {
        int selectedRow = jTableKategori.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Pilih baris untuk dihapus");
            return;
        }
        try {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            Kategori kategori = service.getById(id);
            if (kategori != null) {
                service.delete(kategori);
                JOptionPane.showMessageDialog(this, "Kategori berhasil dihapus");
                clearFields();
                loadData();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error deleting: " + ex.getMessage());
        }
    }

    private boolean validateInput() {
        if (!ValidationUtil.isNotEmpty(jTextFieldNamaKategori.getText())) {
            JOptionPane.showMessageDialog(this, "Nama kategori tidak boleh kosong");
            return false;
        }
        return true;
    }

    private void clearFields() {
        jTextFieldNamaKategori.setText("");
        jTextAreaDeskripsi.setText("");
    }

    private javax.swing.JLabel jLabelHeadline;
    private javax.swing.JLabel jLabel1, jLabel2;
    private javax.swing.JTextField jTextFieldNamaKategori;
    private javax.swing.JTextArea jTextAreaDeskripsi;
    private javax.swing.JButton jButtonSave, jButtonUpdate, jButtonDelete, jButtonRefresh;
    private javax.swing.JScrollPane jScrollPane1, jScrollPane2;
    private javax.swing.JTable jTableKategori;
}