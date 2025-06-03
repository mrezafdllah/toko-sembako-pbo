package toko.sembako.dao;

import toko.sembako.entity.DetailTransaksi;
import toko.sembako.koneksi.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import toko.sembako.service.ServiceDetailTransaksi;

public class DaoDetailTransaksi implements ServiceDetailTransaksi {
    @Override
    public void insert(DetailTransaksi detail) throws SQLException {
        String sql = "INSERT INTO detail_transaksi (transaksi_id, barang_id, jumlah, harga_satuan, subtotal) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, detail.getTransaksiId());
            stmt.setInt(2, detail.getBarangId());
            stmt.setInt(3, detail.getJumlah());
            stmt.setDouble(4, detail.getHargaSatuan());
            stmt.setDouble(5, detail.getSubtotal());
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(DetailTransaksi detail) throws SQLException {
        String sql = "UPDATE detail_transaksi SET transaksi_id=?, barang_id=?, jumlah=?, harga_satuan=?, subtotal=? WHERE id=?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, detail.getTransaksiId());
            stmt.setInt(2, detail.getBarangId());
            stmt.setInt(3, detail.getJumlah());
            stmt.setDouble(4, detail.getHargaSatuan());
            stmt.setDouble(5, detail.getSubtotal());
            stmt.setInt(6, detail.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(DetailTransaksi detail) throws SQLException {
        String sql = "DELETE FROM detail_transaksi WHERE id=?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, detail.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public DetailTransaksi getById(int id) throws SQLException {
        String sql = "SELECT * FROM detail_transaksi WHERE id=?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new DetailTransaksi(
                    rs.getInt("id"),
                    rs.getInt("transaksi_id"),
                    rs.getInt("barang_id"),
                    rs.getInt("jumlah"),
                    rs.getDouble("harga_satuan"),
                    rs.getDouble("subtotal")
                );
            }
            return null;
        }
    }

    @Override
    public List<DetailTransaksi> getAll() throws SQLException {
        List<DetailTransaksi> list = new ArrayList<>();
        String sql = "SELECT * FROM detail_transaksi";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new DetailTransaksi(
                    rs.getInt("id"),
                    rs.getInt("transaksi_id"),
                    rs.getInt("barang_id"),
                    rs.getInt("jumlah"),
                    rs.getDouble("harga_satuan"),
                    rs.getDouble("subtotal")
                ));
            }
        }
        return list;
    }
}