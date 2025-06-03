package toko.sembako.service;

import toko.sembako.entity.Transaksi;
import java.sql.SQLException;
import java.util.List;

public interface ServiceTransaksi {
    void insert(Transaksi transaksi) throws SQLException;
    void update(Transaksi transaksi) throws SQLException;
    void delete(Transaksi transaksi) throws SQLException;
    Transaksi getById(int id) throws SQLException;
    List<Transaksi> getAll() throws SQLException;
}