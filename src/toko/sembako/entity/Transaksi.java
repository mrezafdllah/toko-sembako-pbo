package toko.sembako.entity;

import java.util.Date;

public class Transaksi {
    private int id;
    private Date tanggal;
    private double total;
    private String kasir;

    public Transaksi() {}

    public Transaksi(int id, Date tanggal, double total, String kasir) {
        this.id = id;
        this.tanggal = tanggal;
        this.total = total;
        this.kasir = kasir;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Date getTanggal() { return tanggal; }
    public void setTanggal(Date tanggal) { this.tanggal = tanggal; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public String getKasir() { return kasir; }
    public void setKasir(String kasir) { this.kasir = kasir; }
}