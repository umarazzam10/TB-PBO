import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        Scanner terminalInput = new Scanner(System.in);
        String pilihanUser;
        boolean isLanjutkan = true;

        Admin admin = new Admin();
        Login adm = new Login();

        adm.login();
        
        while (isLanjutkan) {
            clearScreen();
            System.out.println("Database ShowRoom Thelema\n");
            System.out.println("1.\tLihat Data Pelanggan");
            System.out.println("2.\tTambah Data Pelanggan");
            System.out.println("3.\tEdit Data Pelanggan");
            System.out.println("4.\tHapus Data Pelanggan");
            System.out.println("5.\tPrint Struk");
            System.out.println("6.\tKeluar");

            System.out.print("\n\nPilihan anda: ");
            pilihanUser = terminalInput.next();

            switch (pilihanUser) {
                case "1":
                    clearScreen();
                    System.out.println("\n==============================");
                    System.out.println("     LIST DATA PELANGGAN      ");
                    System.out.println("==============================");
                    admin.viewData();
                    break;
                case "2":
                    clearScreen();
                    System.out.println("\n==============================");
                    System.out.println("     TAMBAH DATA PELANGGAN    ");
                    System.out.println("==============================");
                    admin.addData();
                    break;
                case "3":
                    clearScreen();
                    System.out.println("\n==============================");
                    System.out.println("     UBAH DATA PELANGGAN      ");
                    System.out.println("==============================");
                    admin.updateData();
                    admin.viewData();
                    break;
                case "4":
                    clearScreen();
                    System.out.println("\n==============================");
                    System.out.println("     HAPUS DATA PELANGGAN     ");
                    System.out.println("==============================");
                    admin.deleteData();
                    break;
                 case "5":
                    clearScreen();
                    System.out.println("\n==============================");
                    System.out.println("         STRUK BELANJA        ");
                    System.out.println("==============================");
                    admin.printStruk();
                    break;    
                case "6":
                    clearScreen();
                    System.out.println("\n==============================");
                    System.out.println("ANDA TELAH KELUAR, SILAHKAN LOGIN KEMBALI");
                    System.out.println("==============================");
                    System.exit(0);
                    break;
                default:
                    System.err.println("\nInput anda tidak ditemukan\nSilahkan pilih [1-6]");
            }

            isLanjutkan = admin.getYesorNo("Apakah Anda ingin melanjutkan");
        }

    }

        private static void clearScreen(){
        try {
            if (System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (Exception ex){
            System.err.println("tidak bisa clear screen");
        }
        
    }
}
