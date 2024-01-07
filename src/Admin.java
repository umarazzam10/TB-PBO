import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.sql.*;

public class Admin implements CRUD{
    //Koneksi ke Database
    Connection conn;
    String url = "jdbc:mysql://localhost/db_tbpbo";
    String driver = "com.mysql.jdbc.Driver";

    //Properti
    String faktur, nama, noHP, alamat, namaBarang;
    Integer jmlBarang, hargaBarang, totalBayar;

    //Method untuk menampilkan data dari Database
    @Override
    public void viewData() throws SQLException{
        try {
                String sql = "SELECT * FROM showroom";
                conn = DriverManager.getConnection(url, "root", "");
                Statement st = conn.createStatement();
                ResultSet result = st.executeQuery(sql);

                //perulangan agar data yang ditampilkan tidak hanya 1
                while (result.next()) {
                    System.out.println("No Faktur \t: " + result.getString("Faktur"));
                    System.out.println("Nama Pelanggan \t: " + result.getString("Nama"));
                    System.out.println("No HP \t\t: " + result.getString("no_HP"));
                    System.out.println("Alamat \t\t: " + result.getString("Alamat"));
                    System.out.println("Nama Barang \t: " + result.getString("Barang"));
                    System.out.println("Harga \t\t: " + result.getInt("Harga"));
                    System.out.println("Jumlah \t\t: " + result.getInt("Jumlah"));
                    System.out.println("Total Bayar \t: " + result.getInt("Total") +"\n");
                }

                st.close();
            } catch (Exception e) {
                System.out.println("GAGAL MENAMPILKAN DATA");
            }
    }

    //Method untuk menambahkan data ke Database
    @Override
    public void addData() throws SQLException{

        Scanner scanStr = new Scanner(System.in);
        Scanner scanIn = new Scanner(System.in);
        System.out.print("Masukkan no faktur = ");
        faktur = scanStr.next();
        System.out.print("Masukkan nama pelanggan = ");
        nama = scanStr.next();
        System.out.print("Masukkan no HP = ");
        noHP = scanStr.next();
        System.out.print("Masukkan alamat = ");
        alamat = scanStr.next();
        System.out.print("Masukkan nama barang = ");
        namaBarang = scanStr.next();
        System.out.print("Masukkan harga barang = ");
        hargaBarang = scanIn.nextInt();
        System.out.print("Masukkan jumlah barang = ");
        jmlBarang = scanIn.nextInt();
        totalBayar = hargaBarang * jmlBarang;

        //konfirmasi untuk memasukkan data ke Database
        boolean isTambah = getYesorNo("Apakah ingin menambahkan Data ke Database? ");

        //Percabangan yang bergantung pada boolean
        if (isTambah) {
            try{
                conn = DriverManager.getConnection(url, "root", "");
                Statement st = conn.createStatement();
                String sql = "INSERT INTO showroom (Faktur, Nama, no_HP, Alamat, Barang, Harga, Jumlah, Total) VALUES ('"+ faktur 
                + "','"+ nama + "','"+ noHP + "','"+ alamat + "','"+ namaBarang + "','"+ hargaBarang + "','"+ jmlBarang + "','"+ totalBayar + "') ";
                st.execute(sql);    
                System.out.println("DATA BERHASIL DITAMBAHKAN");

                } catch (Exception e) {
                    System.err.println("DATA GAGAL DITAMBAHKAN");
                }
        }
            
        }

    //Method untuk mengupdate data pada Database
    @Override
    public void updateData() throws SQLException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Masukkan No Faktur Pelanggan yang ingin diubah : ");
        faktur = scanner.next();
        conn = DriverManager.getConnection(url, "root", "");
        Statement st = conn.createStatement();

        System.out.println("Data yang ingin diubah\n");
        System.out.println("1. Data Pelanggan");
        System.out.println("2. Data Pembelian Barang");

        System.out.println("Pilihan anda : ");
        Scanner terminalInput = new Scanner(System.in);
        Integer PilihanUser = terminalInput.nextInt();

        switch (PilihanUser) {
            case 1:
                System.out.print("Masukkan Nama Baru : ");
                nama = scanner.next();
                System.out.print("Masukkan no HP baru : ");
                noHP = scanner.next();
                System.out.print("Masukkan Alamat baru : ");
                alamat = scanner.next();
                String sql = "UPDATE showroom SET Nama = '%s', no_HP = '%s', Alamat = '%s' WHERE Faktur ='%s'";
                sql = String.format(sql, nama, noHP,alamat, faktur);
                st.executeUpdate(sql);
                System.out.println("DATA BERHASIL DIUPDATE\n");
                break;
            case 2:
                System.out.print("Masukkan nama Barang baru : ");
                namaBarang = scanner.next();
                System.out.print("Masukkan Harga : ");
                hargaBarang = scanner.nextInt();
                System.out.print("Masukkan Jumlah : ");
                jmlBarang = scanner.nextInt();
                totalBayar = hargaBarang*jmlBarang;
                String sql2 = "UPDATE showroom SET Barang = '%s', Harga = '%s', Jumlah = '%s', Total = '%s' WHERE Faktur ='%s'";
                sql2 = String.format(sql2, namaBarang, hargaBarang, jmlBarang, totalBayar, faktur);
                st.executeUpdate(sql2);
                System.out.println("DATA BERHASIL DIUPDATE\n");
                break;
            default:
                System.err.println("\nInput anda tidak ditemukan\nSilahkan pilih [1 atau 2]");
        }
    }

    //Method untuk menghapus data pada Database
    @Override
    public void deleteData() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Masukkan Faktur Pelanggan yang ingin dihapus : ");
            faktur = scanner.next();

            conn = DriverManager.getConnection(url, "root", "");
            String sql = String.format("DELETE FROM showroom WHERE Faktur ='%s'", faktur);
            Statement st = conn.createStatement();

            st.executeUpdate(sql);
            System.out.println("Berhasil menghapus data pelanggan dengan No Faktur = " + faktur);

        } catch (Exception e) {
            System.out.println("No Faktur tidak ada dalam Database");
        }
        
    }
    
    //Method untuk melakukan print Struk
    public void printStruk(){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan Faktur Pelanggan yang ingin di Cetak : ");
        faktur = scanner.next();

        try {
            conn = DriverManager.getConnection(url, "root", "");
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM showroom WHERE Faktur =?");
            pst.setString(1, faktur);
            ResultSet result = pst.executeQuery();

            Date date = new Date();
            SimpleDateFormat hari = new SimpleDateFormat("'Hari/Tanggal \t:' EEEEEEEEEE dd-mm-yy");
            SimpleDateFormat jam =  new SimpleDateFormat("'Waktu \t\t:' hh:mm:ss z");

            if (result.next()) {
            System.out.println("----------- ShowRoom Thelema -----------");
            System.out.println(hari.format(date));
            System.out.println(jam.format(date));
            System.out.println("No Faktur \t: " + result.getString("Faktur"));
            System.out.println("====================================");
            System.out.println("---------- DATA PELANGGAN ----------");
            System.out.println("Nama Pelanggan \t: " + result.getString("Nama"));
            System.out.println("No HP \t\t: " + result.getString("no_HP"));
            System.out.println("Alamat \t\t: " + result.getString("Alamat"));
            System.out.println("------ DATA PEMBELIAN BARANG -------");
            System.out.println("Nama Barang \t: " + result.getString("Barang"));
            System.out.println("Harga \t\t: " + result.getInt("Harga"));
            System.out.println("Jumlah \t\t: " + result.getInt("Jumlah"));
            System.out.println("Total Bayar \t: " + result.getInt("Total"));
            System.out.println("------------------------------------");
            System.out.println("Kasir \t\t: Umar Abdullah A\n");
            }
            else{
                System.out.println("Data tidak ditemukan");
            }
            
        } catch (Exception e) {
            e.printStackTrace();//System.err.println("Data dengan no Faktur " + faktur + " tidak ditemukan");
        }
        
    }

    //Method fungsi boolean untuk konfirmasi
    public boolean getYesorNo(String message){
        Scanner terminalInput = new Scanner(System.in);
        System.out.print("\n"+message+" (y/n)? ");
        String pilihanUser = terminalInput.next();

        //percabangan dan implemantasi method String
        while(!pilihanUser.equalsIgnoreCase("y") && !pilihanUser.equalsIgnoreCase("n")) {
            System.err.println("Pilihan anda bukan y atau n");
            System.out.print("\n"+message+" (y/n)? ");
            pilihanUser = terminalInput.next();
        }

        return pilihanUser.equalsIgnoreCase("y");
    }
    
}
