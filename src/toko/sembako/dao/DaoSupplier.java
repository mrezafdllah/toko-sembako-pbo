package toko.sembako.dao;

import toko.sembako.entity.Supplier;
import toko.sembako.koneksi.Koneksi;
import toko.sembako.service.ServiceSupplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoSupplier implements ServiceSupplier {
    @Override
    public void insert(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO supplier (nama, alamat, telepon) VALUES (?, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, supplier.getNama());
            stmt.setString(2, supplier.getAlamat());
            stmt.setString(3, supplier.getTelepon());
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Supplier supplier) throws SQLException {
        String sql = "UPDATE supplier SET nama = ?, alamat = ?, telepon = ? WHERE id = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, supplier.getNama());
            stmt.setString(2, supplier.getAlamat());
            stmt.setString(3, supplier.getTelepon());
            stmt.setInt(4, supplier.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Supplier supplier) throws SQLException {
        String sql = "DELETE FROM supplier WHERE id = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, supplier.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public Supplier getById(int id) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE id = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Supplier(
                        rs.getInt("id"),
                        rs.getString("nama"),
                        rs.getString("alamat"),
                        rs.getString("telepon")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Supplier> getAll() throws SQLException {
        List<Supplier> list = new ArrayList<>();
        String sql = "SELECT * FROM supplier";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Supplier(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("alamat"),
                    rs.getString("telepon")
                ));
            }
        }
        return list;
    }
}