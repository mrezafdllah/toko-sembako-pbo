package toko.sembako.entity;

public class DetailTransaksi {
    private int id;
    private int transaksiId;
    private int barangId;
    private int jumlah;
    private double hargaSatuan;
    private double subtotal;

    public DetailTransaksi() {}

    public DetailTransaksi(int id, int transaksiId, int barangId, int jumlah, double hargaSatuan, double subtotal) {
        this.id = id;
        this.transaksiId = transaksiId;
        this.barangId = barangId;
        this.jumlah = jumlah;
        this.hargaSatuan = hargaSatuan;
        this.subtotal = subtotal;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getTransaksiId() { return transaksiId; }
    public void setTransaksiId(int transaksiId) { this.transaksiId = transaksiId; }
    public int getBarangId() { return barangId; }
    public void setBarangId(int barangId) { this.barangId = barangId; }
    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }
    public double getHargaSatuan() { return hargaSatuan; }
    public void setHargaSatuan(double hargaSatuan) { this.hargaSatuan = hargaSatuan; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}