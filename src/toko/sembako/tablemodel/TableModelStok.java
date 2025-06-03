package toko.sembako.tablemodel;

import toko.sembako.entity.Stok;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModelStok extends AbstractTableModel {
    private ArrayList<Stok> listStok = new ArrayList<>();

    @Override
    public int getRowCount() {
        return listStok.size();
    }

    @Override
    public int getColumnCount() {
        return 5; // id, nama_barang, jumlah_stok, stok_minimum, tanggal_update
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Stok stok = listStok.get(rowIndex);
        switch (columnIndex) {
            case 0: return stok.getId();
            case 1: return stok.getNamaBarang(); // Nama barang akan diisi melalui join
            case 2: return stok.getJumlahStok();
            case 3: return stok.getStokMinimum();
            case 4: return stok.getTanggalUpdate();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "ID";
            case 1: return "Nama Barang";
            case 2: return "Jumlah Stok";
            case 3: return "Stok Minimum";
            case 4: return "Tanggal Update";
            default: return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: // ID
            case 2: // Jumlah Stok
            case 3: // Stok Minimum
                return Integer.class;
            case 1: // Nama Barang
                return String.class;
            case 4: // Tanggal Update
                return java.util.Date.class;
            default:
                return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Tabel tidak dapat diedit langsung
    }

    public void insertStok(Stok stok) {
        listStok.add(stok);
        fireTableRowsInserted(listStok.size() - 1, listStok.size() - 1);
    }

    public void updateStok(int index, Stok stok) {
        listStok.set(index, stok);
        fireTableRowsUpdated(index, index);
    }

    public void deleteStok(int index) {
        listStok.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public void setData(ArrayList<Stok> listStok) {
        this.listStok = listStok != null ? listStok : new ArrayList<>();
        fireTableDataChanged();
    }

    public Stok getStok(int index) {
        return listStok.get(index);
    }

    public void clear() {
        listStok.clear();
        fireTableDataChanged();
    }
}