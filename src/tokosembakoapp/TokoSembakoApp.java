package tokosembakoapp;

import toko.sembako.gui.FormMenuUtama;

public class TokoSembakoApp {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new FormMenuUtama().setVisible(true);
        });
    }
}