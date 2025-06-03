package toko.sembako.gui;

import toko.sembako.entity.LaporanStok;
import toko.sembako.entity.LaporanPenjualan;
import toko.sembako.service.ServiceLaporan;
import toko.sembako.dao.DaoLaporan;
import toko.sembako.tablemodel.TableModelLaporanStok;
import toko.sembako.tablemodel.TableModelLaporanPenjualan;
import toko.sembako.util.CurrencyFormatter;
import toko.sembako.util.DateUtil;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Panel GUI untuk menampilkan dan mengelola laporan stok dan penjualan.
 * Mendukung ekspor laporan ke format PDF tanpa menggunakan kelas report.
 */
public class FormLaporan extends javax.swing.JPanel {
    private ServiceLaporan serviceLaporan;
    private TableModelLaporanStok tableModelLaporanStok;
    private TableModelLaporanPenjualan tableModelLaporanPenjualan;

    /**
     * Konstruktor untuk inisialisasi FormLaporan.
     * Menginisialisasi koneksi ke layanan laporan dan komponen UI.
     */
    public FormLaporan() {
        serviceLaporan = new DaoLaporan();
        initComponents();
        setupTables();
    }

    private void initComponents() {
        jLabelHeadline = new javax.swing.JLabel("Toko Sembako Zandra - Laporan");
        jTabbedPane = new javax.swing.JTabbedPane();
        jPanelStok = new javax.swing.JPanel();
        jPanelPenjualan = new javax.swing.JPanel();
        jScrollPaneStok = new javax.swing.JScrollPane();
        jTableStok = new javax.swing.JTable();
        jScrollPanePenjualan = new javax.swing.JScrollPane();
        jTablePenjualan = new javax.swing.JTable();
        jButtonGenerateStok = new javax.swing.JButton("Generate Laporan Stok");
        jButtonGeneratePenjualan = new javax.swing.JButton("Generate Laporan Penjualan");
        jButtonExportStok = new javax.swing.JButton("Export to PDF (Stok)");
        jButtonExportPenjualan = new javax.swing.JButton("Export to PDF (Penjualan)");

        jLabelHeadline.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabelHeadline.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButtonGenerateStok.setBackground(new java.awt.Color(0, 153, 0));
        jButtonGenerateStok.setForeground(new java.awt.Color(255, 255, 255));
        jButtonGeneratePenjualan.setBackground(new java.awt.Color(0, 153, 0));
        jButtonGeneratePenjualan.setForeground(new java.awt.Color(255, 255, 255));
        jButtonExportStok.setBackground(new java.awt.Color(0, 102, 204));
        jButtonExportStok.setForeground(new java.awt.Color(255, 255, 255));
        jButtonExportPenjualan.setBackground(new java.awt.Color(0, 102, 204));
        jButtonExportPenjualan.setForeground(new java.awt.Color(255, 255, 255));

        jButtonGenerateStok.addActionListener(e -> generateLaporanStok());
        jButtonGeneratePenjualan.addActionListener(e -> generateLaporanPenjualan());
        jButtonExportStok.addActionListener(e -> exportLaporanStok());
        jButtonExportPenjualan.addActionListener(e -> exportLaporanPenjualan());

        // Layout untuk tab Laporan Stok
        javax.swing.GroupLayout jPanelStokLayout = new javax.swing.GroupLayout(jPanelStok);
        jPanelStok.setLayout(jPanelStokLayout);
        jPanelStokLayout.setHorizontalGroup(
            jPanelStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelStokLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanelStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPaneStok, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                        .addComponent(jButtonGenerateStok, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonExportStok, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        jPanelStokLayout.setVerticalGroup(
            jPanelStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelStokLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButtonGenerateStok)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButtonExportStok)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPaneStok, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addContainerGap())
        );

        jScrollPaneStok.setViewportView(jTableStok);

        // Layout untuk tab Laporan Penjualan
        javax.swing.GroupLayout jPanelPenjualanLayout = new javax.swing.GroupLayout(jPanelPenjualan);
        jPanelPenjualan.setLayout(jPanelPenjualanLayout);
        jPanelPenjualanLayout.setHorizontalGroup(
            jPanelPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelPenjualanLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanelPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPanePenjualan, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                        .addComponent(jButtonGeneratePenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonExportPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        jPanelPenjualanLayout.setVerticalGroup(
            jPanelPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelPenjualanLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButtonGeneratePenjualan)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButtonExportPenjualan)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPanePenjualan, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addContainerGap())
        );

        jScrollPanePenjualan.setViewportView(jTablePenjualan);

        // Tambahkan tab ke JTabbedPane
        jTabbedPane.addTab("Laporan Stok", jPanelStok);
        jTabbedPane.addTab("Laporan Penjualan", jPanelPenjualan);

        // Layout utama FormLaporan
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelHeadline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabelHeadline)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );
    }

    private void setupTables() {
        tableModelLaporanStok = new TableModelLaporanStok();
        jTableStok.setModel(tableModelLaporanStok);
        jTableStok.setAutoCreateRowSorter(true); // Enable sorting

        tableModelLaporanPenjualan = new TableModelLaporanPenjualan();
        jTablePenjualan.setModel(tableModelLaporanPenjualan);
        jTablePenjualan.setAutoCreateRowSorter(true); // Enable sorting
    }

    private void generateLaporanStok() {
        if (serviceLaporan != null) {
            try {
                List<LaporanStok> stokList = serviceLaporan.generateLaporanStok();
                if (stokList != null) {
                    tableModelLaporanStok.setData(new ArrayList<>(stokList));
                    if (stokList.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Tidak ada data stok untuk ditampilkan.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Laporan Stok berhasil digenerate (" + stokList.size() + " entri).", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Data stok tidak tersedia.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error generating laporan stok: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Unexpected error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Service Laporan tidak diinisialisasi.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generateLaporanPenjualan() {
        if (serviceLaporan != null) {
            try {
                List<LaporanPenjualan> penjualanList = serviceLaporan.generateLaporanPenjualan();
                if (penjualanList != null) {
                    tableModelLaporanPenjualan.setData(new ArrayList<>(penjualanList));
                    if (penjualanList.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Tidak ada data penjualan untuk ditampilkan.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Laporan Penjualan berhasil digenerate (" + penjualanList.size() + " entri).", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Data penjualan tidak tersedia.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error generating laporan penjualan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Unexpected error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Service Laporan tidak diinisialisasi.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exportLaporanStok() {
        if (tableModelLaporanStok.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tidak ada data untuk diekspor.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("C:\\Users\\LENOVO\\Documents"));
        fileChooser.setDialogTitle("Simpan Laporan Stok");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));
        fileChooser.setSelectedFile(new java.io.File("LaporanStok_" + System.currentTimeMillis() + ".pdf"));

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            java.io.File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".pdf")) {
                filePath += ".pdf";
            }

            try {
                // Inisialisasi dokumen PDF
                PdfWriter writer = new PdfWriter(filePath);
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf);

                // Tambahkan header
                document.add(new Paragraph("Laporan Stok Toko Sembako"));
                document.add(new Paragraph("Tanggal Cetak: " + DateUtil.formatDateTime(new java.util.Date())));

                // Buat tabel untuk data stok
                float[] columnWidths = {1, 2, 2, 2, 2, 3};
                Table table = new Table(columnWidths);
                table.addHeaderCell("ID");
                table.addHeaderCell("Barang ID");
                table.addHeaderCell("Nama Barang");
                table.addHeaderCell("Jumlah Stok");
                table.addHeaderCell("Stok Minimum");
                table.addHeaderCell("Tanggal Update");

                // Isi tabel dengan data dari tableModelLaporanStok
                List<LaporanStok> stokList = tableModelLaporanStok.getData();
                for (LaporanStok stok : stokList) {
                    table.addCell(String.valueOf(stok.getId()));
                    table.addCell(String.valueOf(stok.getBarangId()));
                    table.addCell(stok.getNamaBarang() != null ? stok.getNamaBarang() : "Tidak Diketahui");
                    table.addCell(String.valueOf(stok.getJumlahStok()));
                    table.addCell(String.valueOf(stok.getStokMinimum()));
                    table.addCell(DateUtil.formatDateTime(stok.getTanggalUpdate()));
                }

                document.add(table);
                document.close();

                JOptionPane.showMessageDialog(this, "Laporan Stok berhasil diekspor ke: " + filePath, "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Error exporting laporan stok: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Unexpected error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void exportLaporanPenjualan() {
        if (tableModelLaporanPenjualan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tidak ada data untuk diekspor.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("C:\\Users\\LENOVO\\Documents"));
        fileChooser.setDialogTitle("Simpan Laporan Penjualan");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));
        fileChooser.setSelectedFile(new java.io.File("LaporanPenjualan_" + System.currentTimeMillis() + ".pdf"));

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            java.io.File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".pdf")) {
                filePath += ".pdf";
            }

            try {
                // Inisialisasi dokumen PDF
                PdfWriter writer = new PdfWriter(filePath);
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf);

                // Tambahkan header
                document.add(new Paragraph("Laporan Penjualan Toko Sembako"));
                document.add(new Paragraph("Tanggal Cetak: " + DateUtil.formatDateTime(new java.util.Date())));

                // Buat tabel untuk data penjualan
                float[] columnWidths = {1, 3, 2, 2, 2, 2};
                Table table = new Table(columnWidths);
                table.addHeaderCell("ID Transaksi");
                table.addHeaderCell("Tanggal Transaksi");
                table.addHeaderCell("Nama Barang");
                table.addHeaderCell("Jumlah");
                table.addHeaderCell("Harga Satuan");
                table.addHeaderCell("Subtotal");

                // Isi tabel dengan data dari tableModelLaporanPenjualan
                List<LaporanPenjualan> penjualanList = tableModelLaporanPenjualan.getData();
                for (LaporanPenjualan penjualan : penjualanList) {
                    table.addCell(String.valueOf(penjualan.getTransaksiId()));
                    table.addCell(DateUtil.formatDateTime(penjualan.getTanggalTransaksi()));
                    table.addCell(penjualan.getNamaBarang() != null ? penjualan.getNamaBarang() : "Tidak Diketahui");
                    table.addCell(String.valueOf(penjualan.getJumlah()));
                    table.addCell(CurrencyFormatter.formatCurrency(penjualan.getHargaSatuan()));
                    table.addCell(CurrencyFormatter.formatCurrency(penjualan.getSubtotal()));
                }

                document.add(table);
                document.close();

                JOptionPane.showMessageDialog(this, "Laporan Penjualan berhasil diekspor ke: " + filePath, "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Error exporting laporan penjualan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Unexpected error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private javax.swing.JLabel jLabelHeadline;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JPanel jPanelStok;
    private javax.swing.JPanel jPanelPenjualan;
    private javax.swing.JScrollPane jScrollPaneStok;
    private javax.swing.JScrollPane jScrollPanePenjualan;
    private javax.swing.JTable jTableStok;
    private javax.swing.JTable jTablePenjualan;
    private javax.swing.JButton jButtonGenerateStok;
    private javax.swing.JButton jButtonGeneratePenjualan;
    private javax.swing.JButton jButtonExportStok;
    private javax.swing.JButton jButtonExportPenjualan;
}