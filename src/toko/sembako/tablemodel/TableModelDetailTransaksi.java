package toko.sembako.tablemodel;

import toko.sembako.entity.DetailTransaksi;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModelDetailTransaksi extends AbstractTableModel {
    private ArrayList<DetailTransaksi> listDetailTransaksi = new ArrayList<>();

    @Override
    public int getRowCount() {
        return listDetailTransaksi.size();
    }

    @Override
    public int getColumnCount() {
        return 6; // id, transaksi_id, barang_id, jumlah, harga_satuan, subtotal
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DetailTransaksi detail = listDetailTransaksi.get(rowIndex);
        switch (columnIndex) {
            case 0: return detail.getId();
            case 1: return detail.getTransaksiId();
            case 2: return detail.getBarangId();
            case 3: return detail.getJumlah();
            case 4: return detail.getHargaSatuan();
            case 5: return detail.getSubtotal();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "ID";
            case 1: return "Transaksi ID";
            case 2: return "Barang ID";
            case 3: return "Jumlah";
            case 4: return "Harga Satuan";
            case 5: return "Subtotal";
            default: return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: // ID
            case 1: // Transaksi ID
            case 2: // Barang ID
            case 3: // Jumlah
                return Integer.class;
            case 4: // Harga Satuan
            case 5: // Subtotal
                return Double.class;
            default:
                return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Tabel tidak dapat diedit langsung
    }

    public void insertDetailTransaksi(DetailTransaksi detail) {
        listDetailTransaksi.add(detail);
        fireTableRowsInserted(listDetailTransaksi.size() - 1, listDetailTransaksi.size() - 1);
    }

    public void updateDetailTransaksi(int index, DetailTransaksi detail) {
        listDetailTransaksi.set(index, detail);
        fireTableRowsUpdated(index, index);
    }

    public void deleteDetailTransaksi(int index) {
        listDetailTransaksi.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public void setData(ArrayList<DetailTransaksi> listDetailTransaksi) {
        this.listDetailTransaksi = listDetailTransaksi != null ? listDetailTransaksi : new ArrayList<>();
        fireTableDataChanged();
    }

    public DetailTransaksi getDetailTransaksi(int index) {
        return listDetailTransaksi.get(index);
    }

    public void clear() {
        listDetailTransaksi.clear();
        fireTableDataChanged();
    }
}