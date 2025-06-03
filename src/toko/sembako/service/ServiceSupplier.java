package toko.sembako.service;

import toko.sembako.entity.Supplier;
import java.sql.SQLException;
import java.util.List;

public interface ServiceSupplier {
    void insert(Supplier supplier) throws SQLException;
    void update(Supplier supplier) throws SQLException;
    void delete(Supplier supplier) throws SQLException;
    Supplier getById(int id) throws SQLException;
    List<Supplier> getAll() throws SQLException;
}