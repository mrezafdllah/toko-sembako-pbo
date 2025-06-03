package toko.sembako.service;

import toko.sembako.entity.Kategori;
import java.sql.SQLException;
import java.util.List;

public interface ServiceKategori {
    void insert(Kategori kategori) throws SQLException;
    void update(Kategori kategori) throws SQLException;
    void delete(Kategori kategori) throws SQLException;
    Kategori getById(int id) throws SQLException;
    List<Kategori> getAll() throws SQLException;
}