package toko.sembako.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class CurrencyFormatter {
    private static final DecimalFormat formatter;

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        formatter = new DecimalFormat("Rp #,##0.00", symbols);
    }

    public static String formatCurrency(double amount) {
        return formatter.format(amount);
    }

    public static double parseCurrency(String amountStr) {
        try {
            String cleanStr = amountStr.replace("Rp ", "").replace(".", "").replace(",", ".");
            return Double.parseDouble(cleanStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid currency format: " + amountStr);
        }
    }
}