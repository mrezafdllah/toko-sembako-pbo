package toko.sembako.dao;

import toko.sembako.entity.Barang;
import toko.sembako.koneksi.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import toko.sembako.service.ServiceBarang;

public class DaoBarang implements ServiceBarang {
    @Override
    public void insert(Barang barang) throws SQLException {
        String sql = "INSERT INTO barang (nama, harga, stok, kategori_id, supplier_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, barang.getNama());
            stmt.setDouble(2, barang.getHarga());
            stmt.setInt(3, barang.getStok());
            stmt.setInt(4, barang.getKategoriId());
            stmt.setInt(5, barang.getSupplierId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Barang barang) throws SQLException {
        String sql = "UPDATE barang SET nama=?, harga=?, stok=?, kategori_id=?, supplier_id=? WHERE id=?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, barang.getNama());
            stmt.setDouble(2, barang.getHarga());
            stmt.setInt(3, barang.getStok());
            stmt.setInt(4, barang.getKategoriId());
            stmt.setInt(5, barang.getSupplierId());
            stmt.setInt(6, barang.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Barang barang) throws SQLException {
        String sql = "DELETE FROM barang WHERE id=?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, barang.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public Barang getById(int id) throws SQLException {
        String sql = "SELECT b.*, k.nama_kategori, s.nama AS nama_supplier " +
                    "FROM barang b " +
                    "LEFT JOIN kategori k ON b.kategori_id = k.id " +
                    "LEFT JOIN supplier s ON b.supplier_id = s.id " +
                    "WHERE b.id=?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Barang(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getDouble("harga"),
                    rs.getInt("stok"),
                    rs.getInt("kategori_id"),
                    rs.getInt("supplier_id"),
                    rs.getString("nama_kategori"),
                    rs.getString("nama_supplier")
                );
            }
            return null;
        }
    }

    @Override
    public List<Barang> getAll() throws SQLException {
        List<Barang> list = new ArrayList<>();
        String sql = "SELECT b.*, k.nama_kategori, s.nama AS nama_supplier " +
                    "FROM barang b " +
                    "LEFT JOIN kategori k ON b.kategori_id = k.id " +
                    "LEFT JOIN supplier s ON b.supplier_id = s.id";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Barang(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getDouble("harga"),
                    rs.getInt("stok"),
                    rs.getInt("kategori_id"),
                    rs.getInt("supplier_id"),
                    rs.getString("nama_kategori"),
                    rs.getString("nama_supplier")
                ));
            }
        }
        return list;
    }
}