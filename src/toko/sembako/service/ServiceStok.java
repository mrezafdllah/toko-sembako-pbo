package toko.sembako.service;

import toko.sembako.entity.Stok;
import java.sql.SQLException;
import java.util.List;

public interface ServiceStok {
    void insert(Stok stok) throws SQLException;
    void update(Stok stok) throws SQLException;
    void delete(Stok stok) throws SQLException;
    Stok getById(int id) throws SQLException;
    List<Stok> getAll() throws SQLException;
}