import java.util.HashMap;
import java.util.Scanner;

public class Login extends Admin{

    public void login(){

        HashMap<String,String> login = new HashMap();

        login.put("umar", "oemarr10");
        login.put("abdul", "abduLL19");
        login.put("admin", "password");

        System.out.println("oooooooooo LOGIN oooooooooooo");
        
        Scanner scannerInput = new Scanner(System.in);

        System.out.print("Username : ");
        String username = scannerInput.nextLine();
        System.out.print("Password : ");
        String password = scannerInput.nextLine();

        while (!login.containsKey(username) && login.get(password).equals(password)) {
            System.err.println("LOGIN GAGAL, SILAHKAN LOGIN KEMBALI\n");
            System.out.print("Username : ");
            username = scannerInput.nextLine();
            System.out.print("Password : ");
            password = scannerInput.nextLine();

        }
        System.out.println("LOGIN BERHASIL, SELAMAT DATANG "+ username);

    }

}