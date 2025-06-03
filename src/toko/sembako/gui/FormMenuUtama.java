package toko.sembako.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormMenuUtama extends javax.swing.JFrame {
    private JLabel jLabelHeader;
    private JLabel jLabelDateTime;
    private JButton jButtonExit;
    private JTabbedPane jTabbedPane1;
    private FormBarang formBarang;
    private FormKategori formKategori;
    private FormSupplier formSupplier;
    private FormTransaksi formTransaksi;
    private FormStok formStok;
    private FormLaporan formLaporan;
    private FormDetailTransaksi formDetailTransaksi;

    public FormMenuUtama() {
        setTitle("Manajemen Stok Barang Toko Sembako");
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 500)); // Atur ukuran minimum
        setLocationRelativeTo(null);

        initComponents();
        updateDateTime(); // Tampilkan tanggal dan waktu saat ini
    }

    private void initComponents() {
        jLabelHeader = new JLabel("Manajemen Stok Barang - Toko Sembako Zandra");
        jLabelHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabelHeader.setHorizontalAlignment(SwingConstants.CENTER);

        jLabelDateTime = new JLabel();
        jLabelDateTime.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabelDateTime.setHorizontalAlignment(SwingConstants.CENTER);

        jButtonExit = new JButton("Keluar");
        jButtonExit.setBackground(new Color(204, 0, 0));
        jButtonExit.setForeground(Color.WHITE);
        jButtonExit.addActionListener(e -> System.exit(0));

        jTabbedPane1 = new JTabbedPane();

        // Inisialisasi form dengan penanganan eksepsi
        try {
            formBarang = new FormBarang();
            formKategori = new FormKategori();
            formSupplier = new FormSupplier();
            formTransaksi = new FormTransaksi();
            formStok = new FormStok();
            formDetailTransaksi = new FormDetailTransaksi();
            formLaporan = new FormLaporan();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal inisialisasi form: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1); // Keluar jika inisialisasi gagal
        }

        jTabbedPane1.addTab("Barang", formBarang);
        jTabbedPane1.addTab("Kategori", formKategori);
        jTabbedPane1.addTab("Supplier", formSupplier);
        jTabbedPane1.addTab("Transaksi", formTransaksi);
        jTabbedPane1.addTab("Stok", formStok);
        jTabbedPane1.addTab("Detail Transaksi", formDetailTransaksi);
        jTabbedPane1.addTab("Laporan", formLaporan);

        // Gunakan BorderLayout untuk tata letak utama
        setLayout(new BorderLayout(10, 10));

        // Panel header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(jLabelHeader, BorderLayout.NORTH);
        headerPanel.add(jLabelDateTime, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // Tabbed pane
        add(jTabbedPane1, BorderLayout.CENTER);

        // Panel untuk tombol keluar
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.add(jButtonExit);
        add(footerPanel, BorderLayout.SOUTH);

        pack();
    }

    private void updateDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy, HH:mm:ss 'WIB'");
        jLabelDateTime.setText("Tanggal: " + sdf.format(new Date()));
    }

    public static void main(String[] args) {
        // Jalankan aplikasi dengan EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            new FormMenuUtama().setVisible(true);
        });
    }
}