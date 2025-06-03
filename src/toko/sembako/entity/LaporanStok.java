package toko.sembako.entity;

import java.sql.Timestamp;

public class LaporanStok {
    private int id;
    private int barangId;
    private String namaBarang;
    private int jumlahStok;
    private int stokMinimum;
    private Timestamp tanggalUpdate;

    public LaporanStok(int id, int barangId, String namaBarang, int jumlahStok, int stokMinimum, Timestamp tanggalUpdate) {
        this.id = id;
        this.barangId = barangId;
        this.namaBarang = namaBarang;
        this.jumlahStok = jumlahStok;
        this.stokMinimum = stokMinimum;
        this.tanggalUpdate = tanggalUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBarangId() {
        return barangId;
    }

    public void setBarangId(int barangId) {
        this.barangId = barangId;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getJumlahStok() {
        return jumlahStok;
    }

    public void setJumlahStok(int jumlahStok) {
        this.jumlahStok = jumlahStok;
    }

    public int getStokMinimum() {
        return stokMinimum;
    }

    public void setStokMinimum(int stokMinimum) {
        this.stokMinimum = stokMinimum;
    }

    public Timestamp getTanggalUpdate() {
        return tanggalUpdate;
    }

    public void setTanggalUpdate(Timestamp tanggalUpdate) {
        this.tanggalUpdate = tanggalUpdate;
    }
}