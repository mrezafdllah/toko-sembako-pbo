package toko.sembako.tablemodel;

import toko.sembako.entity.LaporanStok;
import toko.sembako.util.DateUtil;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableModelLaporanStok extends AbstractTableModel {
    private List<LaporanStok> data = new ArrayList<>();
    private final String[] columnNames = {"ID", "Barang ID", "Nama Barang", "Jumlah Stok", "Stok Minimum", "Tanggal Update"};

    public void setData(List<LaporanStok> data) {
        this.data = data;
        fireTableDataChanged();
    }

    public List<LaporanStok> getData() {
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
        LaporanStok stok = data.get(rowIndex);
        switch (columnIndex) {
            case 0: return stok.getId();
            case 1: return stok.getBarangId();
            case 2: return stok.getNamaBarang();
            case 3: return stok.getJumlahStok();
            case 4: return stok.getStokMinimum();
            case 5: return DateUtil.formatDateTime(stok.getTanggalUpdate());
            default: return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 1:
            case 3:
            case 4:
                return Integer.class;
            case 2:
            case 5:
                return String.class;
            default:
                return Object.class;
        }
    }
}