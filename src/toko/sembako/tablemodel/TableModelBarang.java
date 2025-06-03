package toko.sembako.tablemodel;

import toko.sembako.entity.Barang;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModelBarang extends AbstractTableModel {
    private ArrayList<Barang> listBarang = new ArrayList<>();

    @Override
    public int getRowCount() {
        return listBarang.size();
    }

    @Override
    public int getColumnCount() {
        return 6; // id, nama, harga, stok, nama_kategori, nama_supplier
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Barang barang = listBarang.get(rowIndex);
        switch (columnIndex) {
            case 0: return barang.getId();
            case 1: return barang.getNama();
            case 2: return barang.getHarga();
            case 3: return barang.getStok();
            case 4: return barang.getNamaKategori();
            case 5: return barang.getNamaSupplier();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "ID";
            case 1: return "Nama";
            case 2: return "Harga";
            case 3: return "Stok";
            case 4: return "Nama Kategori";
            case 5: return "Nama Supplier";
            default: return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: // ID
            case 3: // Stok
                return Integer.class;
            case 2: // Harga
                return Double.class;
            case 1: // Nama
            case 4: // Nama Kategori
            case 5: // Nama Supplier
                return String.class;
            default:
                return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Tabel tidak dapat diedit langsung
    }

    public void insertBarang(Barang barang) {
        listBarang.add(barang);
        fireTableRowsInserted(listBarang.size() - 1, listBarang.size() - 1);
    }

    public void updateBarang(int index, Barang barang) {
        listBarang.set(index, barang);
        fireTableRowsUpdated(index, index);
    }

    public void deleteBarang(int index) {
        listBarang.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public void setData(ArrayList<Barang> listBarang) {
        this.listBarang = listBarang != null ? listBarang : new ArrayList<>();
        fireTableDataChanged();
    }

    public Barang getBarang(int index) {
        return listBarang.get(index);
    }

    public void clear() {
        listBarang.clear();
        fireTableDataChanged();
    }
}