package toko.sembako.tablemodel;

import toko.sembako.entity.Transaksi;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModelTransaksi extends AbstractTableModel {
    private ArrayList<Transaksi> listTransaksi = new ArrayList<>();

    @Override
    public int getRowCount() {
        return listTransaksi.size();
    }

    @Override
    public int getColumnCount() {
        return 4; // id, tanggal, total, kasir
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Transaksi transaksi = listTransaksi.get(rowIndex);
        switch (columnIndex) {
            case 0: return transaksi.getId();
            case 1: return transaksi.getTanggal();
            case 2: return transaksi.getTotal();
            case 3: return transaksi.getKasir();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "ID";
            case 1: return "Tanggal";
            case 2: return "Total";
            case 3: return "Kasir";
            default: return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return Integer.class; // ID
            case 1: return java.util.Date.class; // Tanggal
            case 2: return Double.class; // Total
            case 3: return String.class; // Kasir
            default: return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Tabel tidak dapat diedit langsung
    }

    public void insertTransaksi(Transaksi transaksi) {
        listTransaksi.add(transaksi);
        fireTableRowsInserted(listTransaksi.size() - 1, listTransaksi.size() - 1);
    }

    public void updateTransaksi(int index, Transaksi transaksi) {
        listTransaksi.set(index, transaksi);
        fireTableRowsUpdated(index, index);
    }

    public void deleteTransaksi(int index) {
        listTransaksi.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public void setData(ArrayList<Transaksi> listTransaksi) {
        this.listTransaksi = listTransaksi != null ? listTransaksi : new ArrayList<>();
        fireTableDataChanged();
    }

    public Transaksi getTransaksi(int index) {
        return listTransaksi.get(index);
    }

    public void clear() {
        listTransaksi.clear();
        fireTableDataChanged();
    }
}