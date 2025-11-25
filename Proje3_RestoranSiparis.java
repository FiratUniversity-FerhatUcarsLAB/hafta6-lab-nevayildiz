/**
 * Ad Soyad: Neva Yıldız
 * Öğrenci No: 250541034
 * Proje: Restoran Siparis
 * Tarih: 25.11.2025
 */



import java.util.Scanner;

public class Main {

    // === 1) Ana Yemek Fiyatı ===
    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85;   // Izgara Tavuk
            case 2: return 120;  // Adana Kebap
            case 3: return 110;  // Levrek
            case 4: return 65;   // Mantı
            default: return 0;
        }
    }

    // === 2) Başlangıç Fiyatı ===
    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25;   // Çorba
            case 2: return 45;   // Humus
            case 3: return 55;   // Sigara Böreği
            default: return 0;
        }
    }

    // === 3) İçecek Fiyatı ===
    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15;   // Kola
            case 2: return 12;   // Ayran
            case 3: return 35;   // Meyve Suyu
            case 4: return 25;   // Limonata
            default: return 0;
        }
    }

    // === 4) Tatlı Fiyatı ===
    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65;   // Künefe
            case 2: return 55;   // Baklava
            case 3: return 35;   // Sütlaç
            default: return 0;
        }
    }

    // === 5) Combo Menü Kontrolü ===
    public static boolean isComboOrder(boolean anaVar, boolean icecekVar, boolean tatliVar) {
        return anaVar && icecekVar && tatliVar;
    }

    // === 6) Happy Hour Kontrolü ===
    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat <= 17;
    }

    // === 7) İndirim Hesaplama ===
    // combo → %15, happy hour → içeceklerde %20, öğrenci → %10 (hafta içi), 200+ → %10
    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, boolean happyHour, 
                                           double drinkPrice, boolean haftaIci) {

        double discount = 0;

        // Combo Menüsü indirimi
        if (combo) {
            discount += tutar * 0.15;
        }

        // Happy Hour → sadece içecek azaltılır
        if (happyHour) {
            discount += drinkPrice * 0.20;
        }

        // Öğrenci indirimi (hafta içi)
        if (ogrenci && haftaIci) {
            discount += tutar * 0.10;
        }

        // 200 TL üzeri → %10 indirim
        if (tutar >= 200) {
            discount += tutar * 0.10;
        }

        return discount;
    }

    // === 8) Bahşiş Önerisi Hesaplama ===
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10; // %10
    }


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("=== AKILLI RESTORAN SIPARIS SISTEMI ===");

        // Saat bilgisi (Happy Hour için)
        System.out.print("Saat (0-23): ");
        int saat = input.nextInt();
        boolean happyHour = isHappyHour(saat);

        // Hafta içi mi?
        System.out.print("Hafta içi mi? (1=Evet, 2=Hayır): ");
        int hafta = input.nextInt();
        boolean haftaIci = (hafta == 1);

        // Öğrenci misiniz?
        System.out.print("Öğrenci misiniz? (1=Evet, 2=Hayır): ");
        int o = input.nextInt();
        boolean ogrenci = (o == 1);


        // === Menü Seçimleri ===
        System.out.print("\nAna Yemek Seçimi (0=Yok, 1-4): ");
        int ana = input.nextInt();
        boolean anaVar = ana != 0;

        System.out.print("Başlangıç Seçimi (0=Yok, 1-3): ");
        int bas = input.nextInt();

        System.out.print("İçecek Seçimi (0=Yok, 1-4): ");
        int icecek = input.nextInt();
        boolean icecekVar = icecek != 0;

        System.out.print("Tatlı Seçimi (0=Yok, 1-3): ");
        int tatli = input.nextInt();
        boolean tatliVar = tatli != 0;


        // === Fiyat Hesapları ===
        double mainPrice = getMainDishPrice(ana);
        double appPrice = getAppetizerPrice(bas);
        double drinkPrice = getDrinkPrice(icecek);
        double dessPrice = getDessertPrice(tatli);

        double toplam = mainPrice + appPrice + drinkPrice + dessPrice;

        boolean combo = isComboOrder(anaVar, icecekVar, tatliVar);

        double discount = calculateDiscount(toplam, combo, ogrenci, happyHour, drinkPrice, haftaIci);
        double odenecek = toplam - discount;

        double tip = calculateServiceTip(odenecek);


        // === SONUÇ ===
        System.out.println("\n=== SIPARIS OZETI ===");
        System.out.println("Toplam Tutar: " + toplam + "₺");
        System.out.println("İndirim: -" + discount + "₺");
        System.out.println("Ödenecek Tutar: " + odenecek + "₺");
        System.out.println("Bahşiş Önerisi (%10): " + tip + "₺");
    }
}
