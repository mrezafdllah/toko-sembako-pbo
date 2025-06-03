package toko.sembako.gui;

import toko.sembako.entity.Barang;
import toko.sembako.entity.Kategori;
import toko.sembako.entity.Supplier;
import toko.sembako.service.ServiceBarang;
import toko.sembako.service.ServiceKategori;
import toko.sembako.service.ServiceSupplier;
import toko.sembako.dao.DaoBarang;
import toko.sembako.dao.DaoKategori;
import toko.sembako.dao.DaoSupplier;
import toko.sembako.tablemodel.TableModelBarang;
import toko.sembako.util.CurrencyFormatter;
import toko.sembako.util.ValidationUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FormBarang extends javax.swing.JPanel {
    private final ServiceBarang service;
    private final ServiceKategori serviceKategori;
    private final ServiceSupplier serviceSupplier;
    private TableModelBarang tableModel;
    private List<Kategori> kategoriList;
    private List<Supplier> supplierList;

    public FormBarang() {
        ServiceBarang tempService = null;
        ServiceKategori tempServiceKategori = null;
        ServiceSupplier tempServiceSupplier = null;
        tempService = new DaoBarang();
        tempServiceKategori = new DaoKategori();
        tempServiceSupplier = new DaoSupplier();
        this.service = tempService;
        this.serviceKategori = tempServiceKategori;
        this.serviceSupplier = tempServiceSupplier;

        initComponents();
        setupTable();
        loadKategori();
        loadSupplier();
        loadData();
    }

    private void initComponents() {
        jLabelHeadline = new javax.swing.JLabel("Toko Sembako Zandra");
        jLabel1 = new javax.swing.JLabel("Nama:");
        jTextFieldNama = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel("Harga:");
        jTextFieldHarga = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel("Stok:");
        jTextFieldStok = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel("Kategori:");
        jComboBoxKategori = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel("Supplier:");
        jComboBoxSupplier = new javax.swing.JComboBox<>();
        jButtonSave = new javax.swing.JButton("Save");
        jButtonUpdate = new javax.swing.JButton("Update");
        jButtonDelete = new javax.swing.JButton("Delete");
        jButtonRefresh = new javax.swing.JButton("Refresh");
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableBarang = new javax.swing.JTable();

        jLabelHeadline.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabelHeadline.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12));

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

        jTableBarang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableBarang.getSelectionModel().addListSelectionListener(e -> loadSelectedRow());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelHeadline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1).addComponent(jTextFieldNama, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2).addComponent(jTextFieldHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3).addComponent(jTextFieldStok, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4).addComponent(jComboBoxKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5).addComponent(jComboBoxSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jLabel1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jScrollPane1.setViewportView(jTableBarang);
    }

    private void setupTable() {
        tableModel = new TableModelBarang();
        jTableBarang.setModel(tableModel);
    }

    private void loadKategori() {
        jComboBoxKategori.removeAllItems();
        jComboBoxKategori.addItem("Pilih Kategori");
        if (serviceKategori != null) {
            try {
                kategoriList = serviceKategori.getAll(); // Perbarui daftar kategori
                for (Kategori kategori : kategoriList) {
                    jComboBoxKategori.addItem(kategori.getNama());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error loading kategori: " + ex.getMessage());
            }
        }
    }

    private void loadSupplier() {
        jComboBoxSupplier.removeAllItems();
        jComboBoxSupplier.addItem("Pilih Supplier");
        if (serviceSupplier != null) {
            try {
                supplierList = serviceSupplier.getAll(); // Perbarui daftar supplier
                for (Supplier supplier : supplierList) {
                    jComboBoxSupplier.addItem(supplier.getNama());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error loading supplier: " + ex.getMessage());
            }
        }
    }

    private void loadData() {
        if (service != null) {
            try {
                List<Barang> list = service.getAll();
                tableModel.setData(new ArrayList<>(list));
                loadKategori(); // Perbarui kategori saat load data
                loadSupplier(); // Perbarui supplier saat load data
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage());
            }
        }
    }

    private void loadSelectedRow() {
        int selectedRow = jTableBarang.getSelectedRow();
        if (selectedRow >= 0) {
            jTextFieldNama.setText(tableModel.getValueAt(selectedRow, 1).toString());
            jTextFieldHarga.setText(String.valueOf(tableModel.getValueAt(selectedRow, 2)));
            jTextFieldStok.setText(String.valueOf(tableModel.getValueAt(selectedRow, 3)));
            
            // Ambil Barang dari tableModel untuk mendapatkan kategori_id dan supplier_id
            Barang barang = tableModel.getBarang(selectedRow);
            int kategoriId = barang.getKategoriId();
            int supplierId = barang.getSupplierId();

            // Cocokkan kategori_id dengan opsi di JComboBox
            int kategoriIndex = 0;
            if (kategoriList != null) {
                for (int i = 0; i < kategoriList.size(); i++) {
                    if (kategoriList.get(i).getId() == kategoriId) {
                        kategoriIndex = i + 1; // +1 karena indeks 0 adalah "Pilih Kategori"
                        break;
                    }
                }
            }
            jComboBoxKategori.setSelectedIndex(kategoriIndex);

            // Cocokkan supplier_id dengan opsi di JComboBox
            int supplierIndex = 0;
            if (supplierList != null) {
                for (int i = 0; i < supplierList.size(); i++) {
                    if (supplierList.get(i).getId() == supplierId) {
                        supplierIndex = i + 1; // +1 karena indeks 0 adalah "Pilih Supplier"
                        break;
                    }
                }
            }
            jComboBoxSupplier.setSelectedIndex(supplierIndex);
        }
    }

    private void saveAction() {
        if (!validateInput()) return;
        try {
            Barang barang = new Barang();
            barang.setNama(jTextFieldNama.getText());
            barang.setHarga(CurrencyFormatter.parseCurrency(jTextFieldHarga.getText()));
            barang.setStok(Integer.parseInt(jTextFieldStok.getText()));
            int kategoriIndex = jComboBoxKategori.getSelectedIndex();
            int supplierIndex = jComboBoxSupplier.getSelectedIndex();
            if (kategoriIndex > 0 && kategoriList != null && kategoriIndex - 1 < kategoriList.size()) {
                barang.setKategoriId(kategoriList.get(kategoriIndex - 1).getId());
            }
            if (supplierIndex > 0 && supplierList != null && supplierIndex - 1 < supplierList.size()) {
                barang.setSupplierId(supplierList.get(supplierIndex - 1).getId());
            }
            service.insert(barang);
            JOptionPane.showMessageDialog(this, "Barang berhasil ditambahkan");
            clearFields();
            loadData(); // Perbarui semua data, termasuk JComboBox
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void updateAction() {
        int selectedRow = jTableBarang.getSelectedRow();
        if (selectedRow < 0 || !validateInput()) return;
        try {
            Barang barang = new Barang();
            barang.setId((int) tableModel.getValueAt(selectedRow, 0));
            barang.setNama(jTextFieldNama.getText());
            barang.setHarga(CurrencyFormatter.parseCurrency(jTextFieldHarga.getText()));
            barang.setStok(Integer.parseInt(jTextFieldStok.getText()));
            int kategoriIndex = jComboBoxKategori.getSelectedIndex();
            int supplierIndex = jComboBoxSupplier.getSelectedIndex();
            if (kategoriIndex > 0 && kategoriList != null && kategoriIndex - 1 < kategoriList.size()) {
                barang.setKategoriId(kategoriList.get(kategoriIndex - 1).getId());
            }
            if (supplierIndex > 0 && supplierList != null && supplierIndex - 1 < supplierList.size()) {
                barang.setSupplierId(supplierList.get(supplierIndex - 1).getId());
            }
            service.update(barang);
            JOptionPane.showMessageDialog(this, "Barang berhasil diperbarui");
            clearFields();
            loadData(); // Perbarui semua data, termasuk JComboBox
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void deleteAction() {
        int selectedRow = jTableBarang.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Pilih baris untuk dihapus");
            return;
        }
        try {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            Barang barang = service.getById(id);
            if (barang != null) {
                service.delete(barang);
                JOptionPane.showMessageDialog(this, "Barang berhasil dihapus");
                clearFields();
                loadData(); // Perbarui semua data, termasuk JComboBox
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
        if (!ValidationUtil.isNumeric(jTextFieldHarga.getText())) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka");
            return false;
        }
        if (!ValidationUtil.isNumeric(jTextFieldStok.getText())) {
            JOptionPane.showMessageDialog(this, "Stok harus berupa angka");
            return false;
        }
        if (jComboBoxKategori.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Pilih kategori");
            return false;
        }
        if (jComboBoxSupplier.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Pilih supplier");
            return false;
        }
        return true;
    }

    private void clearFields() {
        jTextFieldNama.setText("");
        jTextFieldHarga.setText("");
        jTextFieldStok.setText("");
        jComboBoxKategori.setSelectedIndex(0);
        jComboBoxSupplier.setSelectedIndex(0);
    }

    private javax.swing.JLabel jLabelHeadline;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5;
    private javax.swing.JTextField jTextFieldNama, jTextFieldHarga, jTextFieldStok;
    private javax.swing.JComboBox<String> jComboBoxKategori, jComboBoxSupplier;
    private javax.swing.JButton jButtonSave, jButtonUpdate, jButtonDelete, jButtonRefresh;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableBarang;
}