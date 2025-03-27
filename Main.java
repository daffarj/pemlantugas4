import java.util.*;
public class Main {
    
    // deklarasi variabel objek Akun dan inisialisasi data akun
    private static Akun[] pelanggan = {
        new Akun("Andi", "3812345678", 500000, "1234"),
        new Akun("Budi", "5612345678", 1500000, "5678"),
        new Akun("Citra", "7412345678", 2500000, "9876")
    };

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // looping untuk menjalankan program berkali kali
        while (true) {
            System.out.println("==== Selamat datang di program swalayan Tiny ====");
            System.out.println("Pilih menu dibawah");
            System.out.println("1. Top up");
            System.out.println("2. Pembelian");
            System.out.println("3. Keluar");
            System.out.print("pilihan :");
            int pilihan = input.nextInt();
            input.nextLine();

            // break untuk menghentikan looping
            if (pilihan == 3) break;

            System.out.println("Masukkan No Pelanggan : ");
            String noPelanggan = input.nextLine();
            // method cari akun dipanggil untuk menentukan apakah akun yang diinputkan tersedia
            // Serta instansiasi objek Akun
            Akun akun = cariAkun(noPelanggan);

            if (akun == null) {
                System.out.println("No Pelanggan tidak ditemukan !!");
                continue;
            }

            System.out.println("Masukkan Pin : ");
            String pin = input.nextLine();

            // if statement untuk mengautentikasi pin (method autentikasi ada di class Akun)
            if (!akun.autentikasi(pin)) continue;

            // Menu top up menggunakan method topUp di class TransaksiPengguna
            if (pilihan == 1) {
                System.out.println("Masukkan nominal top Up : ");
                double jumlah = input.nextDouble();
                TransaksiPengguna.topUp(akun, jumlah);
            } else if (pilihan == 2) {
                System.out.println("Masukkan Jumlah Pembelian : ");
                double beli = input.nextDouble();
                TransaksiPengguna.beli(akun, beli);
            }
        }
        System.out.println("Terima kasih telah menggunakan layanan sistem swalayan Tiny!");
        input.close();
    }

    // method cariAkun digunakan untuk memastikan apakah akun yang diinputkan tersedia
    private static Akun cariAkun(String noPelanggan){
        for (Akun akun : pelanggan){
            if (akun.getNoPelanggan().equals(noPelanggan)) {
                return akun;
            }
        }
        return null;
    }
    
}
