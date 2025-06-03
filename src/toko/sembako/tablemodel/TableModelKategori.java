package toko.sembako.tablemodel;

import toko.sembako.entity.Kategori;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModelKategori extends AbstractTableModel {
    private ArrayList<Kategori> listKategori = new ArrayList<>();

    @Override
    public int getRowCount() {
        return listKategori.size();
    }

    @Override
    public int getColumnCount() {
        return 3; // id, nama_kategori, deskripsi
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kategori kategori = listKategori.get(rowIndex);
        switch (columnIndex) {
            case 0: return kategori.getId();
            case 1: return kategori.getNamaKategori();
            case 2: return kategori.getDeskripsi();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "ID";
            case 1: return "Nama Kategori";
            case 2: return "Deskripsi";
            default: return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return Integer.class; // ID
            case 1: return String.class;  // Nama Kategori
            case 2: return String.class;  // Deskripsi
            default: return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Tabel tidak dapat diedit langsung
    }

    public void insertKategori(Kategori kategori) {
        listKategori.add(kategori);
        fireTableRowsInserted(listKategori.size() - 1, listKategori.size() - 1);
    }

    public void updateKategori(int index, Kategori kategori) {
        listKategori.set(index, kategori);
        fireTableRowsUpdated(index, index);
    }

    public void deleteKategori(int index) {
        listKategori.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public void setData(ArrayList<Kategori> listKategori) {
        this.listKategori = listKategori != null ? listKategori : new ArrayList<>();
        fireTableDataChanged();
    }

    public Kategori getKategori(int index) {
        return listKategori.get(index);
    }

    public void clear() {
        listKategori.clear();
        fireTableDataChanged();
    }
}