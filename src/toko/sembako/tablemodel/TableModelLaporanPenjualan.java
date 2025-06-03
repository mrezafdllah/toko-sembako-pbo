package toko.sembako.tablemodel;

import toko.sembako.entity.LaporanPenjualan;
import toko.sembako.util.CurrencyFormatter;
import toko.sembako.util.DateUtil;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableModelLaporanPenjualan extends AbstractTableModel {
    private List<LaporanPenjualan> data = new ArrayList<>();
    private final String[] columnNames = {"ID Transaksi", "Tanggal", "Nama Barang", "Jumlah", "Harga Satuan", "Subtotal"};

    public void setData(List<LaporanPenjualan> data) {
        this.data = data;
        fireTableDataChanged();
    }

    public List<LaporanPenjualan> getData() {
        return data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LaporanPenjualan penjualan = data.get(rowIndex);
        switch (columnIndex) {
            case 0: return penjualan.getTransaksiId();
            case 1: return DateUtil.formatDateTime(penjualan.getTanggalTransaksi());
            case 2: return penjualan.getNamaBarang();
            case 3: return penjualan.getJumlah();
            case 4: return CurrencyFormatter.formatCurrency(penjualan.getHargaSatuan());
            case 5: return CurrencyFormatter.formatCurrency(penjualan.getSubtotal());
            default: return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 3:
                return Integer.class;
            case 1:
            case 2:
            case 4:
            case 5:
                return String.class;
            default:
                return Object.class;
        }
    }
}