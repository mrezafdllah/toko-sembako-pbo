package toko.sembako.entity;

import java.sql.Timestamp;

public class LaporanPenjualan {
    private int transaksiId;
    private Timestamp tanggalTransaksi;
    private String namaBarang;
    private int jumlah;
    private double hargaSatuan;
    private double subtotal;

    public LaporanPenjualan(int transaksiId, Timestamp tanggalTransaksi, String namaBarang, int jumlah, double hargaSatuan, double subtotal) {
        this.transaksiId = transaksiId;
        this.tanggalTransaksi = tanggalTransaksi;
        this.namaBarang = namaBarang;
        this.jumlah = jumlah;
        this.hargaSatuan = hargaSatuan;
        this.subtotal = subtotal;
    }

    public int getTransaksiId() {
        return transaksiId;
    }

    public void setTransaksiId(int transaksiId) {
        this.transaksiId = transaksiId;
    }

    public Timestamp getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(Timestamp tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double getHargaSatuan() {
        return hargaSatuan;
    }

    public void setHargaSatuan(double hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}