package toko.sembako.dao;

import toko.sembako.entity.Stok;
import toko.sembako.koneksi.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import toko.sembako.service.ServiceStok;

public class DaoStok implements ServiceStok {
    @Override
    public void insert(Stok stok) throws SQLException {
        String sql = "INSERT INTO stok (barang_id, jumlah_stok, stok_minimum, tanggal_update) VALUES (?, ?, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, stok.getBarangId());
            stmt.setInt(2, stok.getJumlahStok());
            stmt.setInt(3, stok.getStokMinimum());
            stmt.setTimestamp(4, new java.sql.Timestamp(stok.getTanggalUpdate().getTime()));
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Stok stok) throws SQLException {
        String sql = "UPDATE stok SET barang_id=?, jumlah_stok=?, stok_minimum=?, tanggal_update=? WHERE id=?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, stok.getBarangId());
            stmt.setInt(2, stok.getJumlahStok());
            stmt.setInt(3, stok.getStokMinimum());
            stmt.setTimestamp(4, new java.sql.Timestamp(stok.getTanggalUpdate().getTime()));
            stmt.setInt(5, stok.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Stok stok) throws SQLException {
        String sql = "DELETE FROM stok WHERE id=?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, stok.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public Stok getById(int id) throws SQLException {
        String sql = "SELECT s.id, s.barang_id, s.jumlah_stok, s.stok_minimum, s.tanggal_update, b.nama AS nama_barang " +
                     "FROM stok s JOIN barang b ON s.barang_id = b.id WHERE s.id = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Stok(
                        rs.getInt("id"),
                        rs.getInt("barang_id"),
                        rs.getInt("jumlah_stok"),
                        rs.getInt("stok_minimum"),
                        rs.getTimestamp("tanggal_update"),
                        rs.getString("nama_barang")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Stok> getAll() throws SQLException {
        List<Stok> list = new ArrayList<>();
        String sql = "SELECT s.id, s.barang_id, s.jumlah_stok, s.stok_minimum, s.tanggal_update, b.nama AS nama_barang " +
                     "FROM stok s JOIN barang b ON s.barang_id = b.id";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Stok(
                    rs.getInt("id"),
                    rs.getInt("barang_id"),
                    rs.getInt("jumlah_stok"),
                    rs.getInt("stok_minimum"),
                    rs.getTimestamp("tanggal_update"),
                    rs.getString("nama_barang")
                ));
            }
        }
        return list;
    }
}