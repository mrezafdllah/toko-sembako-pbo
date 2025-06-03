package toko.sembako.dao;

import toko.sembako.entity.Kategori;
import toko.sembako.koneksi.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import toko.sembako.service.ServiceKategori;

public class DaoKategori implements ServiceKategori {
    @Override
    public void insert(Kategori kategori) throws SQLException {
        String sql = "INSERT INTO kategori (nama_kategori, deskripsi) VALUES (?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, kategori.getNamaKategori());
            stmt.setString(2, kategori.getDeskripsi());
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Kategori kategori) throws SQLException {
        String sql = "UPDATE kategori SET nama_kategori=?, deskripsi=? WHERE id=?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, kategori.getNamaKategori());
            stmt.setString(2, kategori.getDeskripsi());
            stmt.setInt(3, kategori.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Kategori kategori) throws SQLException {
        String sql = "DELETE FROM kategori WHERE id=?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, kategori.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public Kategori getById(int id) throws SQLException {
        String sql = "SELECT * FROM kategori WHERE id=?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Kategori(
                        rs.getInt("id"),
                        rs.getString("nama_kategori"),
                        rs.getString("deskripsi")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Kategori> getAll() throws SQLException {
        List<Kategori> list = new ArrayList<>();
        String sql = "SELECT * FROM kategori";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Kategori(
                    rs.getInt("id"),
                    rs.getString("nama_kategori"),
                    rs.getString("deskripsi")
                ));
            }
        }
        return list;
    }
}