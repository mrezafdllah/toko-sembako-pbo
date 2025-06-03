package toko.sembako.tablemodel;

import toko.sembako.entity.Supplier;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModelSupplier extends AbstractTableModel {
    private ArrayList<Supplier> listSupplier = new ArrayList<>();

    @Override
    public int getRowCount() {
        return listSupplier.size();
    }

    @Override
    public int getColumnCount() {
        return 4; // id, nama, alamat, telepon
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Supplier supplier = listSupplier.get(rowIndex);
        switch (columnIndex) {
            case 0: return supplier.getId();
            case 1: return supplier.getNama();
            case 2: return supplier.getAlamat();
            case 3: return supplier.getTelepon();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "ID";
            case 1: return "Nama";
            case 2: return "Alamat";
            case 3: return "Telepon";
            default: return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return Integer.class; // ID
            default: return String.class; // Nama, Alamat, Telepon
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Tabel tidak dapat diedit langsung
    }

    public void insertSupplier(Supplier supplier) {
        listSupplier.add(supplier);
        fireTableRowsInserted(listSupplier.size() - 1, listSupplier.size() - 1);
    }

    public void updateSupplier(int index, Supplier supplier) {
        listSupplier.set(index, supplier);
        fireTableRowsUpdated(index, index);
    }

    public void deleteSupplier(int index) {
        listSupplier.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public void setData(ArrayList<Supplier> listSupplier) {
        this.listSupplier = listSupplier != null ? listSupplier : new ArrayList<>();
        fireTableDataChanged();
    }

    public Supplier getSupplier(int index) {
        return listSupplier.get(index);
    }

    public void clear() {
        listSupplier.clear();
        fireTableDataChanged();
    }
}