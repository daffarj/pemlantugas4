class TransaksiPengguna {

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
}
