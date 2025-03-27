# pemlantugas4

**code**
1. [Akun.java](Akun.java)
2. [TransaksiPengguna.java](TransaksiPengguna.java)
3. [Main.java](Main.java)

## 1. Class Akun
### Method bertipe boolean untuk autentikasi akun
Method  ini akan menentukan apakah akun tersebut masih bisa digunakan atau sudah diblokir
Akun diblokir apabila salah pin 3 kali

```java
 // method digunakan untuk menentukan apakah akun tsb masih bisa digunakan atau sudah diblokir
    // (apabila lebih dari 3 kali salah pin akun diblokir)
    public boolean autentikasi(String inputPin){
        if (isBlocked) {
            System.out.println("Akun telah diblokir!");
            return false;
        }
        if (pin.equals(inputPin)) {
            failedAttempts = 0;
            return true;
            
        } else{
            failedAttempts ++;
            if (failedAttempts >= 3) {
                isBlocked = true;
                System.out.println("Akun telah diblokir karena 3 kali kesalahan memasukkan pin!");
            } else{
                System.out.println("Pin salah!! Anda telah mencoba " + failedAttempts + " kali.");
            }
            return false;
        }
    }
```
## 2. Class Transaksi Pengguna
### Method bertipe boolean untuk menu TopUp 
ketentuan jumlah top up lebih dari 0 jika kurang maka topUp gagal
``` java
// method tipe boolean untuk top up saldo menentukan apakah ketentuan top up sesuai aturan
    // (jumlah top up tidak kurang dari 0)
    public static boolean topUp(Akun akun, double jumlah){
        if (jumlah > 0) {
            akun.setSaldo(akun.getSaldo()+jumlah);
            System.out.println("Top up berhasil! Saldo baru : Rp." +akun.getSaldo());
            return true;
        }
        return false;
    }
```
### Method bertipe boolean
Digunakan untuk transaksi pembelian dan menetukan saldo pelanggan cukup atau tidak
```java
// method tipe boolean untuk transaksi pembelian dan menetukan saldo pelanggan cukup atau tidak
    public static boolean beli(Akun akun, double jumlah){
        if (jumlah > akun.getSaldo()) {
            System.out.println("Saldo tidak cukup!");
            return false;
        }

        // operasi menggunakan method hitungCashback untuk menentukan berapa cashback yang didapat pelanggan
        double cashback = hitungCashback(akun.getNoPelanggan(), jumlah);
        akun.setSaldo(akun.getSaldo() - jumlah + cashback);
        System.out.println("Selamat anda mendapat cashback sejumlah : " + cashback);

        // if statement digunakan untuk menentukan apakah saldo pelanggan pasca transaksi kurang dari 10.000
        // (apabila kurang maka transaksi gagal)
        if (akun.getSaldo() < 10000) {
            akun.setSaldo(akun.getSaldo() + jumlah - cashback);
            System.out.println("Transaksi gagal saldo tidak boleh kurang dari Rp. 10.000!!");
            return false;
        }

        System.out.println("Pembelian berhasil saldo terbaru = Rp. " + akun.getSaldo());
        return true;
    }
```
### Method bertipe double
Method ini digunakan untuk menghitung jumlah cashback yang diterima
```java
 // method untuk perhitungan cashback
    private static double hitungCashback(String noPelanggan, double jumlah){
        if (noPelanggan.startsWith("38")) {
            return (jumlah >= 1000000) ? jumlah * 0.05 : 0;
        } else if (noPelanggan.startsWith("56")) {
            return (jumlah >= 1000000) ? jumlah * 0.7 : jumlah * 0.02;
        } else if (noPelanggan.startsWith("74")) {
            return (jumlah >= 1000000) ? jumlah * 0.10 : jumlah * 0.05;
        }
        return 0;
    }
```
## 3. Class Main
### Deklarasi Variabel objek Akun dan inisialisasi data Akun menggunakan data Array
Data array digunakan untuk memasukan list data Akun yang banyak
``` java
 // deklarasi variabel objek Akun dan inisialisasi data akun
    private static Akun[] pelanggan = {
        new Akun("Andi", "3812345678", 500000, "1234"),
        new Akun("Budi", "5612345678", 1500000, "5678"),
        new Akun("Citra", "7412345678", 2500000, "9876")
    };
```
### looping untuk mengulang program agar berjalan terus sampai dihentikan
Looping while digunakan untuk menu program dan memberikan user pilihan untuk memilih fitur apa yang ingin digunakan lalu diisi dengan operasi dengan memanggil method dari class `Akun.java` dan class `TransaksiPengguna.java` sesuai dengan yang dibutuhkan 
```java
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
```
### Method cari akun untuk mencari data akun yang sesuai dengan inputan User
```java
  // method cariAkun digunakan untuk memastikan apakah akun yang diinputkan tersedia
    private static Akun cariAkun(String noPelanggan){
        for (Akun akun : pelanggan){
            if (akun.getNoPelanggan().equals(noPelanggan)) {
                return akun;
            }
        }
        return null;
    }
```
## 4. Program Berjalan
