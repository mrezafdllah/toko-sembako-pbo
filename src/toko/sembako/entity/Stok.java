package toko.sembako.entity;

import java.util.Date;

public class Stok {
    private int id;
    private int barangId;
    private int jumlahStok;
    private int stokMinimum;
    private Date tanggalUpdate;
    private String namaBarang; // Untuk menampilkan nama barang di tabel

    public Stok() {}

    public Stok(int id, int barangId, int jumlahStok, int stokMinimum, Date tanggalUpdate) {
        this.id = id;
        this.barangId = barangId;
        this.jumlahStok = jumlahStok;
        this.stokMinimum = stokMinimum;
        this.tanggalUpdate = tanggalUpdate;
    }

    public Stok(int id, int barangId, int jumlahStok, int stokMinimum, Date tanggalUpdate, String namaBarang) {
        this.id = id;
        this.barangId = barangId;
        this.jumlahStok = jumlahStok;
        this.stokMinimum = stokMinimum;
        this.tanggalUpdate = tanggalUpdate;
        this.namaBarang = namaBarang;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getBarangId() { return barangId; }
    public void setBarangId(int barangId) { this.barangId = barangId; }
    public int getJumlahStok() { return jumlahStok; }
    public void setJumlahStok(int jumlahStok) { this.jumlahStok = jumlahStok; }
    public int getStokMinimum() { return stokMinimum; }
    public void setStokMinimum(int stokMinimum) { this.stokMinimum = stokMinimum; }
    public Date getTanggalUpdate() { return tanggalUpdate; }
    public void setTanggalUpdate(Date tanggalUpdate) { this.tanggalUpdate = tanggalUpdate; }
    public String getNamaBarang() { return namaBarang; }
    public void setNamaBarang(String namaBarang) { this.namaBarang = namaBarang; }
}