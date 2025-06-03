package toko.sembako.service;

import toko.sembako.entity.Barang;
import java.sql.SQLException;
import java.util.List;

public interface ServiceBarang {
    void insert(Barang barang) throws SQLException;
    void update(Barang barang) throws SQLException;
    void delete(Barang barang) throws SQLException;
    Barang getById(int id) throws SQLException;
    List<Barang> getAll() throws SQLException;
}