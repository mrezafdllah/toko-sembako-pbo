package toko.sembako.entity;

public class Barang {
    private int id;
    private String nama;
    private double harga;
    private int stok;
    private int kategoriId;
    private int supplierId;
    private String namaKategori; // Menyimpan nama kategori
    private String namaSupplier; // Menyimpan nama supplier

    public Barang() {}

    public Barang(int id, String nama, double harga, int stok, int kategoriId, int supplierId) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
        this.kategoriId = kategoriId;
        this.supplierId = supplierId;
    }

    public Barang(int id, String nama, double harga, int stok, int kategoriId, int supplierId, String namaKategori, String namaSupplier) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
        this.kategoriId = kategoriId;
        this.supplierId = supplierId;
        this.namaKategori = namaKategori;
        this.namaSupplier = namaSupplier;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }
    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }
    public int getKategoriId() { return kategoriId; }
    public void setKategoriId(int kategoriId) { this.kategoriId = kategoriId; }
    public int getSupplierId() { return supplierId; }
    public void setSupplierId(int supplierId) { this.supplierId = supplierId; }
    public String getNamaKategori() { return namaKategori; }
    public void setNamaKategori(String namaKategori) { this.namaKategori = namaKategori; }
    public String getNamaSupplier() { return namaSupplier; }
    public void setNamaSupplier(String namaSupplier) { this.namaSupplier = namaSupplier; }
}