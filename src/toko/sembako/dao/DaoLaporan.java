package toko.sembako.dao;

import toko.sembako.entity.LaporanStok;
import toko.sembako.entity.LaporanPenjualan;
import toko.sembako.koneksi.Koneksi;
import toko.sembako.service.ServiceLaporan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementasi dari ServiceLaporan untuk mengelola data laporan stok dan penjualan.
 */
public class DaoLaporan implements ServiceLaporan {

    /**
     * Menggenerate laporan stok dari database.
     * @return List<LaporanStok> berisi data stok dari tabel stok dan barang.
     * @throws SQLException jika terjadi error saat mengakses database.
     */
    @Override
    public List<LaporanStok> generateLaporanStok() throws SQLException {
        List<LaporanStok> list = new ArrayList<>();
        String sql = "SELECT s.id, s.barang_id, b.nama AS nama_barang, s.jumlah_stok, s.stok_minimum, s.tanggal_update " +
                     "FROM stok s JOIN barang b ON s.barang_id = b.id";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs != null) {
                while (rs.next()) {
                    list.add(new LaporanStok(
                        rs.getInt("id"),
                        rs.getInt("barang_id"),
                        rs.getString("nama_barang") != null ? rs.getString("nama_barang") : "Tidak Diketahui",
                        rs.getInt("jumlah_stok"),
                        rs.getInt("stok_minimum"),
                        rs.getTimestamp("tanggal_update")
                    ));
                }
            }
        }
        return list;
    }

    /**
     * Menggenerate laporan penjualan dari database.
     * @return List<LaporanPenjualan> berisi data penjualan dari tabel transaksi, detail_transaksi, dan barang.
     * @throws SQLException jika terjadi error saat mengakses database.
     */
    @Override
    public List<LaporanPenjualan> generateLaporanPenjualan() throws SQLException {
        List<LaporanPenjualan> list = new ArrayList<>();
        String sql = "SELECT t.id AS transaksi_id, t.tanggal AS tanggal_transaksi, b.nama AS nama_barang, dt.jumlah, dt.harga_satuan, dt.subtotal " +
                     "FROM transaksi t " +
                     "JOIN detail_transaksi dt ON t.id = dt.transaksi_id " +
                     "JOIN barang b ON dt.barang_id = b.id";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs != null) {
                while (rs.next()) {
                    list.add(new LaporanPenjualan(
                        rs.getInt("transaksi_id"),
                        rs.getTimestamp("tanggal_transaksi"),
                        rs.getString("nama_barang") != null ? rs.getString("nama_barang") : "Tidak Diketahui",
                        rs.getInt("jumlah"),
                        rs.getDouble("harga_satuan"),
                        rs.getDouble("subtotal")
                    ));
                }
            }
        }
        return list;
    }
}