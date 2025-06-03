package toko.sembako.entity;

public class Kategori {
    private int id;
    private String nama_kategori;
    private String deskripsi;

    public Kategori() {}

    public Kategori(int id, String nama_kategori, String deskripsi) {
        this.id = id;
        this.nama_kategori = nama_kategori;
        this.deskripsi = deskripsi;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNamaKategori() { return nama_kategori; }
    public void setNamaKategori(String namaKategori) { this.nama_kategori = namaKategori; }
    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public String getNama() {
        return nama_kategori; // Mengembalikan nama_kategori
    }
}