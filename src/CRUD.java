import java.sql.SQLException;

public interface CRUD {
    //method untuk menampilkan data
    void viewData() throws SQLException;
    //method untuk menambahkan data
    void addData() throws SQLException;
    //method untuk mengubah data
    void updateData() throws SQLException;
    //method untuk menghapus data
    void deleteData() throws SQLException;
}
