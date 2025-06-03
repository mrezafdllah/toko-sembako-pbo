package toko.sembako.service;

import toko.sembako.entity.DetailTransaksi;
import java.sql.SQLException;
import java.util.List;

public interface ServiceDetailTransaksi {
    void insert(DetailTransaksi detail) throws SQLException;
    void update(DetailTransaksi detail) throws SQLException;
    void delete(DetailTransaksi detail) throws SQLException;
    DetailTransaksi getById(int id) throws SQLException;
    List<DetailTransaksi> getAll() throws SQLException;
}