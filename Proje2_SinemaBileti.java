/**
 * Ad Soyad: Neva Yıldız
 * Öğrenci No: 250541034
 * Proje: Sinema Bileti
 * Tarih: 25.11.2025
 */





import java.util.Scanner;

public class Main {

    // 1) Hafta sonu kontrolü
    public static boolean isWeekend(int gun) {
        return gun == 6 || gun == 7; // Cumartesi - Pazar
    }

    // 2) Matine kontrolü (12:00 öncesi)
    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    // 3) Temel fiyat hesaplama
    public static double calculateBasePrice(int gun, int saat) {
        boolean weekend = isWeekend(gun);
        boolean matinee = isMatinee(saat);

        if (!weekend && matinee) return 45;       // Hafta içi matine
        if (!weekend) return 65;                 // Hafta içi normal
        if (weekend && matinee) return 55;       // Hafta sonu matine
        return 85;                               // Hafta sonu normal
    }

    // 4) Meslek + yaş indirimi hesaplama
    public static double calculateDiscount(int yas, int meslek, int gun) {

        // Yaş indirimleri her zaman uygulanır
        if (yas >= 65) return 0.30;
        if (yas < 12) return 0.25;

        // Öğrenci
        if (meslek == 1) {
            if (gun >= 1 && gun <= 4) return 0.20;   // Pzt - Prş
            else return 0.15;                        // Cum - Paz
        }

        // Öğretmen
        if (meslek == 2) {
            if (gun == 3) return 0.35;               // Çarşamba
        }

        return 0.0; // Diğer
    }

    // 5) Film formatı ek fiyatı
    public static double getFormatExtra(int tur) {
        switch (tur) {
            case 1: return 0;     // 2D
            case 2: return 25;    // 3D
            case 3: return 35;    // IMAX
            case 4: return 50;    // 4DX
            default: return 0;
        }
    }

    // 6) Nihai fiyat hesaplama
    public static double calculateFinalPrice(double base, double discountRate, double extra) {
        double discount = base * discountRate;
        double indirimli = base - discount;
        return indirimli + extra;
    }

    // 7) Bilet bilgisini yazdırma
    public static void generateTicketInfo(int gun, int saat, int yas, int meslek, int tur) {

        // Gün adları
        String gunAdi = "";
        switch (gun) {
            case 1: gunAdi = "Pazartesi"; break;
            case 2: gunAdi = "Salı"; break;
            case 3: gunAdi = "Çarşamba"; break;
            case 4: gunAdi = "Perşembe"; break;
            case 5: gunAdi = "Cuma"; break;
            case 6: gunAdi = "Cumartesi"; break;
            case 7: gunAdi = "Pazar"; break;
        }

        double base = calculateBasePrice(gun, saat);
        double discountRate = calculateDiscount(yas, meslek, gun);
        double discountAmount = base * discountRate;
        double priceAfterDiscount = base - discountAmount;
        double extra = getFormatExtra(tur);
        double total = calculateFinalPrice(base, discountRate, extra);

        System.out.println("\n=== BILET BILGISI ===");
        System.out.println("Gün: " + gunAdi);
        System.out.println("Saat: " + saat);
        System.out.println("Yaş: " + yas);

        String meslekAdi = (meslek == 1) ? "Öğrenci" :
                           (meslek == 2) ? "Öğretmen" : "Diğer";
        System.out.println("Meslek: " + meslekAdi);

        String turAdi = "";
        switch (tur) {
            case 1: turAdi = "2D"; break;
            case 2: turAdi = "3D"; break;
            case 3: turAdi = "IMAX"; break;
            case 4: turAdi = "4DX"; break;
        }
        System.out.println("Film Türü: " + turAdi);

        System.out.println("-------------------------------");
        System.out.println("Temel Fiyat: " + base + " TL");
        System.out.println("İndirim (" + (int)(discountRate * 100) + "%): -" + discountAmount + " TL");
        System.out.println("İndirimli Fiyat: " + priceAfterDiscount + " TL");
        System.out.println("Format Ekstrası: +" + extra + " TL");
        System.out.println("TOPLAM: " + total + " TL");
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Gün (1-7): ");
        int gun = input.nextInt();

        System.out.print("Saat (8-23): ");
        int saat = input.nextInt();

        System.out.print("Yaş: ");
        int yas = input.nextInt();

        System.out.print("Meslek (1=Öğrenci, 2=Öğretmen, 3=Diğer): ");
        int meslek = input.nextInt();

        System.out.print("Film Türü (1=2D, 2=3D, 3=IMAX, 4=4DX): ");
        int tur = input.nextInt();

        generateTicketInfo(gun, saat, yas, meslek, tur);
    }
}

