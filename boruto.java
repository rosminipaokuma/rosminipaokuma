import java.util.Scanner;

public class boruto {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Data login untuk admin
        String adminUsername = "admin";
        String adminPassword = "admin123";

        // Data login untuk mahasiswa
        String[] mahasiswaNIMs = {"123456789012345", "987654321012345"};

        System.out.println("Selamat datang di sistem login library");

        // Meminta user memilih jenis login
        System.out.println("Pilih jenis login: ");
        System.out.println("1. Admin");
        System.out.println("2. Mahasiswa");
        int choice = input.nextInt();

        if (choice == 1) {
            // Login sebagai admin
            System.out.println("Masukkan username: ");
            String username = input.next();
            System.out.println("Masukkan password: ");
            String password = input.next();

            if (username.equals(adminUsername) && password.equals(adminPassword)) {
                System.out.println("Login admin berhasil");
            } else {
                System.out.println("Login gagal, username atau password salah");
            }
        } else if (choice == 2) {
            // Login sebagai mahasiswa
            System.out.println("Masukkan NIM (harus 15 digit): ");
            String nim = input.next();

            if (isValidNIM(mahasiswaNIMs, nim)) {
                System.out.println("Login mahasiswa berhasil");
            } else {
                System.out.println("Login gagal, NIM tidak valid");
            }
        } else {
            System.out.println("Pilihan tidak valid");
        }
    }

    // Fungsi untuk validasi NIM mahasiswa
    public static boolean isValidNIM(String[] nims, String nim) {
        for (String validNIM : nims) {
            if (nim.equals(validNIM) && nim.length() == 15) {
                return true;
            }
        }
        return false;
    }
}

