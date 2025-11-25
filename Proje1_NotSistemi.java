/**
 * Ad Soyad: Neva Yıldız
 * Öğrenci No: 250541034
 * Proje: Not Sistemi
 * Tarih: 25.11.2025
 */




import java.util.Scanner;

public class NotSistemi {

    // 1) Ortalama Hesaplayan Metot
    public static double calculateAverage(int vize_notu, int final_notu, int odev_notu) {
        return vize_notu * 0.3 + final_notu * 0.4 + odev_notu * 0.3;
    }

    // 2) Geçme Durumu
    public static boolean isPassingGrade(double ortalama) {
        return ortalama >= 50;
    }

    // 3) Harf Notu
    public static String getLetterGrade(double ortalama) {
        String harf_notu;
        if (ortalama >= 90 && ortalama <= 100) {
            harf_notu = "A";
        } else if (ortalama >= 80 && ortalama <= 89) {
            harf_notu = "B";
        } else if (ortalama >= 70 && ortalama <= 79) {
            harf_notu = "C";
        } else if (ortalama >= 60 && ortalama <= 69) {
            harf_notu = "D";
        } else {
            harf_notu = "F";
        }
        return harf_notu;
    }

    // 4) Onur Listesi
    public static boolean isHonorList(double ortalama, int vize_notu, int final_notu, int odev_notu) {
        return ortalama >= 85 && vize_notu >= 70 && final_notu >= 70 && odev_notu >= 70;
    }

    // 5) Bütünleme Hakkı
    public static boolean hasRetakeRight(double ortalama) {
        return (ortalama >= 40 && ortalama < 50);
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int vize_notu, final_notu, odev_notu;

        System.out.print("Vize notunu girin: ");
        vize_notu = scanner.nextInt();

        System.out.print("Final notunu girin: ");
        final_notu = scanner.nextInt();

        System.out.print("Ödev notunu girin: ");
        odev_notu = scanner.nextInt();

        double ortalama = calculateAverage(vize_notu, final_notu, odev_notu);

        System.out.println("\n=== ÖĞRENCİ NOT RAPORU ===");
        System.out.println("Vize Notu  : " + vize_notu);
        System.out.println("Final Notu : " + final_notu);
        System.out.println("Ödev Notu  : " + odev_notu);
        System.out.println("-------------------------------");
        System.out.println("Ortalama   : " + ortalama);
        System.out.println("Harf Notu  : " + getLetterGrade(ortalama));

        System.out.println("Durum      : " + (isPassingGrade(ortalama) ? "GEÇTİ" : "KALDI"));

        System.out.println("Onur Listesi: " + (isHonorList(ortalama, vize_notu, final_notu, odev_notu) ? "EVET" : "HAYIR"));

        System.out.println("Bütünleme  : " + (hasRetakeRight(ortalama) ? "VAR" : "YOK"));

        scanner.close();
    }
}

