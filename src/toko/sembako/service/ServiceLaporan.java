package toko.sembako.service;

import toko.sembako.entity.LaporanStok;
import toko.sembako.entity.LaporanPenjualan;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface untuk mengelola laporan stok dan penjualan.
 */
public interface ServiceLaporan {

    /**
     * Menggenerate laporan stok dari database.
     * @return List<LaporanStok> berisi data stok.
     * @throws SQLException jika terjadi error saat mengakses database.
     */
    List<LaporanStok> generateLaporanStok() throws SQLException;

    /**
     * Menggenerate laporan penjualan dari database.
     * @return List<LaporanPenjualan> berisi data penjualan.
     * @throws SQLException jika terjadi error saat mengakses database.
     */
    List<LaporanPenjualan> generateLaporanPenjualan() throws SQLException;
}