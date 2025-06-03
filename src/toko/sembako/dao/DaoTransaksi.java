package toko.sembako.dao;

import toko.sembako.entity.Transaksi;
import toko.sembako.koneksi.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import toko.sembako.service.ServiceTransaksi;

public class DaoTransaksi implements ServiceTransaksi {
    @Override
    public void insert(Transaksi transaksi) throws SQLException {
        String sql = "INSERT INTO transaksi (tanggal, total, kasir) VALUES (?, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, new java.sql.Timestamp(transaksi.getTanggal().getTime()));
            stmt.setDouble(2, transaksi.getTotal());
            stmt.setString(3, transaksi.getKasir());
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Transaksi transaksi) throws SQLException {
        String sql = "UPDATE transaksi SET tanggal=?, total=?, kasir=? WHERE id=?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, new java.sql.Timestamp(transaksi.getTanggal().getTime()));
            stmt.setDouble(2, transaksi.getTotal());
            stmt.setString(3, transaksi.getKasir());
            stmt.setInt(4, transaksi.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Transaksi transaksi) throws SQLException {
        String sql = "DELETE FROM transaksi WHERE id=?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, transaksi.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public Transaksi getById(int id) throws SQLException {
        String sql = "SELECT * FROM transaksi WHERE id=?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Transaksi(
                    rs.getInt("id"),
                    rs.getTimestamp("tanggal"),
                    rs.getDouble("total"),
                    rs.getString("kasir")
                );
            }
            return null;
        }
    }

    @Override
    public List<Transaksi> getAll() throws SQLException {
        List<Transaksi> list = new ArrayList<>();
        String sql = "SELECT * FROM transaksi";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Transaksi(
                    rs.getInt("id"),
                    rs.getTimestamp("tanggal"),
                    rs.getDouble("total"),
                    rs.getString("kasir")
                ));
            }
        }
        return list;
    }
}