class Akun {
    // atribut utama class Akun
    private String nama;
    private String noPelanggan;
    private double saldo;
    private String pin;
    private Boolean isBlocked = false;
    private int failedAttempts = 0;
    
    // overloading agar bisa memasukkan data dengan dua cara (lebih fleksibel)
    public Akun(){}

    // constructor untuk memasukan data akun data pelanggan
    public Akun(String nama, String noPelanggan, double saldo, String pin){
        this.nama = nama;
        this.noPelanggan = noPelanggan;
        this.saldo = saldo;
        this.pin = pin;
    }

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

    // getter untuk noPelanggan
    public String getNoPelanggan(){
        return noPelanggan;
    }

    // getter untuk saldo pelanggan
    public double getSaldo(){
        return saldo;
    }

    // setter saldo pelanggan 
    public void setSaldo(double saldo){
        this.saldo = saldo;
    }
}
